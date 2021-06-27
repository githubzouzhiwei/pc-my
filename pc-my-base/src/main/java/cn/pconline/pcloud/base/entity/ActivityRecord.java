package cn.pconline.pcloud.base.entity;

import java.util.Date;

public class ActivityRecord extends ActivityRecordKey {
    private Integer activityPacketId;

    private Date createAt;

    public ActivityRecord(Integer activityId, String openid, Integer activityPacketId, Date createAt) {
        super(activityId, openid);
        this.activityPacketId = activityPacketId;
        this.createAt = createAt;
    }

    public ActivityRecord() {
        super();
    }

    public Integer getActivityPacketId() {
        return activityPacketId;
    }

    public void setActivityPacketId(Integer activityPacketId) {
        this.activityPacketId = activityPacketId;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }
}