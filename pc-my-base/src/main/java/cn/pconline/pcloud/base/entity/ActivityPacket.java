package cn.pconline.pcloud.base.entity;

import java.util.Date;

public class ActivityPacket {
    private Long activityPacketId;

    private Long activityId;

    private Integer packetAmount;

    private Byte status;

    private String openid;

    private Date drawAt;

    public ActivityPacket(Long activityPacketId, Long activityId, Integer packetAmount, Byte status, String openid, Date drawAt) {
        this.activityPacketId = activityPacketId;
        this.activityId = activityId;
        this.packetAmount = packetAmount;
        this.status = status;
        this.openid = openid;
        this.drawAt = drawAt;
    }

    public ActivityPacket() {
        super();
    }

    public Long getActivityPacketId() {
        return activityPacketId;
    }

    public void setActivityPacketId(Long activityPacketId) {
        this.activityPacketId = activityPacketId;
    }

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    public Integer getPacketAmount() {
        return packetAmount;
    }

    public void setPacketAmount(Integer packetAmount) {
        this.packetAmount = packetAmount;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid == null ? null : openid.trim();
    }

    public Date getDrawAt() {
        return drawAt;
    }

    public void setDrawAt(Date drawAt) {
        this.drawAt = drawAt;
    }
}