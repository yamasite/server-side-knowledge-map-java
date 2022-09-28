package com.javadevmap.springBasic.anno;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.javadevmap.service.ProductServiceBoth;

/**
 * 单元测试 自动装配
 * 
 * @author Administrator
 *
 */
public class TestAnnoCase04Qualifier {
	ApplicationContext ctx;
	ProductServiceBoth productServiceTwo;

	@Test
	public void testCase() {
		// 容器
		ctx = new ClassPathXmlApplicationContext("spring-bean-scan.xml");
		// 从容器中获得bean
		productServiceTwo = (ProductServiceBoth) ctx.getBean(ProductServiceBoth.class);
		String name = "spring 编程思想";
		productServiceTwo.addProduct("0040-1", name);
	}

	@Test
	public void testCase2() {
		// 容器
		ctx = new ClassPathXmlApplicationContext("spring-bean-scan.xml");
		// 从容器中获得bean
		productServiceTwo = (ProductServiceBoth) ctx.getBean(ProductServiceBoth.class);
		String name = "java 编程思想";
		productServiceTwo.addProduct("0040-2", name);

	}

}
