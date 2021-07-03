package cn.pconline.pcloud.admin.service;

import cn.pconline.pcloud.base.dao.ActivityPacketMapper;
import cn.pconline.pcloud.base.entity.ActivityPacket;
import cn.pconline.pcloud.base.entity.ActivityPacketExample;
import cn.pconline.pcloud.base.service.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityPacketService extends AbstractService<ActivityPacket, ActivityPacketMapper> {

    @Autowired
    private ActivityPacketMapper activityPacketMapper;

    protected ActivityPacketService() {
        super(ActivityPacket.class, ActivityPacketMapper.class);
    }

    /**
     * 获取待领取红包
     *
     * @param activityId 活动ID
     * @return
     */
    public List<ActivityPacket> listNotDraw(Long activityId) {
        ActivityPacketExample activityPacketExample = new ActivityPacketExample();
        ActivityPacketExample.Criteria criteria = activityPacketExample.createCriteria();
        criteria.andActivityIdEqualTo(activityId);
        criteria.andStatusEqualTo((byte) 0);

        return activityPacketMapper.selectByExample(activityPacketExample);
    }

    public int countByActivityIdAndOpenid(Long activityId, String openid) {
        ActivityPacketExample activityPacketExample = new ActivityPacketExample();
        ActivityPacketExample.Criteria criteria = activityPacketExample.createCriteria();
        criteria.andActivityIdEqualTo(activityId);
        criteria.andOpenidEqualTo(openid);
        return activityPacketMapper.countByExample(activityPacketExample);
    }
}
