package com.javadevmap.bean;


/**
 * 在xml中使用有参构造方法创建对象
 *
 */
public class Product  {

	private int id;
	private String name;
	public Product() {
	}
	public Product(int id, String name) {
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
	
}
