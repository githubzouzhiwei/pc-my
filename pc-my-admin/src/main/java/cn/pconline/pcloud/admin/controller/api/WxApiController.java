package cn.pconline.pcloud.admin.controller.api;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.alibaba.fastjson.JSON;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSONObject;

import cn.pconline.framework.util.DateUtils;
import cn.pconline.framework.util.StringUtils;
import cn.pconline.pcloud.admin.service.UserOpenService;
import cn.pconline.pcloud.admin.service.UserReportService;
import cn.pconline.pcloud.base.config.BaseProperties;
import cn.pconline.pcloud.base.entity.UserOpen;
import cn.pconline.pcloud.base.entity.UserReport;
import cn.pconline.pcloud.base.util.RedisTemplateUtil;

/**
 * 微信公众号授权接口API
 *
 * @author su*
 * 首次授权步骤：
 * 1.小程序客户端调用wx.login拿到code，完成登录操作
 * 2.客户端调用后端/auth接口，后端请求平台access_token接口获得{openid}，并缓存之
 * 3.后端根据openid请求平台/cgi-bin/token接口获得access_token
 * 4.后端根据openid和access_token请求平台/cgi-bin/user/info接口获得unionid，并缓存之
 * 5.后端通知客户端继续走实名认证流程
 * 6.客户端调用后端/report接口完成实名认证，并返回其报告数据
 */
@CrossOrigin
@RestController
@RequestMapping("/api/wx")
public class WxApiController {

    // String state = "chunya123"; // 客户端wx.login才需要

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
    // 春芽佛山医院服务号
    private static final String APP_ID = "wx9e8d40b7b1cfb27d";
    private static final String APP_SECRET = "e491d14da717cc5d8fd74e09d31886a1";

    @GetMapping({"/ticket"})
    @ResponseBody
    public String ticket(String url, @RequestParam(required = false) String callback) {
        String accessToken = getAccessToken(false);
        if (StringUtils.isBlank(accessToken)) {
            if (StringUtils.isBlank(callback)) {
                return null;
            }
            return callback + "()";
        }

        String ticket = getTicket(accessToken);
        String nonceStr = UUID.randomUUID().toString();
        String timestamp = Long.toString(System.currentTimeMillis() / 1000L);

        String str = "jsapi_ticket=" + ticket + "&noncestr=" + nonceStr + "&timestamp=" + timestamp + "&url=" + url;

        JSONObject result = new JSONObject();
        result.put("appId", APP_ID);
        result.put("nonceStr", nonceStr);
        result.put("timestamp", timestamp);
        result.put("signature", DigestUtils.shaHex(str));

        result.put("code", 0);

        if (StringUtils.isBlank(callback)) {
            return result.toJSONString();
        }

        return callback + "(" + result.toJSONString() + ")";
    }

    private String getTicket(String accessToken) {
        if (StringUtils.isBlank(accessToken)) {
            return null;
        }

        Object object = this.redisTemplateUtil.get(APP_ID + "_ticket");
        if (object != null) {
            return String.valueOf(object);
        }

        String getTicketUrl = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?type=jsapi&access_token=";

        String data = getHttpRequestData(getTicketUrl + accessToken);
        if ((StringUtils.isBlank(data)) || (!data.startsWith("{"))) {
            return null;
        }

        JSONObject jsonObject = JSON.parseObject(data);
        int errcode = jsonObject.getIntValue("errcode");

        if (errcode != 40001) {
            return jsonObject.getString("ticket");
        }

        accessToken = getAccessToken(true);
        data = getHttpRequestData(getTicketUrl + accessToken);
        if ((StringUtils.isBlank(data)) || (!data.startsWith("{"))) {
            return null;
        }

        jsonObject = JSON.parseObject(data);
        String ticket = jsonObject.getString("ticket");
        int expiresIn = jsonObject.getIntValue("expires_in");

        this.redisTemplateUtil.set(APP_ID + "_ticket", ticket, expiresIn);

        return ticket;
    }

    private String getHttpRequestData(String url) {
        HttpClient httpClient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet();
        try {
            URI uri = new URI(url);
            httpGet.setURI(uri);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        try {
            HttpResponse httpResponse = httpClient.execute(httpGet);
            if (httpResponse == null) {
                return null;
            }

            HttpEntity httpEntity = httpResponse.getEntity();
            if (httpEntity == null) {
                return null;
            }

            String result = EntityUtils.toString(httpEntity, "UTF-8");
            if (StringUtils.isBlank(result)) {
                return null;
            }

            return result;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String getAccessToken(boolean refresh) {
        if (!refresh) {
            Object object = this.redisTemplateUtil.get(APP_ID + "_access_token");
            if (object != null) {
                return String.valueOf(object);
            }
        }

        String getAccessTokenUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + APP_ID + "&secret=" + APP_SECRET;
        String result = getHttpRequestData(getAccessTokenUrl);

        if ((StringUtils.isBlank(result)) || (!result.startsWith("{"))) {
            return null;
        }

        JSONObject jsonObject = JSON.parseObject(result);
        String accessToken = jsonObject.getString("access_token");

        if (StringUtils.isNotBlank(accessToken)) {
            System.out.println("设置缓存:" + accessToken);
            this.redisTemplateUtil.set(APP_ID + "_access_token", accessToken, 7200L);
        }

        return accessToken;
    }

    /**
     * 用户授权接口(用户每次查看报告触发)
     * https://developers.weixin.qq.com/doc/offiaccount/OA_Web_Apps/Wechat_webpage_authorization.html
     *
     * @param code     通过公众号客户端wx.login获得(有效期1小时)
     * @param callback
     * @return
     */
    @RequestMapping(value = "/auth")
    public String auth(@RequestParam(value = "code", required = true) String code,
                       @RequestParam(value = "callback", defaultValue = "", required = false) String callback) {

        int status = 200;
        JSONObject resultJson = new JSONObject();
        String openid = redisTemplateUtil.get(code) == null ? null : redisTemplateUtil.get(code).toString();
        String nowTime = "[" + DateUtils.formatDate(new Date(), DateUtils.detailDateFormat) + "] ";

        if (openid != null) {
            System.out.println(nowTime + "当前处于公众号登录状态：openid=" + openid + ", code=" + code);

            // 已登录状态，直接加载报告数据
            this.loadDataOrOpen(openid, code, resultJson);
        } else {
            // 根据code重新获取openid
            String url = "https://api.weixin.qq.com/sns/oauth2/access_token?grant_type=authorization_code"
                    + "&appid=" + baseProperties.getWxAppId()
                    + "&secret=" + baseProperties.getWxAppSecret()
                    + "&code=" + code;

            String wxResult = "";
            try {
                wxResult = restTemplate.getForObject(url, String.class); // 请求微信授权接口

                if (!StringUtils.isBlank(wxResult)) {
                    System.out.println(wxResult);

                    JSONObject json = JSONObject.parseObject(wxResult);
                    if (json.containsKey("openid")) {
                        openid = json.getString("openid");
                        System.out.println(nowTime + "请求公众号授权接口获得：openid=" + openid + "，code=" + code);

                        // 加载报告数据或更新绑定信息
                        this.loadDataOrOpen(openid, code, resultJson);
                    } else {
                        System.out.println(nowTime + "请求公众号授权接口获取openid失败，code=" + code);
                        status = 1002;
                        resultJson.put("msg", wxResult);
                    }
                }
            } catch (Exception e) {
                System.out.println(url);
                status = 500;
                resultJson.put("msg", wxResult);
                e.printStackTrace();
            }
        }

        resultJson.put("status", status);
        String result = resultJson.toJSONString();

        // 跨域回调处理
        if (!StringUtils.isBlank(callback)) {
            result = callback + "(" + result + ")";
        }
        return result;
    }

    /**
     * 加载报告或更新授权信息
     * https://developers.weixin.qq.com/doc/offiaccount/Basic_Information/Get_access_token.html
     * https://developers.weixin.qq.com/doc/offiaccount/User_Management/Get_users_basic_information_UnionID.html#UinonId
     *
     * @param openid
     * @param code
     * @param resultJson isFirst：0 已实名认证并返回报告数据，1 未实名认证
     */
    private void loadDataOrOpen(String openid, String code, JSONObject resultJson) {
        // 记录缓存，以供接下来的校验
        if (!redisTemplateUtil.hasKey(code)) {
            redisTemplateUtil.set(code, openid, 3600);
        }
        resultJson.put("isFirst", 1); // 继续走实名认证流程
        resultJson.put("msg", "请继续走实名认证流程..");

        UserOpen userOpen = userOpenService.findByOpenid(openid);

        if (userOpen == null) { // 首次公众号授权登录
            // 获取token
            String tokenUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential"
                    + "&appid=" + baseProperties.getWxAppId()
                    + "&secret=" + baseProperties.getWxAppSecret();
            String access_token = "";
            try {
                System.out.println("## 开始获取公众号access_token..");
                String tokenResult = restTemplate.getForObject(tokenUrl, String.class); // 请求微信获取token(有效期2小时)
                if (!StringUtils.isBlank(tokenResult)) {
                    JSONObject json = JSONObject.parseObject(tokenResult);
                    if (json.containsKey("access_token")) {
                        access_token = json.getString("access_token"); // 只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段。
                    }
                    System.out.println(tokenResult);
                }
            } catch (Exception e) {
                System.out.println(tokenUrl);
                e.printStackTrace();
            }

            String unionid = "";
            if (!StringUtils.isBlank(access_token)) {
                // 调用接口获取unionid
                String url = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=" + access_token
                        + "&openid=" + openid
                        + "&lang=zh_CN";

                String infoResult = "";
                try {
                    System.out.println("## 开始获取公众号Unionid..");
                    infoResult = restTemplate.getForObject(url, String.class); // 请求微信拉取用户信息接口

                    if (!StringUtils.isBlank(infoResult)) {
                        JSONObject json = JSONObject.parseObject(infoResult);
                        if (json.containsKey("unionid")) {
                            unionid = json.getString("unionid"); // 只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段。
                        }
                        System.out.println(infoResult);
                    }
                } catch (Exception e) {
                    System.out.println(url);
                    e.printStackTrace();
                }
            }

            if (!StringUtils.isBlank(unionid)) {
                // 缓存起来
                if (!redisTemplateUtil.hasKey("unionid-" + code)) {
                    redisTemplateUtil.set("unionid-" + code, unionid, 3600);
                }

                // 根据unionid查询userOpen
                userOpen = userOpenService.findByUnionid(unionid);

                if (userOpen != null) { // 说明已绑定了小程序，且小程序拿到了unionid
                    // 更新绑定信息
                    userOpen.setOpenid(openid);
                    userOpen.setUpdateAt(new Date());
                    userOpenService.update(userOpen);

                    // 查询报告列表
                    List<UserReport> list = userReportService.list(userOpen.getName(), userOpen.getMobile());
                    resultJson.put("isFirst", 0);
                    resultJson.put("msg", "查询报告完成");
                    resultJson.put("data", list);
                }
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
