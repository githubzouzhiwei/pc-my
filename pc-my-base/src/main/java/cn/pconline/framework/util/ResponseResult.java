package cn.pconline.framework.util;

import cn.pconline.framework.enums.ResponseCodeEnum;

/**
 * web专用返回
 * @author Orange
 * @date 2018/10/28
 */
public class ResponseResult<T> {

    private int code;

    private String msg;

    private T data;
    
    /****列表数据分页****/
    private Integer pageNo;
    
    private Integer pageSize;
    
    private Integer total; 

    private long currentTime = System.currentTimeMillis();

    private ResponseResult(ResponseCodeEnum responseCodeEnum) {
        this.code = responseCodeEnum.getCode();
        this.msg = responseCodeEnum.getDesc();
    }

    private ResponseResult(ResponseCodeEnum responseCodeEnum,T data) {
        this.code = responseCodeEnum.getCode();
        this.msg = responseCodeEnum.getDesc();
        this.data = data;
    }

    private ResponseResult(ResponseCodeEnum responseCodeEnum,String msg) {
        this.code = responseCodeEnum.getCode();
        this.msg = msg;
    }

    private ResponseResult(ResponseCodeEnum responseCodeEnum,String msg,T data) {
        this.code = responseCodeEnum.getCode();
        this.msg = msg;
        this.data = data;
    }
    
    private ResponseResult(ResponseCodeEnum responseCodeEnum,T data, int pageNo, int pageSize, int total) {
    	this.code = responseCodeEnum.getCode();
    	this.data = data;
    	this.pageNo = pageNo;
    	this.pageSize = pageSize;
    	this.total = total;
    }

    public static ResponseResult success() {
        return new ResponseResult(ResponseCodeEnum.SUCCESS);
    }
    public static ResponseResult error() {
        return new ResponseResult(ResponseCodeEnum.ERROR);
    }
    public static ResponseResult build(ResponseCodeEnum responseCodeEnum) {
        return new ResponseResult(responseCodeEnum);
    }
    public static ResponseResult build(String msg,ResponseCodeEnum responseCodeEnum) {
        return new ResponseResult(responseCodeEnum,msg);
    }
    public static<T> ResponseResult<T> build(ResponseCodeEnum responseCodeEnum,T data) {
        return new ResponseResult(responseCodeEnum,data);
    }
    public static<T> ResponseResult<T> build(ResponseCodeEnum responseCodeEnum,String msg,T data) {
        return new ResponseResult(responseCodeEnum,msg,data);
    }
    
    public static<T> ResponseResult<T> build(ResponseCodeEnum responseCodeEnum,T data,int pageNo,int pageSize,int total) {
    	return new ResponseResult(responseCodeEnum,data,pageNo,pageSize,total);
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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public long getCurrentTime() {
        return currentTime;
    }

}
