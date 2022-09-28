package com.javadevmap.springBasic.anno;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.javadevmap.service.ProductService;


/**
 * 单元测试  自动装配
 * @author Administrator
 *
 */
public class TestAnnoCase03Auto {

	ProductService orderService;
	
	@Test
	public void testCase() {
		 //容器
        ApplicationContext ctx=new ClassPathXmlApplicationContext("spring-bean-scan.xml");
        //从容器中获得bean
        orderService=(ProductService)ctx.getBean(ProductService.class);
        String name = "spring basic book";
		orderService.addProduct("003", name);
        
	}
	
}
