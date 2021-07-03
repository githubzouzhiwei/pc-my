package cn.pconline.pcloud.base.entity;

public class ActivityOpenidKey {
    private Integer activityId;

    private String openid;

    public ActivityOpenidKey(Integer activityId, String openid) {
        this.activityId = activityId;
        this.openid = openid;
    }

    public ActivityOpenidKey() {
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