package cn.pconline.pcloud.base.entity;

import org.springframework.data.annotation.Id;

import java.util.Date;

public class Activity {

    @Id
    private Long activityId;

    private String name;

    private Date startTime;

    private Date endTime;

    private Byte status;

    private String contactNumber;

    private String applyUrl;

    private String applyTitle;

    private String applyButtonUrl;

    private String applyLogoUrl;

    private Integer funds;

    private Integer packetNum;

    private Byte packetType;

    private Integer packetAmount;

    private Integer minPacketAmount;

    private Integer maxPacketAmount;

    private Integer remainFunds;

    private Byte remainFundsAllocType;

    private Integer remainPacketNum;

    private Byte isSetStayTime;

    private Integer stayTime;

    private Byte isSetJoinArea;

    private String joinArea;

    private Byte isNeedFollow;

    private Date createAt;

    private String createBy;

    private Date updateAt;

    private String updateBy;

    public Activity(Long activityId, String name, Date startTime, Date endTime, Byte status, String contactNumber, String applyUrl, String applyTitle, String applyButtonUrl, String applyLogoUrl, Integer funds, Integer packetNum, Byte packetType, Integer packetAmount, Integer minPacketAmount, Integer maxPacketAmount, Integer remainFunds, Byte remainFundsAllocType, Integer remainPacketNum, Byte isSetStayTime, Integer stayTime, Byte isSetJoinArea, String joinArea, Byte isNeedFollow, Date createAt, String createBy, Date updateAt, String updateBy) {
        this.activityId = activityId;
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
        this.contactNumber = contactNumber;
        this.applyUrl = applyUrl;
        this.applyTitle = applyTitle;
        this.applyButtonUrl = applyButtonUrl;
        this.applyLogoUrl = applyLogoUrl;
        this.funds = funds;
        this.packetNum = packetNum;
        this.packetType = packetType;
        this.packetAmount = packetAmount;
        this.minPacketAmount = minPacketAmount;
        this.maxPacketAmount = maxPacketAmount;
        this.remainFunds = remainFunds;
        this.remainFundsAllocType = remainFundsAllocType;
        this.remainPacketNum = remainPacketNum;
        this.isSetStayTime = isSetStayTime;
        this.stayTime = stayTime;
        this.isSetJoinArea = isSetJoinArea;
        this.joinArea = joinArea;
        this.isNeedFollow = isNeedFollow;
        this.createAt = createAt;
        this.createBy = createBy;
        this.updateAt = updateAt;
        this.updateBy = updateBy;
    }

    public Activity() {
        super();
    }

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
        this.name = name == null ? null : name.trim();
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

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber == null ? null : contactNumber.trim();
    }

    public String getApplyUrl() {
        return applyUrl;
    }

    public void setApplyUrl(String applyUrl) {
        this.applyUrl = applyUrl == null ? null : applyUrl.trim();
    }

    public String getApplyTitle() {
        return applyTitle;
    }

    public void setApplyTitle(String applyTitle) {
        this.applyTitle = applyTitle == null ? null : applyTitle.trim();
    }

    public String getApplyButtonUrl() {
        return applyButtonUrl;
    }

    public void setApplyButtonUrl(String applyButtonUrl) {
        this.applyButtonUrl = applyButtonUrl == null ? null : applyButtonUrl.trim();
    }

    public String getApplyLogoUrl() {
        return applyLogoUrl;
    }

    public void setApplyLogoUrl(String applyLogoUrl) {
        this.applyLogoUrl = applyLogoUrl == null ? null : applyLogoUrl.trim();
    }

    public Integer getFunds() {
        return funds;
    }

    public void setFunds(Integer funds) {
        this.funds = funds;
    }

    public Integer getPacketNum() {
        return packetNum;
    }

    public void setPacketNum(Integer packetNum) {
        this.packetNum = packetNum;
    }

    public Byte getPacketType() {
        return packetType;
    }

    public void setPacketType(Byte packetType) {
        this.packetType = packetType;
    }

    public Integer getPacketAmount() {
        return packetAmount;
    }

    public void setPacketAmount(Integer packetAmount) {
        this.packetAmount = packetAmount;
    }

    public Integer getMinPacketAmount() {
        return minPacketAmount;
    }

    public void setMinPacketAmount(Integer minPacketAmount) {
        this.minPacketAmount = minPacketAmount;
    }

    public Integer getMaxPacketAmount() {
        return maxPacketAmount;
    }

    public void setMaxPacketAmount(Integer maxPacketAmount) {
        this.maxPacketAmount = maxPacketAmount;
    }

    public Integer getRemainFunds() {
        return remainFunds;
    }

    public void setRemainFunds(Integer remainFunds) {
        this.remainFunds = remainFunds;
    }

    public Byte getRemainFundsAllocType() {
        return remainFundsAllocType;
    }

    public void setRemainFundsAllocType(Byte remainFundsAllocType) {
        this.remainFundsAllocType = remainFundsAllocType;
    }

    public Integer getRemainPacketNum() {
        return remainPacketNum;
    }

    public void setRemainPacketNum(Integer remainPacketNum) {
        this.remainPacketNum = remainPacketNum;
    }

    public Byte getIsSetStayTime() {
        return isSetStayTime;
    }

    public void setIsSetStayTime(Byte isSetStayTime) {
        this.isSetStayTime = isSetStayTime;
    }

    public Integer getStayTime() {
        return stayTime;
    }

    public void setStayTime(Integer stayTime) {
        this.stayTime = stayTime;
    }

    public Byte getIsSetJoinArea() {
        return isSetJoinArea;
    }

    public void setIsSetJoinArea(Byte isSetJoinArea) {
        this.isSetJoinArea = isSetJoinArea;
    }

    public String getJoinArea() {
        return joinArea;
    }

    public void setJoinArea(String joinArea) {
        this.joinArea = joinArea == null ? null : joinArea.trim();
    }

    public Byte getIsNeedFollow() {
        return isNeedFollow;
    }

    public void setIsNeedFollow(Byte isNeedFollow) {
        this.isNeedFollow = isNeedFollow;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy == null ? null : updateBy.trim();
    }
}