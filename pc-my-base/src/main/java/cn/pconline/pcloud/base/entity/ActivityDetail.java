package cn.pconline.pcloud.base.entity;

import org.springframework.data.annotation.Id;

public class ActivityDetail {

    @Id
    private Long activityId;

    private String shareTitle;

    private String shareDesc;

    private String sharePicUrl;

    private Byte source;

    private String sourceInfo;

    private String content;

    public ActivityDetail(Long activityId, String shareTitle, String shareDesc, String sharePicUrl, Byte source, String sourceInfo, String content) {
        this.activityId = activityId;
        this.shareTitle = shareTitle;
        this.shareDesc = shareDesc;
        this.sharePicUrl = sharePicUrl;
        this.source = source;
        this.sourceInfo = sourceInfo;
        this.content = content;
    }

    public ActivityDetail() {
        super();
    }

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    public String getShareTitle() {
        return shareTitle;
    }

    public void setShareTitle(String shareTitle) {
        this.shareTitle = shareTitle == null ? null : shareTitle.trim();
    }

    public String getShareDesc() {
        return shareDesc;
    }

    public void setShareDesc(String shareDesc) {
        this.shareDesc = shareDesc == null ? null : shareDesc.trim();
    }

    public String getSharePicUrl() {
        return sharePicUrl;
    }

    public void setSharePicUrl(String sharePicUrl) {
        this.sharePicUrl = sharePicUrl == null ? null : sharePicUrl.trim();
    }

    public Byte getSource() {
        return source;
    }

    public void setSource(Byte source) {
        this.source = source;
    }

    public String getSourceInfo() {
        return sourceInfo;
    }

    public void setSourceInfo(String sourceInfo) {
        this.sourceInfo = sourceInfo == null ? null : sourceInfo.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}