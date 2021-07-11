package cn.pconline.pcloud.admin.controller.front;

import cn.pconline.framework.util.StringUtils;
import cn.pconline.pcloud.admin.service.ActivityDetailService;
import cn.pconline.pcloud.admin.service.ActivityService;
import cn.pconline.pcloud.base.entity.Activity;
import cn.pconline.pcloud.base.entity.ActivityDetail;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller("frontActivityController")
@RequestMapping("/front/activity")
public class ActivityController {

    @Autowired
    private ActivityService activityService;
    @Autowired
    private ActivityDetailService activityDetailService;

    @GetMapping("/detail/{activityId}")
    public String article(@PathVariable("activityId") Long activityId, String code, Model model) {
        if (activityId != null && activityId > 0) {
            Activity activity = activityService.find(activityId);
            model.addAttribute("activity", activity);
            ActivityDetail activityDetail = activityDetailService.find(activityId);
            model.addAttribute("activityDetail", activityDetail);
        }
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
