package com.javadevmap.bean;
import org.springframework.beans.factory.InitializingBean;
public class ProductWithInitializingBean implements InitializingBean  {

	private int id;
	private String name;
	public ProductWithInitializingBean() {
	}
	public ProductWithInitializingBean(int id, String name) {
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
	

	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("execute afterPropertiesSet()");	
	}
	
	
}
