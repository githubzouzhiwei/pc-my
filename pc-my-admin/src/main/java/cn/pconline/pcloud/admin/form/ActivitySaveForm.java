package cn.pconline.pcloud.admin.form;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

public class ActivitySaveForm {

    private Long activityId;
    private String name;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;
    private String contactNumber;
    private String applyUrl;
    private String applyTitle;
    private String applyButtonUrl;
    private String applyLogoUrl;
    private Double funds;
    private Integer packetNum;
    private Integer packetType;
    private Double packetAmount;
    private Double minPacketAmount;
    private Double maxPacketAmount;
    private Double remainFunds;
    private Integer remainPacketNum;
    private Integer remainFundsAllocType;
    private Integer isSetStayTime;
    private Integer stayTime;
    private Integer isSetJoinArea;
    private String joinArea;
    private Integer isNeedFollow;
    private List<Double> configPacketAmounts;
    private List<Integer> configPacketNums;


    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getApplyUrl() {
        return applyUrl;
    }

    public void setApplyUrl(String applyUrl) {
        this.applyUrl = applyUrl;
    }

    public String getApplyTitle() {
        return applyTitle;
    }

    public void setApplyTitle(String applyTitle) {
        this.applyTitle = applyTitle;
    }

    public String getApplyButtonUrl() {
        return applyButtonUrl;
    }

    public void setApplyButtonUrl(String applyButtonUrl) {
        this.applyButtonUrl = applyButtonUrl;
    }

    public String getApplyLogoUrl() {
        return applyLogoUrl;
    }

    public void setApplyLogoUrl(String applyLogoUrl) {
        this.applyLogoUrl = applyLogoUrl;
    }

    public Double getFunds() {
        return funds;
    }

    public void setFunds(Double funds) {
        this.funds = funds;
    }

    public Integer getPacketNum() {
        return packetNum;
    }

    public void setPacketNum(Integer packetNum) {
        this.packetNum = packetNum;
    }

    public Integer getPacketType() {
        return packetType;
    }

    public void setPacketType(Integer packetType) {
        this.packetType = packetType;
    }

    public Double getPacketAmount() {
        return packetAmount;
    }

    public void setPacketAmount(Double packetAmount) {
        this.packetAmount = packetAmount;
    }

    public Double getMinPacketAmount() {
        return minPacketAmount;
    }

    public void setMinPacketAmount(Double minPacketAmount) {
        this.minPacketAmount = minPacketAmount;
    }

    public Double getMaxPacketAmount() {
        return maxPacketAmount;
    }

    public void setMaxPacketAmount(Double maxPacketAmount) {
        this.maxPacketAmount = maxPacketAmount;
    }

    public Double getRemainFunds() {
        return remainFunds;
    }

    public void setRemainFunds(Double remainFunds) {
        this.remainFunds = remainFunds;
    }

    public Integer getRemainPacketNum() {
        return remainPacketNum;
    }

    public void setRemainPacketNum(Integer remainPacketNum) {
        this.remainPacketNum = remainPacketNum;
    }

    public Integer getRemainFundsAllocType() {
        return remainFundsAllocType;
    }

    public void setRemainFundsAllocType(Integer remainFundsAllocType) {
        this.remainFundsAllocType = remainFundsAllocType;
    }

    public Integer getIsSetStayTime() {
        return isSetStayTime;
    }

    public void setIsSetStayTime(Integer isSetStayTime) {
        this.isSetStayTime = isSetStayTime;
    }

    public Integer getStayTime() {
        return stayTime;
    }

    public void setStayTime(Integer stayTime) {
        this.stayTime = stayTime;
    }

    public Integer getIsSetJoinArea() {
        return isSetJoinArea;
    }

    public void setIsSetJoinArea(Integer isSetJoinArea) {
        this.isSetJoinArea = isSetJoinArea;
    }

    public String getJoinArea() {
        return joinArea;
    }

    public void setJoinArea(String joinArea) {
        this.joinArea = joinArea;
    }

    public Integer getIsNeedFollow() {
        return isNeedFollow;
    }

    public void setIsNeedFollow(Integer isNeedFollow) {
        this.isNeedFollow = isNeedFollow;
    }

    public List<Double> getConfigPacketAmounts() {
        return configPacketAmounts;
    }

    public void setConfigPacketAmounts(List<Double> configPacketAmounts) {
        this.configPacketAmounts = configPacketAmounts;
    }

    public List<Integer> getConfigPacketNums() {
        return configPacketNums;
    }

    public void setConfigPacketNums(List<Integer> configPacketNums) {
        this.configPacketNums = configPacketNums;
    }

}
