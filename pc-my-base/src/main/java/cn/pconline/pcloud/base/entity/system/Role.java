package cn.pconline.pcloud.base.entity.system;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;

public class Role {
	@Id
    private Long roleId;

    private String name;

    private String roleKey;

    private String description;

    private Integer status;

    private Date createAt;

    private Date updateAt;

    public Role(Long roleId, String name, String roleKey, String description, Integer status, Date createAt, Date updateAt) {
        this.roleId = roleId;
        this.name = name;
        this.roleKey = roleKey;
        this.description = description;
        this.status = status;
        this.createAt = createAt;
        this.updateAt = updateAt;
    }

    public Role() {
        super();
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getRoleKey() {
        return roleKey;
    }

    public void setRoleKey(String roleKey) {
        this.roleKey = roleKey == null ? null : roleKey.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
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
    
    /********************** 扩展属性 **********************/
    /**
     * 关联资源集合
     */
    private List<Resource> relResourceList;

	public List<Resource> getRelResourceList() {
		return relResourceList;
	}

	public void setRelResourceList(List<Resource> relResourceList) {
		this.relResourceList = relResourceList;
	}
    
}