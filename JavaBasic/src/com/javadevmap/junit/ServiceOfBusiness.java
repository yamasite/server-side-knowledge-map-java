package com.javadevmap.junit;


public class ServiceOfBusiness {

	private String serverCode;
	private boolean isTrueFlag;

	public ServiceOfBusiness() {
		super();
	}

	// Constructor
	public ServiceOfBusiness(String code, boolean isTrue) {
		this.serverCode = code;
		this.isTrueFlag = isTrue;
	}
	// prints the Flag
	public boolean printFlag() {
		System.out.println(isTrueFlag);
		return isTrueFlag;
	}

	// prints the serverCode
	public String printServerCode() {
		System.out.println(serverCode);
		return serverCode;
	}
}