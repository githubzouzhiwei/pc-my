package cn.pconline.pcloud.base.form;

import cn.pconline.pcloud.base.entity.system.Resource;

/**
 * 资源查询form
 * @author jzc
 *
 */
@SuppressWarnings("serial")
public class ResourcePageForm extends XAdminPageBase<Resource>  {
	
	protected ResourcePageForm() {
		super(Resource.class);
	}

	private String name;
	private Long parentId;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	
}
