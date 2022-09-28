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
public class TestIocCaseIoC {
	ApplicationContext ctx;
	@Test
	public void testCase() {
		 //容器
        ctx=new ClassPathXmlApplicationContext("spring-bean-ioc.xml");
        Product proClass=(Product)ctx.getBean(Product.class);
		System.out.println("不指定id，只配置必须的全限定类名= "+proClass);
		Product pro=(Product)ctx.getBean("beanId");
		System.out.println("指定id获取 ="+pro);
		pro=(Product)ctx.getBean("beanName");
		System.out.println("指定name 属性获取 = "+pro);
		pro=(Product)ctx.getBean("beanId01");
		System.out.println("指定id和name 获取= "+pro);
	}
}
