package cn.pconline.pcloud.base.enums;

/**
 * 活动状态
 */
public enum ActivityStatus {

    DELETE(-1, "删除"),
    CLOSE(0, "关闭"),
    OPEN(1, "开启");

    private int status;
    private String desc;

    ActivityStatus(int status, String desc) {
        this.status = status;
        this.desc = desc;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
