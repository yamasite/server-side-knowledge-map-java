package com.javadevmap.springBasic.xml;

import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.javadevmap.bean.ProductWithDestroyMethod;
import com.javadevmap.bean.ProductWithInitMethod;
import com.javadevmap.bean.ProductWithInitializingBean;

/**
 * 单元测试
 * @author Administrator
 *
 */
public class TestIocCaseLifeCircleWithDestroyMethod {
	AbstractApplicationContext  ctx;
	ProductWithDestroyMethod bean;
	@Test
	public void testCase() {
		 //容器
		ctx=new ClassPathXmlApplicationContext("spring-bean-destroyMethod.xml");
		System.out.println("execute one");
		bean =(ProductWithDestroyMethod) ctx.getBean("beanId");
		System.out.println("execute two");
		bean =(ProductWithDestroyMethod) ctx.getBean("beanId");
		ctx.registerShutdownHook();
	}
}
