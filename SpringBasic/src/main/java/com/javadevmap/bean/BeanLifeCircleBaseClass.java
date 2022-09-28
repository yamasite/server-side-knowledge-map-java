package com.javadevmap.bean;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class BeanLifeCircleBaseClass implements InitializingBean, DisposableBean {
	private String message;

	public void setMessage(String message) {
		this.message = message;
	}
	public void getMessage(String msg) {
		System.out.println("getMessage : "+msg);
	}
	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("BeanLifeCircleBaseClass is going exec init.");
	}
	@Override
	public void destroy() throws Exception {
		System.out.println("BeanLifeCircleBaseClass will destroy now.");
	}
}