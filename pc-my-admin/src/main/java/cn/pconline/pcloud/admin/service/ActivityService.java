package cn.pconline.pcloud.admin.service;

import cn.pconline.framework.util.StringUtils;
import cn.pconline.pcloud.admin.form.ActivityPageForm;
import cn.pconline.pcloud.admin.form.ActivitySaveDetailForm;
import cn.pconline.pcloud.admin.form.ActivitySaveForm;
import cn.pconline.pcloud.base.dao.ActivityMapper;
import cn.pconline.pcloud.base.dao.ActivityPacketConfigMapper;
import cn.pconline.pcloud.base.dao.ActivityPacketMapper;
import cn.pconline.pcloud.base.entity.*;
import cn.pconline.pcloud.base.entity.system.User;
import cn.pconline.pcloud.base.enums.ActivityPacketStatus;
import cn.pconline.pcloud.base.enums.ActivityStatus;
import cn.pconline.pcloud.base.service.AbstractService;
import cn.pconline.pcloud.base.util.HelperUtil;
import cn.pconline.pcloud.base.util.PacketUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ActivityService extends AbstractService<Activity, ActivityMapper> {

    @Autowired
    private ActivityMapper activityMapper;
    @Autowired
    private ActivityPacketMapper activityPacketMapper;
    @Autowired
    private ActivityPacketConfigMapper activityPacketConfigMapper;
    @Autowired
    private ActivityDetailService activityDetailService;
    /**
     * 百度反地理编码查询接口
     */
    private String BAIDU_REVERSE_GEOCODING_URL = "http://api.map.baidu.com/reverse_geocoding/v3/?ak=GnPeiLQbo8nq3rsVraijPlZm5x3klTHt&output=json&coordtype=wgs84ll";

    public ActivityService() {
        super(Activity.class, ActivityMapper.class);
    }

    public PageInfo<Activity> pager(ActivityPageForm pageForm) {
        ActivityExample example = new ActivityExample();
        // 分页查询
        PageHelper.startPage(pageForm.getPage(), pageForm.getLimit());
        // 排序条件
        if (StringUtils.isNotBlank(pageForm.getField())) {
            example.setOrderByClause(HelperUtil.toUnderline(pageForm.getField()) + " " + pageForm.getOrder());
        } else {
            example.setOrderByClause("create_at desc");
        }
        return new PageInfo<>(activityMapper.selectByExample(example));
    }

    public void save(ActivitySaveForm form) {
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();

        if (form.getActivityId() != null && form.getActivityId() > 0) {
            Activity activity = this.find(form.getActivityId());
            if (activity == null) {
                return;
            }

            activity.setName(form.getName());
            activity.setStartTime(form.getStartTime());
            activity.setEndTime(form.getEndTime());
            activity.setContactNumber(form.getContactNumber());
            activity.setApplyUrl(form.getApplyUrl());
            activity.setApplyTitle(form.getApplyTitle());
            activity.setApplyButtonUrl(form.getApplyButtonUrl());
            activity.setApplyLogoUrl(form.getApplyLogoUrl());

            byte isSetStayTime = 0;
            if (form.getIsSetStayTime() != null) {
                isSetStayTime = form.getIsSetStayTime().byteValue();
            }
            activity.setIsSetStayTime(isSetStayTime);
            if (isSetStayTime == 0) {
                activity.setStayTime(0);
            } else {
                activity.setStayTime(form.getStayTime());
            }
            byte isSetJoinArea = 0;
            if (form.getIsSetJoinArea() != null) {
                isSetJoinArea = form.getIsSetJoinArea().byteValue();
            }
            activity.setIsSetJoinArea(isSetJoinArea);
            if (isSetJoinArea == 0) {
                activity.setJoinArea("");
            } else {
                activity.setJoinArea(form.getJoinArea());
            }
            byte isNeedFollow = 0;
            if (form.getIsNeedFollow() != null) {
                isNeedFollow = form.getIsNeedFollow().byteValue();
            }
            activity.setIsNeedFollow(isNeedFollow);

            activity.setUpdateBy(user.getName());
            activity.setUpdateAt(new Date());

            this.update(activity);
        } else {
            Activity activity = new Activity();
            activity.setName(form.getName());
            activity.setStartTime(form.getStartTime());
            activity.setEndTime(form.getEndTime());
            activity.setContactNumber(form.getContactNumber());
            activity.setApplyUrl(form.getApplyUrl());
            activity.setApplyTitle(form.getApplyTitle());
            activity.setApplyButtonUrl(form.getApplyButtonUrl());
            activity.setApplyLogoUrl(form.getApplyLogoUrl());

            int funds = 0;
            if (form.getFunds() != null) {
                funds = form.getFunds().intValue() * 100;
            }
            activity.setFunds(funds);
            activity.setPacketNum(form.getPacketNum());

            Integer packetType = form.getPacketType();
            if (packetType != null) {
                activity.setPacketType(packetType.byteValue());
            }

            int packetAmount = 0;
            if (form.getPacketAmount() != null) {
                packetAmount = BigDecimal.valueOf(form.getPacketAmount()).multiply(BigDecimal.valueOf(100)).intValue();
            }
            int minPacketAmount = 0;
            if (form.getMinPacketAmount() != null) {
                minPacketAmount = BigDecimal.valueOf(form.getMinPacketAmount()).multiply(BigDecimal.valueOf(100)).intValue();
            }
            activity.setMinPacketAmount(minPacketAmount);
            int maxPacketAmount = 0;
            if (form.getMaxPacketAmount() != null) {
                maxPacketAmount = BigDecimal.valueOf(form.getMaxPacketAmount()).multiply(BigDecimal.valueOf(100)).intValue();
            }

            byte remainFundsAllocType = 1;
            if (form.getRemainFundsAllocType() != null) {
                remainFundsAllocType = form.getRemainFundsAllocType().byteValue();
            }
            int remainFunds = 0;// 剩余经费
            if (form.getRemainFunds() != null) {
                remainFunds = BigDecimal.valueOf(form.getRemainFunds()).multiply(BigDecimal.valueOf(100)).intValue();
            }
            int remainPacketNum = 0;
            if (form.getRemainPacketNum() != null) {
                remainPacketNum = form.getRemainPacketNum();
            }
            if (packetType != null && packetType == 1) {// 固定金额
                activity.setPacketAmount(packetAmount);
            } else if (packetType != null && packetType == 2) {// 随机金额
                activity.setMaxPacketAmount(maxPacketAmount);
            } else if (packetType != null && packetType == 3) {// 指定红包金额及数量
                activity.setRemainFundsAllocType(remainFundsAllocType);
                activity.setRemainFunds(remainFunds);
                activity.setRemainPacketNum(remainPacketNum);
            }

            byte isSetStayTime = 0;
            if (form.getIsSetStayTime() != null) {
                isSetStayTime = form.getIsSetStayTime().byteValue();
            }
            activity.setIsSetStayTime(isSetStayTime);
            activity.setStayTime(form.getStayTime());
            byte isSetJoinArea = 0;
            if (form.getIsSetJoinArea() != null) {
                isSetJoinArea = form.getIsSetJoinArea().byteValue();
            }
            activity.setIsSetJoinArea(isSetJoinArea);
            activity.setJoinArea(form.getJoinArea());
            byte isNeedFollow = 0;
            if (form.getIsNeedFollow() != null) {
                isNeedFollow = form.getIsNeedFollow().byteValue();
            }
            activity.setIsNeedFollow(isNeedFollow);

            activity.setStatus((byte) ActivityStatus.OPEN.getStatus());

            activity.setCreateBy(user.getName());
            activity.setCreateAt(new Date());
            activity.setUpdateBy(user.getName());
            activity.setUpdateAt(new Date());

            this.create(activity);
            Long activityId = activity.getActivityId();
            int packetNum = 0;
            if (form.getPacketNum() != null) {
                packetNum = form.getPacketNum();
            }

            if (packetNum > 0) {
                List<ActivityPacket> list = null;
                // 红包金额生成
                if (packetType != null && packetType == 1) {// 固定红包金额
                    list = new ArrayList<>(packetNum);
                    BigDecimal averageAmount = BigDecimal.valueOf(funds).divide(BigDecimal.valueOf(packetNum), 2, BigDecimal.ROUND_HALF_UP);

                    ActivityPacket activityPacket = new ActivityPacket();
                    activityPacket.setActivityId(activityId);
                    activityPacket.setPacketAmount(averageAmount.intValue());
                    activityPacket.setStatus((byte) ActivityPacketStatus.WAIT_DRAW.getStatus());

                    for (int i = 0; i < packetNum; i++) {
                        list.add(activityPacket);
                    }
                } else if (packetType != null && packetType == 2) {// 随机红包金额
                    list = new ArrayList<>(packetNum);
                    List<Integer> packetAmounts = PacketUtil.getRandomAmount(funds, packetNum, minPacketAmount, maxPacketAmount);
                    ActivityPacket activityPacket;
                    for (Integer amount : packetAmounts) {
                        activityPacket = new ActivityPacket();
                        activityPacket.setActivityId(activityId);
                        activityPacket.setStatus((byte) ActivityPacketStatus.WAIT_DRAW.getStatus());
                        activityPacket.setPacketAmount(amount);
                        list.add(activityPacket);
                    }
                } else if (packetType != null && packetType == 3) {// 指定红包金额及数量
                    list = new ArrayList<>(packetNum);
                    ActivityPacket activityPacket;
                    List<Double> configPacketAmounts = form.getConfigPacketAmounts();
                    List<Integer> configPacketNums = form.getConfigPacketNums();
                    if (configPacketAmounts != null && configPacketNums != null && configPacketAmounts.size() == configPacketNums.size()) {
                        int size = configPacketAmounts.size();
                        for (int i = 0; i < size; i++) {
                            int configPacketAmount = BigDecimal.valueOf(configPacketAmounts.get(i)).multiply(BigDecimal.valueOf(100)).intValue();// 转成分
                            Integer configPacketNum = configPacketNums.get(i);

                            // 保存红包配置
                            ActivityPacketConfig activityPacketConfig = new ActivityPacketConfig();
                            activityPacketConfig.setActivityId(activityId);
                            activityPacketConfig.setPacketAmount(configPacketAmount);
                            activityPacketConfig.setPacketNum(configPacketNum);
                            activityPacketConfigMapper.insert(activityPacketConfig);

                            for (Integer k = 0; k < configPacketNum; k++) {
                                activityPacket = new ActivityPacket();
                                activityPacket.setActivityId(activityId);
                                activityPacket.setStatus((byte) ActivityPacketStatus.WAIT_DRAW.getStatus());
                                activityPacket.setPacketAmount(configPacketAmount);
                                list.add(activityPacket);
                            }
                        }
                        if (remainFunds > 0 && remainPacketNum > 0) {
                            // 剩余经费分配方式
                            int averageAmount = remainFunds / remainPacketNum;// 平均金额（单位：分）
                            if (remainFundsAllocType == 1) {// 平均分配
                                for (int i = 0; i < remainPacketNum; i++) {
                                    activityPacket = new ActivityPacket();
                                    activityPacket.setActivityId(activityId);
                                    activityPacket.setStatus((byte) ActivityPacketStatus.WAIT_DRAW.getStatus());
                                    activityPacket.setPacketAmount(averageAmount);
                                    list.add(activityPacket);
                                }
                            } else if (remainFundsAllocType == 2) {// 随机分配
                                List<Integer> packetAmounts = PacketUtil.getRandomAmount(remainFunds, remainPacketNum, 1, averageAmount + 1);
                                for (Integer amount : packetAmounts) {
                                    activityPacket = new ActivityPacket();
                                    activityPacket.setActivityId(activityId);
                                    activityPacket.setStatus((byte) ActivityPacketStatus.WAIT_DRAW.getStatus());
                                    activityPacket.setPacketAmount(amount);
                                    list.add(activityPacket);
                                }
                            }
                        }
                    }
                }
                if (list != null && list.size() > 0) {
                    activityPacketMapper.insertBatch(list);
                }
            }
        }
    }

    /**
     * 保存详情
     *
     * @param form
     */
    public void saveDetail(ActivitySaveDetailForm form) {
        Long activityId = form.getActivityId();

        ActivityDetail activityDetail = activityDetailService.find(activityId);
        if (activityDetail == null) {
            activityDetail = new ActivityDetail();
            activityDetail.setActivityId(activityId);
            activityDetail.setShareTitle(form.getShareTitle());
            activityDetail.setShareDesc(form.getShareDesc());
            activityDetail.setSharePicUrl(form.getSharePicUrl());
            activityDetail.setSource(form.getSource().byteValue());
            activityDetail.setSourceInfo(form.getSourceInfo());
            activityDetail.setContent(form.getContent());
            activityDetailService.create(activityDetail);
        } else {
            activityDetail.setShareTitle(form.getShareTitle());
            activityDetail.setShareDesc(form.getShareDesc());
            activityDetail.setSharePicUrl(form.getSharePicUrl());
            activityDetail.setSource(form.getSource().byteValue());
            activityDetail.setSourceInfo(form.getSourceInfo());
            activityDetail.setContent(form.getContent());
            activityDetailService.update(activityDetail);
        }
    }

    public String checkJoinArea(Long activityId, String location) {
        Activity activity = find(activityId);
        if (activity == null) {
            return null;
        }

        if (StringUtils.isBlank(location)) {
            return null;
        }

        String joinArea = activity.getJoinArea();
        if (StringUtils.isBlank(joinArea)) {
            return null;
        }

        // 获取用户地区
        String userArea = this.getArea(location);
        boolean canJoin = false;
        String[] joinAreas = joinArea.split(",");
        for (String area : joinAreas) {
            if (area.equals(userArea)) {
                canJoin = true;
                break;
            }
        }

        JSONObject result = new JSONObject();

        if (canJoin) {
            result.put("code", 0);
        } else {
            result.put("code", -1);
            result.put("msg", "本活动仅限" + joinArea + "的用户参加，但不影响您的好友助力。感谢您的支持和关注！");
        }

        return result.toJSONString();
    }

    private String getArea(String location) {
        HttpClient httpClient = new DefaultHttpClient();
        String url = BAIDU_REVERSE_GEOCODING_URL + "&location=" + location;
        HttpGet httpGet = new HttpGet(url);
        try {
            HttpResponse httpResponse = httpClient.execute(httpGet);
            if (httpResponse != null && httpResponse.getStatusLine().getStatusCode() == 200) {
                String result = EntityUtils.toString(httpResponse.getEntity());
                System.out.println("result==>" + result);
                if (StringUtils.isNotBlank(result) && result.startsWith("{")) {
                    JSONObject jsonObject = JSON.parseObject(result);
                    int status = jsonObject.getIntValue("status");
                    if (status == 0) {
                        return jsonObject.getJSONObject("result").getString("formatted_address");
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

}
