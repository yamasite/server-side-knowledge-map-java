package com.javadevmap.springBasic.xml;

import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.javadevmap.bean.ProductWithDisposableBean;
import com.javadevmap.bean.ProductWithInitializingBean;

/**
 * 单元测试
 * @author Administrator
 *
 */
public class TestIocCaseLifeCircleWithDisposableBean {
	AbstractApplicationContext  ctx;
	ProductWithDisposableBean bean;
	@Test
	public void testCase() {
		 //容器
		ctx=new ClassPathXmlApplicationContext("spring-bean-disposable.xml");
		System.out.println("execute one");
		bean =(ProductWithDisposableBean) ctx.getBean("beanId");
		System.out.println("execute two");
		bean =(ProductWithDisposableBean) ctx.getBean("beanId");
		ctx.registerShutdownHook();
	}
}
