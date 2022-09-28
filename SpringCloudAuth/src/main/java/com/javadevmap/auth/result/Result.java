package com.javadevmap.auth.result;

public class Result<T> {
	
	private int resultCode;
	private String msg;
	private T data;
	
	public Result() {
		
	}
	
	public Result(int code,String msg) {
		this.resultCode = code;
		this.msg = msg;
	}
	
	public Result(int code,String msg,T data) {
		this.resultCode = code;
		this.msg = msg;
		this.data = data;
	}
	
	public Result(ResultCode rCode) {
		switch (rCode) {
		case OK:
			this.resultCode = 200;
			this.msg = "ok";
			break;
		case Bad_Request:
			this.resultCode = 400;
			this.msg = "Bad Request";
			break;
		case Unauthorized:
			this.resultCode = 401;
			this.msg = "Unauthorized";
			break;
		case Not_Found:
			this.resultCode = 404;
			this.msg = "Not Found";
			break;
		case ERROR:
			this.resultCode = 500;
			this.msg = "Server Error";
			break;
		case Unavailable:
			this.resultCode = 503;
			this.msg = "Service Unavailable";
			break;
		default:
			this.resultCode = 400;
			this.msg = "Bad Request";
			break;
		}
	}
	
	public Result(ResultCode rCode,T data) {
		this(rCode);
		this.data = data;
	}
		
	public int getResultCode() {
		return resultCode;
	}
	public void setResultCode(int resultCode) {
		this.resultCode = resultCode;
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
}


