package com.javadevmap.model;

import java.io.Serializable;

public class UserModel implements Serializable{
	private static final long serialVersionUID = 1L;
	public String name;
	public int age;
	public String address;
	
	public UserModel(String name,int age,String address) {
		this.name = name;
		this.age = age;
		this.address = address;
	}
	@Override
	public String toString() {
		return "name = " + name + " age = " + age + " address = " + address;
	}
}
