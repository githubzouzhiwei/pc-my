package cn.pconline.pcloud.base.entity;

import java.util.Date;

public class UserOpen {
    private Long id;

    private String openid;

    private String name;

    private String mobile;

    private Integer status;

    private Date createAt;

    private Date updateAt;

    private String miniOpenid;
    
    private String unionid;

    public UserOpen(Long id, String openid, String name, String mobile, Integer status, Date createAt, Date updateAt, String miniOpenid, String unionid) {
        this.id = id;
        this.openid = openid;
        this.name = name;
        this.mobile = mobile;
        this.status = status;
        this.createAt = createAt;
        this.updateAt = updateAt;
        this.miniOpenid = miniOpenid;
        this.unionid = unionid;
    }

    public UserOpen() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid == null ? null : openid.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

	public String getMiniOpenid() {
		return miniOpenid;
	}

	public void setMiniOpenid(String miniOpenid) {
		this.miniOpenid = miniOpenid;
	}

	public String getUnionid() {
		return unionid;
	}

	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}
    
}