package com.javadevmap.springBasic.xml;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.javadevmap.bean.Product;

/**
 * 单元测试  通过属性赋值
 * @author Administrator
 *
 */
public class TestIocCase03property {
	ApplicationContext ctx;
	@Test
	public void testCase() {
		 //容器
        ctx=new ClassPathXmlApplicationContext("spring-bean-property.xml");
        //从容器中获得id为Product的bean
        Product pro=(Product)ctx.getBean("beanProperty");
		System.out.println("product = "+pro);
	}
}
