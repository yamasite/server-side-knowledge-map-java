package com.javadevmap.springBasic.xml;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.javadevmap.bean.Product;

/**
 * 单元测试
 * @author Administrator
 *
 */
public class TestIocCaseStart {
	ApplicationContext ctx;
	@Test
	public void testCase() {
		 //容器
        ctx=new ClassPathXmlApplicationContext("spring-bean.xml");
        Product product=(Product)ctx.getBean("beanId");
		System.out.println("ApplicationContext.getBean() = "+product);
	}
}
