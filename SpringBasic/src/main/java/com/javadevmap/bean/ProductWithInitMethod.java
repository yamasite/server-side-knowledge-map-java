package com.javadevmap.bean;
public class ProductWithInitMethod  {

	private int id;
	private String name;
	public ProductWithInitMethod() {
	}
	public ProductWithInitMethod(int id, String name) {
		System.out.println("invoke method -- Product(int id, String name)");
		this.id = id;
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		System.out.println("invoke method -- setId");
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		System.out.println("invoke method -- setName");
		this.name = name;
	}

	public void initMethod()  {
		System.out.println("execute initMethod()");	
	}
	
}
