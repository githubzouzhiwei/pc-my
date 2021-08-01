package cn.pconline.pcloud.admin.controller.front;

import cn.pconline.framework.util.StringUtils;
import cn.pconline.pcloud.admin.service.ActivityDetailService;
import cn.pconline.pcloud.admin.service.ActivityService;
import cn.pconline.pcloud.base.entity.Activity;
import cn.pconline.pcloud.base.entity.ActivityDetail;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller("frontActivityController")
@RequestMapping("/front/activity")
public class ActivityController {

    @Autowired
    private ActivityService activityService;
    @Autowired
    private ActivityDetailService activityDetailService;

    private static final String APP_ID = "wx9e8d40b7b1cfb27d";
    private static final String APP_SECRET = "e491d14da717cc5d8fd74e09d31886a1";
    private static final String AUTH_URL = "https://open.weixin.qq.com/connect/oauth2/authorize";// 微信授权链接
    private static final String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + APP_ID + "&secret=" + APP_SECRET + "&grant_type=authorization_code";

    @GetMapping("/detail/{activityId}")
    public String detail(@PathVariable("activityId") Long activityId, String code, String state, Model model, HttpServletResponse response) {
        if (activityId == null || activityId <= 0) {
            return null;
        }

        if ("1".equals(state) && StringUtils.isNotBlank(code)) {
            // 获取openid
            String openid = "";
            String getAccessTokenUrl = ACCESS_TOKEN_URL + "&code=" + code;

            HttpClient httpClient = new DefaultHttpClient();
            HttpGet httpGet = new HttpGet(getAccessTokenUrl);
            try {
                HttpResponse httpResponse = httpClient.execute(httpGet);
                if (httpResponse != null && httpResponse.getStatusLine().getStatusCode() == 200) {
                    String result = EntityUtils.toString(httpResponse.getEntity());
                    System.out.println("getAccessToken==>" + result);
                    if (StringUtils.isNotBlank(result) && result.startsWith("{")) {
                        JSONObject jsonObject = JSON.parseObject(result);
                        openid = jsonObject.getString("openid");
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }


            try {
                response.sendRedirect("http://ex.5cy.cn/front/activity/detail/" + activityId + "?openid=" + openid + "&state=2");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        if (StringUtils.isBlank(state) && StringUtils.isBlank(code)) {
            String redirectUrl = AUTH_URL + "?appid=" + APP_ID;
            redirectUrl += "&redirect_uri=http%3A%2F%2Fex.5cy.cn%2Ffront%2Factivity%2Fdetail%2F" + activityId;
            redirectUrl += "&response_type=code&scope=snsapi_base&state=1#wechat_redirect";
            try {
                response.sendRedirect(redirectUrl);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        Activity activity = activityService.find(activityId);
        model.addAttribute("activity", activity);
        ActivityDetail activityDetail = activityDetailService.find(activityId);
        model.addAttribute("activityDetail", activityDetail);

        return "front/activity/detail";
    }

    @GetMapping("/checkJoinArea/{activityId}")
    @ResponseBody
    public JSONObject checkJoinArea(@PathVariable("activityId") Long activityId, String location) {
        return activityService.checkJoinArea(activityId, location);
    }

    /**
     * 领取红包
     *
     * @param activityId 活动ID
     * @param openid     微信openid
     * @return
     */
    @GetMapping("/drawPacket/{activityId}")
    @ResponseBody
    public JSONObject drawPacket(@PathVariable("activityId") Long activityId, String openid) {
        return activityService.drawPacket(activityId, openid);
    }

}
