package cn.pconline.framework.util;

import java.util.List;

public class PagerResult<E> {
	private int pageNo = 1;
	private int pageSize;
	private int totalRecord;
	private int totalPage;
	private List<E> rsList;

	public PagerResult(int pageNo, int pageSize, int totalRecord, List<E> rsList) {
		this.pageNo = pageNo;
		this.pageSize = pageSize;
		this.totalRecord = totalRecord;
		this.rsList = rsList;
	}
	
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public long getTotalRecord() {
		return totalRecord;
	}
	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}
	public int getTotalPage() {
		if(totalRecord > 0 && totalPage == 0) {
			totalPage = (totalRecord - 1)/pageSize + 1;
		}
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public List<E> getRsList() {
		return rsList;
	}
	public void setRsList(List<E> rsList) {
		this.rsList = rsList;
	}
}
