package cn.pconline.pcloud.base.entity;

import org.springframework.data.annotation.Id;

public class ActivityPacket {

    @Id
    private Long activityPacketId;

    private Long activityId;

    private Integer packetAmount;

    private Byte status;

    public ActivityPacket(Long activityPacketId, Long activityId, Integer packetAmount, Byte status) {
        this.activityPacketId = activityPacketId;
        this.activityId = activityId;
        this.packetAmount = packetAmount;
        this.status = status;
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
}