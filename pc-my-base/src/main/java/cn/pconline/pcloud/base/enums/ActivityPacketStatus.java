package cn.pconline.pcloud.base.enums;

public enum ActivityPacketStatus {

    WAIT_DRAW(0, "待领取"),
    HAD_DRAW(1, "已领取");

    private int status;
    private String desc;

    ActivityPacketStatus(int status, String desc) {
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
