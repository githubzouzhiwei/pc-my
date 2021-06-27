package cn.pconline.pcloud.admin.form;

public class ActivitySaveDetailForm {

    private Long activityId;
    private String shareTitle;
    private String shareDesc;
    private String sharePicUrl;
    private String content;
    private Integer source;
    private String sourceInfo;

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
        this.shareTitle = shareTitle;
    }

    public String getShareDesc() {
        return shareDesc;
    }

    public void setShareDesc(String shareDesc) {
        this.shareDesc = shareDesc;
    }

    public String getSharePicUrl() {
        return sharePicUrl;
    }

    public void setSharePicUrl(String sharePicUrl) {
        this.sharePicUrl = sharePicUrl;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }

    public String getSourceInfo() {
        return sourceInfo;
    }

    public void setSourceInfo(String sourceInfo) {
        this.sourceInfo = sourceInfo;
    }

    @Override
    public String toString() {
        return "ActivitySaveDetailForm{" +
                "activityId=" + activityId +
                ", shareTitle='" + shareTitle + '\'' +
                ", shareDesc='" + shareDesc + '\'' +
                ", sharePicUrl='" + sharePicUrl + '\'' +
                ", content='" + content + '\'' +
                ", source=" + source +
                ", sourceInfo='" + sourceInfo + '\'' +
                '}';
    }
}
