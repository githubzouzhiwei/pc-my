package cn.pconline.pcloud.base.form;

import cn.pconline.pcloud.base.entity.UserOpen;

@SuppressWarnings("serial")
public class UserOpenPageForm extends XAdminPageBase<UserOpen> {
	
	protected UserOpenPageForm() {
		super(UserOpen.class);
	}
	
	private String name;
	private String createAts;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCreateAts() {
		return createAts;
	}

	public void setCreateAts(String createAts) {
		this.createAts = createAts;
	}
	
}
