package cn.pconline.pcloud.base.entity;

public class ActivityRecordKey {
    private Integer activityId;

    private String openid;

    public ActivityRecordKey(Integer activityId, String openid) {
        this.activityId = activityId;
        this.openid = openid;
    }

    public ActivityRecordKey() {
        super();
    }

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid == null ? null : openid.trim();
    }
}