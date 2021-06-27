package cn.pconline.framework.enums;

public enum XAdminCodeEnum {

	SUCCESS(0, "成功"),
	ERROR(1, "错误"),
	EXIST(2, "数据已存在数据库中"),
	NOT_PERMIT(-1, "没有权限");

	private int code;
	private String desc;

	XAdminCodeEnum(int code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public int getCode() {
		return code;
	}
	
	public String getDesc() {
		return desc;
	}

}
