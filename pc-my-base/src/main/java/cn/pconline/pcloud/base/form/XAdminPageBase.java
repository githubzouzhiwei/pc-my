package cn.pconline.pcloud.base.form;

import java.io.Serializable;

/**
 * 所有的List请求的基类
 *
 * @author liaoqiqi
 * @version 2013-12-4
 */
public abstract class XAdminPageBase<T> implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 7999737352570996142L;

	/**
	 * type
	 */
	protected Class<T> type;

	/**
	 * 
	 * @param type
	 */
	protected XAdminPageBase(Class<T> type) {
		this.type = type;
	}

	private int page = 1;

	private int limit = 20;

	private String field = null;

	private String order = "desc";

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

}
