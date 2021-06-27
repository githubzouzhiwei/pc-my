package cn.pconline.pcloud.base.form;

import cn.pconline.pcloud.base.entity.system.OperateLog;

@SuppressWarnings("serial")
public class OperateLogPageForm extends XAdminPageBase<OperateLog> {

	protected OperateLogPageForm() {
		super(OperateLog.class);
	}
	
	private String module;
	private String createAts;

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public String getCreateAts() {
		return createAts;
	}

	public void setCreateAts(String createAts) {
		this.createAts = createAts;
	}
	
}
