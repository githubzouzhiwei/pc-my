package cn.pconline.pcloud.base.form;

import cn.pconline.pcloud.base.entity.UserReport;

@SuppressWarnings("serial")
public class UserReportPageForm extends XAdminPageBase<UserReport> {

	protected UserReportPageForm() {
		super(UserReport.class);
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
