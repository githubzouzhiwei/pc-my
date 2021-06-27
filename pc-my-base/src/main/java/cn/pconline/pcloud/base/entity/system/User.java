package cn.pconline.pcloud.base.entity.system;

import java.util.Date;

import org.springframework.data.annotation.Id;

public class User {
	@Id
    private Long userId;

    private String account;
    
    private String pwd;

    private String name;

    private String department;

    private Integer isAdmin;

    private Integer isLock;

    private Date createAt;

    private Date updateAt;

    public User(Long userId, String account, String pwd, String name, String department, Integer isAdmin, Integer isLock, Date createAt, Date updateAt) {
        this.userId = userId;
        this.account = account;
        this.pwd = pwd;
        this.name = name;
        this.department = department;
        this.isAdmin = isAdmin;
        this.isLock = isLock;
        this.createAt = createAt;
        this.updateAt = updateAt;
    }

    public User() {
        super();
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }
    
    public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department == null ? null : department.trim();
    }

    public Integer getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Integer isAdmin) {
        this.isAdmin = isAdmin;
    }

    public Integer getIsLock() {
        return isLock;
    }

    public void setIsLock(Integer isLock) {
        this.isLock = isLock;
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
}