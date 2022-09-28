package com.javadevmap.springBasic.xml;

import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.javadevmap.bean.ProductWithInitializingBean;

/**
 * 单元测试
 * @author Administrator
 *
 */
public class TestIocCaseLifeCircleWithInterface {
	AbstractApplicationContext  ctx;
	ProductWithInitializingBean bean;
	@Test
	public void testCase() {
		 //容器
		ctx=new ClassPathXmlApplicationContext("spring-bean-initializingbean.xml");
		System.out.println("execute one");
		bean =(ProductWithInitializingBean) ctx.getBean("beanId");
		System.out.println("execute two");
		bean =(ProductWithInitializingBean) ctx.getBean("beanId");
	}
}
