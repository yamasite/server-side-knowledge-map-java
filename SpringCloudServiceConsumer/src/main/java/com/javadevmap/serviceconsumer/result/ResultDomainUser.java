package com.javadevmap.serviceconsumer.result;

import com.javadevmap.serviceconsumer.domain.DomainUser;

public class ResultDomainUser {
	private int resultCode;
	private String msg;
	private DomainUser data;
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
	public DomainUser getData() {
		return data;
	}
	public void setData(DomainUser data) {
		this.data = data;
	}
}
