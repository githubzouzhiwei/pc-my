package cn.pconline.framework.util;

import java.util.List;

import cn.pconline.framework.enums.XAdminCodeEnum;

/**
 * web专用返回
 * 
 * @author Orange
 * @date 2018/10/28
 */
public class XAdmindResult {

	private int code;

	private String msg;

	private List<?> data;

	private long count;

	private XAdmindResult(XAdminCodeEnum xAdminCodeEnum) {
		this.code = xAdminCodeEnum.getCode();
		this.msg = xAdminCodeEnum.getDesc();
	}

	private XAdmindResult(XAdminCodeEnum xAdminCodeEnum, List<?> data, long count) {
		this.code = xAdminCodeEnum.getCode();
		this.msg = xAdminCodeEnum.getDesc();
		this.data = data;
		this.count = count;
	}

	private XAdmindResult(XAdminCodeEnum xAdminCodeEnum, String msg) {
		this.code = xAdminCodeEnum.getCode();
		this.msg = msg;
	}

	private XAdmindResult(XAdminCodeEnum xAdminCodeEnum, String msg, List<?> data, long count) {
		this.code = xAdminCodeEnum.getCode();
		this.msg = msg;
		this.data = data;
		this.count = count;
	}

	public static XAdmindResult success() {
		return new XAdmindResult(XAdminCodeEnum.SUCCESS);
	}

	public static XAdmindResult error() {
		return new XAdmindResult(XAdminCodeEnum.ERROR);
	}


	public static XAdmindResult build(XAdminCodeEnum xAdminCodeEnum) {
		return new XAdmindResult(xAdminCodeEnum);
	}

	public static XAdmindResult build(XAdminCodeEnum xAdminCodeEnum, List<?> data, long count) {
		return new XAdmindResult(xAdminCodeEnum, data, count);
	}

	public static XAdmindResult build(XAdminCodeEnum xAdminCodeEnum, String msg) {
		return new XAdmindResult(xAdminCodeEnum, msg);
	}

	public static XAdmindResult build(XAdminCodeEnum xAdminCodeEnum, String msg, List<?> data, long count) {
		return new XAdmindResult(xAdminCodeEnum, msg, data, count);
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public List<?> getData() {
		return data;
	}

	public void setData(List<?> data) {
		this.data = data;
	}

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

}
