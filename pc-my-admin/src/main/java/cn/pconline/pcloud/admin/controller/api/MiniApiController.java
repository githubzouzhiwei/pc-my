package cn.pconline.pcloud.admin.controller.api;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSONObject;

import cn.pconline.framework.util.DateUtils;
import cn.pconline.framework.util.StringUtils;
import cn.pconline.pcloud.admin.service.UserOpenService;
import cn.pconline.pcloud.admin.service.UserReportService;
import cn.pconline.pcloud.base.config.BaseProperties;
import cn.pconline.pcloud.base.entity.UserOpen;
import cn.pconline.pcloud.base.entity.UserReport;
import cn.pconline.pcloud.base.util.AESUtil;
import cn.pconline.pcloud.base.util.RedisTemplateUtil;

/**
 * 微信小程序授权接口API
 * @author su*
 * 首次授权步骤：
 1.小程序客户端调用wx.login拿到code，完成登录操作
 2.客户端调用后端/mini/auth接口，后端请求平台jscode2session接口获得{session_key, openid, [unionid]}，并缓存之
 3.若未拿到unionid，则客户端继续调用wx.getUserInfo(withCredentials:true)拿到加密数据[encryptedData, iv](用户必须先授权，否则没有unionid)
 4.客户端调用后端/mini/decrypt接口进行解密
 5.后端根据code从缓存中获得session_key，结合iv向量对encryptedData进行AES解密获得unionid，并缓存之
 6.后端通知客户端继续走实名认证流程
 7.客户端调用后端/mini/report接口完成实名认证，并返回其报告数据
 */
@CrossOrigin
@RestController
@RequestMapping("/api/wx/mini")
public class MiniApiController {
	
	@Autowired
	RestTemplate restTemplate;
	@Autowired
	BaseProperties baseProperties;
	@Autowired
	UserOpenService userOpenService;
	@Autowired
	UserReportService userReportService;
	@Autowired
	RedisTemplateUtil redisTemplateUtil;
	
	/**
	 * 用户授权接口(用户每次查看报告触发)
	 * https://developers.weixin.qq.com/miniprogram/dev/api-backend/open-api/login/auth.code2Session.html
	 * @param code 通过小程序客户端wx.login获得(有效期1小时)
	 * @param callback
	 * @return
	 */
	@RequestMapping(value = "/auth")
	public String auth(@RequestParam(value = "code", required = true) String code,
			@RequestParam(value = "callback", defaultValue = "", required = false) String callback) {
		
		int status = 200;
		JSONObject resultJson = new JSONObject();
		String session_key = null, unionid = null;
		String openid = redisTemplateUtil.get(code) == null ? null : redisTemplateUtil.get(code).toString();
		String nowTime = "[" + DateUtils.formatDate(new Date(), DateUtils.detailDateFormat) + "] ";
		
		if(openid != null) {
			System.out.println(nowTime + "当前处于小程序登录状态：openid=" + openid + ", code=" + code);
			
			// 已登录状态，直接加载报告数据
			this.loadDataOrOpen(openid, code, null, unionid, resultJson);
		} else {
			// 根据code重新获取openid
			String url = "https://api.weixin.qq.com/sns/jscode2session?grant_type=authorization_code"
					+ "&appid=" + baseProperties.getWxMiniId()
					+ "&secret=" + baseProperties.getWxMiniSecret()
					+ "&js_code=" + code;
			
			String wxResult = "";
			try {
				wxResult = restTemplate.getForObject(url, String.class); // 请求微信授权接口
				
				if(!StringUtils.isBlank(wxResult)) {
					System.out.println(wxResult);
					
					JSONObject json = JSONObject.parseObject(wxResult);
					if(json.containsKey("openid")) {
						openid = json.getString("openid");
						session_key = json.getString("session_key");
						if(json.containsKey("unionid")) { // 注意大小写
							unionid = json.getString("unionid");
							// 缓存起来
							redisTemplateUtil.set("unionid-" + code, unionid, 3600);
						}
						System.out.println(nowTime + "请求小程序授权接口获得：openid=" + openid + "，code=" + code);
						
						// 加载报告数据或绑定
						this.loadDataOrOpen(openid, code, session_key, unionid, resultJson);
					} else {
						System.out.println(nowTime + "请求小程序授权接口获取openid失败，code=" + code);
						status = 501;
						resultJson.put("msg", wxResult);
					}
				}
			} catch (Exception e) {
				System.out.println(url);
				status = 503;
				resultJson.put("msg", wxResult);
				e.printStackTrace();
			}
		}
		
		resultJson.put("status", status);
		String result = resultJson.toJSONString();
		
		// 跨域回调处理
		if(!StringUtils.isBlank(callback)) {
			result = callback + "(" + result + ")";
		}
		return result;
	}
	
	/**
	 * 解密获得unionid
	 * @param code
	 * @param encryptedData 通过小程序客户端wx.getUserInfo获得(包括敏感数据在内的完整用户信息的加密数据)
	 * @param iv 通过小程序客户端wx.getUserInfo获得(加密算法的初始向量)
	 * @param callback
	 * @return
	 */
	@RequestMapping(value = "/decrypt")
	public String decrypt(@RequestParam(value = "code", required = true) String code,
			@RequestParam(value = "encryptedData", required = true) String encryptedData,
			@RequestParam(value = "iv", required = true) String iv,
			@RequestParam(value = "callback", defaultValue = "", required = false) String callback) {
		
		JSONObject resultJson = new JSONObject();
		
		if(!redisTemplateUtil.hasKey("session_key-" + code)) {
			resultJson.put("status", 500);
			resultJson.put("msg", "session已过期，请重新登录");
		} else {
			String session_key = redisTemplateUtil.get("session_key-" + code).toString();
			String unionid = null;
			
			// 解密
			System.out.println(encryptedData);
			System.out.println(session_key);
			System.out.println(iv);
			String result = AESUtil.decrypt(encryptedData, session_key, iv);
			
			if(!StringUtils.isBlank(result)) {
				System.out.println("## 小程序个人信息解密..");
				System.out.println(result);
				JSONObject jo = JSONObject.parseObject(result);
				
				// 获取unionId (注意大小写)
				if(jo.containsKey("unionId")) {
					unionid = jo.getString("unionId");
					// 缓存起来
					redisTemplateUtil.set("unionid-" + code, unionid, 3600);
					
					// 查询认证信息
					UserOpen userOpen = userOpenService.findByUnionid(unionid);
					if(userOpen != null) { // 已实名认证直接出报告数据
						List<UserReport> list = userReportService.list(userOpen.getName(), userOpen.getMobile());
						resultJson.put("isFirst", 0);
						resultJson.put("msg", "查询报告完成");
						resultJson.put("data", list);
					} else { // 未认证，继续走实名认证流程
						resultJson.put("isFirst", 1);
						resultJson.put("msg", "请继续走实名认证流程..");
					}
				} else {
					// 没有授权，获取不到unionid
					resultJson.put("isFirst", 1);
					resultJson.put("msg", "未授权，请继续走用户授权流程..");
				}
			} else {
				// 解密失败，继续走实名认证流程
				resultJson.put("isFirst", 1);
				resultJson.put("msg", "解密失败，请继续走实名认证流程..");
			}
		}
		
		String result = resultJson.toJSONString();
		
		// 跨域回调处理
		if(!StringUtils.isBlank(callback)) {
			result = callback + "(" + result + ")";
		}
		return result;
	}
	
	/**
	 * 加载报告或更新授权信息
	 * @param openid
	 * @param code
	 * @param session_key
	 * @param unionid
	 * @param resultJson isFirst：0 已实名认证并返回报告数据，1 未实名认证但已拿到unionid，2 未实名认证且未拿到unionid(待wx.getUserInfo)
	 */
	private void loadDataOrOpen(String openid, String code, String session_key, String unionid, JSONObject resultJson) {
		// 记录缓存，以供接下来的校验
		if(!redisTemplateUtil.hasKey(code)) {
			redisTemplateUtil.set(code, openid, 3600);
		}
		resultJson.put("isFirst", 1); // 继续走实名认证流程
		resultJson.put("msg", "请继续走实名认证流程..");
		
		UserOpen userOpen = userOpenService.findByMiniOpenid(openid);
		
		if(userOpen == null) { // 首次小程序授权登录
			if(!StringUtils.isBlank(unionid)) {
				// 缓存起来
				if(!redisTemplateUtil.hasKey("unionid-" + code)) {
					redisTemplateUtil.set("unionid-" + code, unionid, 3600);
				}
				
				userOpen = userOpenService.findByUnionid(unionid);
				
				if(userOpen != null) { // 说明已绑定了公众号，且公众号拿到了unionid
					// 更新绑定信息
					userOpen.setMiniOpenid(openid);
					userOpen.setUpdateAt(new Date());
					userOpenService.update(userOpen);
					
					// 查询报告列表
					List<UserReport> list = userReportService.list(userOpen.getName(), userOpen.getMobile());
					resultJson.put("isFirst", 0);
					resultJson.put("msg", "查询报告完成");
					resultJson.put("data", list);
				}
			} else if(!StringUtils.isBlank(session_key)) { // 若jscode2session接口拿不到unionid，则需要前端调用wx.getUserInfo去拿
				// 缓存session_key，待下一步流程解密时使用
				if(!redisTemplateUtil.hasKey("session_key-" + code)) {
					redisTemplateUtil.set("session_key-" + code, session_key, 3600);
				}
				
				// 返回状态，等待客户端继续获取unionid密文
				resultJson.put("isFirst", 2);
				resultJson.put("msg", "请继续获取个人信息密文.."); // 需要用户授权并获取个人信息密文..
			}
		} else { // 用户已授权绑定
			// 查询报告列表
			List<UserReport> list = userReportService.list(userOpen.getName(), userOpen.getMobile());
			resultJson.put("isFirst", 0);
			resultJson.put("msg", "查询报告完成");
			resultJson.put("data", list);
		}	
	}
	
}
