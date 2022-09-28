package com.javadevmap.springBasic.xml;

import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.javadevmap.bean.ProductWithInitMethod;
import com.javadevmap.bean.ProductWithInitializingBean;

/**
 * 单元测试
 * @author Administrator
 *
 */
public class TestIocCaseLifeCircleWithInitMethod {
	AbstractApplicationContext  ctx;
	ProductWithInitMethod bean;
	@Test
	public void testCase() {
		 //容器
		ctx=new ClassPathXmlApplicationContext("spring-bean-initMethod.xml");
		System.out.println("execute one");
		bean =(ProductWithInitMethod) ctx.getBean("beanId");
		System.out.println("execute two");
		bean =(ProductWithInitMethod) ctx.getBean("beanId");
	}
}
