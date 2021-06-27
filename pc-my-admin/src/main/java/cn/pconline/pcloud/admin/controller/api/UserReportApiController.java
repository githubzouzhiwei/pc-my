package cn.pconline.pcloud.admin.controller.api;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;

import cn.pconline.framework.util.StringUtils;
import cn.pconline.pcloud.admin.service.UserOpenService;
import cn.pconline.pcloud.admin.service.UserReportService;
import cn.pconline.pcloud.base.entity.UserOpen;
import cn.pconline.pcloud.base.entity.UserReport;
import cn.pconline.pcloud.base.util.RedisTemplateUtil;

/**
 * 用户报告数据接口【实名认证专用】
 * @author su*
 *
 */
@CrossOrigin
@RestController
@RequestMapping("/api")
public class UserReportApiController {
	
	@Autowired
	UserReportService userReportService;
	@Autowired
	UserOpenService userOpenService;
	@Autowired
	RedisTemplateUtil redisTemplateUtil;

	/**
	 * 实名认证及查询用户报告(用户第一次授权并查看报告时触发)
	 * @param name
	 * @param mobile
	 * @param code 微信公众号用户授权临时编码
	 * @param callback
	 * @return
	 */
	@RequestMapping(value = "/report")
	public String getOpgReport(@RequestParam(value = "name", required = true) String name,
			@RequestParam(value = "mobile", required = true) String mobile,
			@RequestParam(value = "code", required = true) String code,
			@RequestParam(value = "callback", defaultValue = "", required = false) String callback) {
		
		int status = 200;
		String msg = "";
		JSONObject resultJson = new JSONObject();
		
		String openid = redisTemplateUtil.get(code) == null 
				? null : redisTemplateUtil.get(code).toString();
		String unionid = redisTemplateUtil.get("unionid-" + code) == null 
				? null : redisTemplateUtil.get("unionid-" + code).toString();
		
		if(StringUtils.isBlank(openid)) {
			status = 1001;
			msg = "登录凭证无效，请退出再重新进入";
		} else {
			// 查询用户授权绑定信息
			UserOpen userOpen = userOpenService.findByOpenid(openid); // 公众号
			if(userOpen == null && unionid != null) {
				userOpen = userOpenService.findByUnionid(unionid);
			}
			if(userOpen == null) {
				userOpen = userOpenService.find(name, mobile);
			}
			if(userOpen != null) {
				if(!userOpen.getName().equals(name)) {
					status = 1002;
					msg = "姓名不正确：" + name;
				} else if(!userOpen.getMobile().equals(mobile)) {
					status = 1003;
					msg = "手机号不正确：" + mobile;
				} else if(!StringUtils.isBlank(userOpen.getOpenid()) && !userOpen.getOpenid().equals(openid)) {
					status = 1004;
					msg = "手机已经绑定其它微信，如有疑问请联系客服";
					System.out.println(name + " 正在尝试查看他人的报告!!");
				}
			}
			
			if(status == 200) {
				// 查询用户报告
				List<UserReport> list = userReportService.list(name, mobile);
				
				if(list != null && !list.isEmpty()) {
					resultJson.put("data", list);
					
					if(userOpen == null) { // 首次授权绑定并实名认证入库
						userOpen = new UserOpen();
						if(!StringUtils.isBlank(unionid)) {
							userOpen.setUnionid(unionid);
						}
						userOpen.setOpenid(openid);
						userOpen.setName(name);
						userOpen.setMobile(mobile);
						userOpen.setStatus(1);
						userOpen.setCreateAt(new Date());
						userOpen.setUpdateAt(new Date());
						userOpenService.create(userOpen);
						System.out.println("## 用户(" + name + ")在公众号首次实名认证成功！");
					} else {
						if(!StringUtils.isBlank(unionid)) {
							userOpen.setUnionid(unionid);
						}
						userOpen.setOpenid(openid);
						userOpen.setName(name);
						userOpen.setMobile(mobile);
						userOpen.setUpdateAt(new Date());
						userOpenService.update(userOpen);
						System.out.println("## 用户(" + name + ")在公众号更新实名认证成功！");
					}
				} else {
					status = 1005;
					msg = "暂无报告数据";
				}
			} else {
				System.out.println("openid=" + openid + ", code=" + code + ", status=" + status);
			}
		}
		
		resultJson.put("status", status);
		resultJson.put("msg", msg);
		String result = resultJson.toJSONString();
		
		// 跨域回调处理
		if(!StringUtils.isBlank(callback)) {
			result = callback + "(" + result + ")";
		}
		return result;
	}
	
	
	/**
	 * 实名认证及查询用户报告(用户第一次授权并查看报告时触发)
	 * @param name
	 * @param mobile
	 * @param code 微信小程序用户授权临时编码
	 * @param callback
	 * @return
	 */
	@RequestMapping(value = "/mini/report")
	public String getOpgReport4Mini(@RequestParam(value = "name", required = true) String name,
			@RequestParam(value = "mobile", required = true) String mobile,
			@RequestParam(value = "code", required = true) String code,
			@RequestParam(value = "callback", defaultValue = "", required = false) String callback) {
		
		int status = 200;
		String msg = "";
		JSONObject resultJson = new JSONObject();
		
		String openid = redisTemplateUtil.get(code) == null 
				? null : redisTemplateUtil.get(code).toString();
		String unionid = redisTemplateUtil.get("unionid-" + code) == null 
				? null : redisTemplateUtil.get("unionid-" + code).toString();
		
		if(StringUtils.isBlank(openid)) {
			status = 1001;
			msg = "登录凭证无效，请退出再重新进入";
		} else {
			// 查询用户授权绑定信息
			UserOpen userOpen = userOpenService.findByMiniOpenid(openid); // 小程序
			if(userOpen == null && unionid != null) {
				userOpen = userOpenService.findByUnionid(unionid);
			}
			if(userOpen == null) {
				userOpen = userOpenService.find(name, mobile);
			}
			if(userOpen != null) {
				if(!userOpen.getName().equals(name)) {
					status = 1002;
					msg = "姓名不正确：" + name;
				} else if(!userOpen.getMobile().equals(mobile)) {
					status = 1003;
					msg = "手机号不正确：" + mobile;
				} else if(!StringUtils.isBlank(userOpen.getMiniOpenid()) && !userOpen.getMiniOpenid().equals(openid)) {
					status = 1004;
					msg = "手机已经绑定其它微信，如有疑问请联系客服";
					System.out.println(name + " 正在尝试查看他人的报告!!");
				}
			}
			
			if(status == 200) {
				// 查询用户报告
				List<UserReport> list = userReportService.list(name, mobile);
				
				if(list != null && !list.isEmpty()) {
					resultJson.put("data", list);
					
					if(userOpen == null) { // 首次授权绑定并实名认证入库
						userOpen = new UserOpen();
						if(!StringUtils.isBlank(unionid)) {
							userOpen.setUnionid(unionid);
						}
						userOpen.setMiniOpenid(openid);
						userOpen.setName(name);
						userOpen.setMobile(mobile);
						userOpen.setStatus(1);
						userOpen.setCreateAt(new Date());
						userOpen.setUpdateAt(new Date());
						userOpenService.create(userOpen);
						System.out.println("## 用户(" + name + ")在小程序首次实名认证成功！");
					} else {
						if(!StringUtils.isBlank(unionid)) {
							userOpen.setUnionid(unionid);
						}
						userOpen.setMiniOpenid(openid);
						userOpen.setName(name);
						userOpen.setMobile(mobile);
						userOpen.setUpdateAt(new Date());
						userOpenService.update(userOpen);
						System.out.println("## 用户(" + name + ")在小程序更新实名认证成功！");
					}
				} else {
					status = 1005;
					msg = "暂无报告数据";
				}
			} else {
				System.out.println("openid=" + openid + ", code=" + code + ", status=" + status);
			}
		}
		
		resultJson.put("status", status);
		resultJson.put("msg", msg);
		String result = resultJson.toJSONString();
		
		// 跨域回调处理
		if(!StringUtils.isBlank(callback)) {
			result = callback + "(" + result + ")";
		}
		return result;
	}
	
}
