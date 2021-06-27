package cn.pconline.pcloud.base.entity;

import org.springframework.data.annotation.Id;

public class ActivityPacketConfig {

    @Id
    private Long activityPacketConfigId;

    private Long activityId;

    private Integer packetAmount;

    private Integer packetNum;

    public ActivityPacketConfig(Long activityPacketConfigId, Long activityId, Integer packetAmount, Integer packetNum) {
        this.activityPacketConfigId = activityPacketConfigId;
        this.activityId = activityId;
        this.packetAmount = packetAmount;
        this.packetNum = packetNum;
    }

    public ActivityPacketConfig() {
        super();
    }

    public Long getActivityPacketConfigId() {
        return activityPacketConfigId;
    }

    public void setActivityPacketConfigId(Long activityPacketConfigId) {
        this.activityPacketConfigId = activityPacketConfigId;
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

    public Integer getPacketNum() {
        return packetNum;
    }

    public void setPacketNum(Integer packetNum) {
        this.packetNum = packetNum;
    }
}