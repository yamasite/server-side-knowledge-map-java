package com.javadevmap.springBasic.aop;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.javadevmap.service.ProductService;

public class TestAopCaseAspectJ {
	ApplicationContext ctx;
	ProductService productService; 
	@Test
	public void testCase() {
		// 容器
		ctx = new ClassPathXmlApplicationContext("spring-bean-aspectj.xml");
		productService=(ProductService)ctx.getBean(ProductService.class);
        productService.addProduct("4001", "java dev map");
//        productService.doThrowException(); 
	}

}
