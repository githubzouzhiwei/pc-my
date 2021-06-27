package cn.pconline.pcloud.base.form;

import cn.pconline.pcloud.base.entity.system.Role;
import cn.pconline.pcloud.base.form.XAdminPageBase;

@SuppressWarnings("serial")
public class RolePageForm extends XAdminPageBase<Role> {

	protected RolePageForm() {
		super(Role.class);
	}

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
