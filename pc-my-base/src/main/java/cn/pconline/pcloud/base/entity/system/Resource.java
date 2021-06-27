package cn.pconline.pcloud.base.entity.system;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;

public class Resource {
	@Id
    private Long resourceId;

    private Long parentId;

    private Integer type;

    private String name;

    private String sourceKey;

    private String sourceUrl;

    private Integer sort;

    private String icon;

    private String description;

    private Integer isHide;

    private Date createAt;

    private Date updateAt;

    public Resource(Long resourceId, Long parentId, Integer type, String name, String sourceKey, String sourceUrl, Integer sort, String icon, String description, Integer isHide, Date createAt, Date updateAt) {
        this.resourceId = resourceId;
        this.parentId = parentId;
        this.type = type;
        this.name = name;
        this.sourceKey = sourceKey;
        this.sourceUrl = sourceUrl;
        this.sort = sort;
        this.icon = icon;
        this.description = description;
        this.isHide = isHide;
        this.createAt = createAt;
        this.updateAt = updateAt;
    }

    public Resource() {
        super();
    }

    public Long getResourceId() {
        return resourceId;
    }

    public void setResourceId(Long resourceId) {
        this.resourceId = resourceId;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getSourceKey() {
        return sourceKey;
    }

    public void setSourceKey(String sourceKey) {
        this.sourceKey = sourceKey == null ? null : sourceKey.trim();
    }

    public String getSourceUrl() {
        return sourceUrl;
    }

    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl == null ? null : sourceUrl.trim();
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Integer getIsHide() {
        return isHide;
    }

    public void setIsHide(Integer isHide) {
        this.isHide = isHide;
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
    
    /***************** 扩展属性 *******************/
    /**
     * 资源子集
     */
    private List<Resource> ChildResourceList;

	public List<Resource> getChildResourceList() {
		return ChildResourceList;
	}

	public void setChildResourceList(List<Resource> childResourceList) {
		ChildResourceList = childResourceList;
	}
    
}