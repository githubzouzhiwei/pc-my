package cn.pconline.pcloud.base.form;


import cn.pconline.pcloud.base.entity.system.User;

@SuppressWarnings("serial")
public class UserPageForm  extends XAdminPageBase<User> {

	protected UserPageForm() {
		super(User.class);
	}

	private String account;
	private String department;
	
	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}
	
}
