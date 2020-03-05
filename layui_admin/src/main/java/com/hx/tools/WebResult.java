package com.hx.tools;

public class WebResult {
	/**
	 * 响应吗 状态码
	 * 0 成功
	 * 1 失败
	 */
	private Integer status;
	/**
	 * 相应消息
	 */
	private String msg;
	/**
	 * 携带数据
	 */
	private Object data;
	
	
	public WebResult() {
		
	}
	
	public WebResult( String msg, Object data) {
		super();
		this.status = 1;
		this.msg = msg;
		this.data = data;
	}
	
	public WebResult(Integer status, String msg, Object data) {
		super();
		this.status = status;
		this.msg = msg;
		this.data = data;
	}
	
	public static WebResult success(String msg, Object data){
		return new WebResult(0,msg,data);
	}
	public static WebResult error(String msg, Object data){
		return new WebResult(1,msg,data);
	}
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
	

}
