package com.javadevmap.bean;
import org.springframework.beans.factory.DisposableBean;
public class ProductWithDisposableBean implements DisposableBean   {

	private int id;
	private String name;
	public ProductWithDisposableBean() {
	}
	public ProductWithDisposableBean(int id, String name) {
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
	public void destroy() throws Exception {
		System.out.println("execute DisposableBean destroy()");
	}
	
}
