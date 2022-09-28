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
public class TestIocCaseStartSingleton {
	ApplicationContext ctx;
	@Test
	public void testCase() {
		 //容器
        ctx=new ClassPathXmlApplicationContext("spring-bean.xml");
        //从容器中获得id为beanId的bean
        Product product=(Product)ctx.getBean("beanId");
        Product product2=(Product)ctx.getBean("beanId");
        System.out.println("product==product2 is "+(product==product2));
	}
}
