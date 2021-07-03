package cn.pconline.pcloud.admin.controller.front;

import cn.pconline.framework.util.StringUtils;
import cn.pconline.pcloud.admin.service.ActivityDetailService;
import cn.pconline.pcloud.admin.service.ActivityService;
import cn.pconline.pcloud.base.entity.Activity;
import cn.pconline.pcloud.base.entity.ActivityDetail;
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

import java.io.IOException;

@Controller("frontActivityController")
@RequestMapping("/front/activity")
public class ActivityController {

    @Autowired
    private ActivityService activityService;
    @Autowired
    private ActivityDetailService activityDetailService;

    @GetMapping("/detail/{activityId}")
    public String article(@PathVariable("activityId") Long activityId, Model model) {
        if (activityId != null && activityId > 0) {
            Activity activity = activityService.find(activityId);
            model.addAttribute("activity", activity);
            ActivityDetail activityDetail = activityDetailService.find(activityId);
            model.addAttribute("activityDetail", activityDetail);
        }
        return "front/activity/detail";
    }

    @GetMapping("/checkJoinArea")
    public String checkJoinArea(Long activityId, String location) {
        if (activityId == null || activityId <= 0 || StringUtils.isBlank(location)) {
            return null;
        }

        return activityService.checkJoinArea(activityId, location);
    }

}
