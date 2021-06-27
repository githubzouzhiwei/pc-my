package cn.pconline.pcloud.admin.service;

import cn.pconline.pcloud.base.dao.ActivityDetailMapper;
import cn.pconline.pcloud.base.entity.ActivityDetail;
import cn.pconline.pcloud.base.service.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActivityDetailService extends AbstractService<ActivityDetail, ActivityDetailMapper> {

    @Autowired
    private ActivityDetailMapper activityDetailMapper;

    protected ActivityDetailService() {
        super(ActivityDetail.class, ActivityDetailMapper.class);
    }
}
