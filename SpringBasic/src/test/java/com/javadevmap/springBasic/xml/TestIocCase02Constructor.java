package com.javadevmap.springBasic.xml;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.javadevmap.bean.Product;
import com.javadevmap.dao.IProductDao;

/**
 * 单元测试    在xml中使用有参构造方法创建对象
 * @author Administrator
 *
 */
public class TestIocCase02Constructor {
	ApplicationContext ctx;
	@Test
	public void testCase() {
		 //容器
        ctx=new ClassPathXmlApplicationContext("spring-bean-constructor.xml");
        //从容器中获得bean
        Product product=(Product)ctx.getBean("beanNoConstructorArg");
		System.out.println("beanNoConstructorArg = "+product);
		Product productCon=(Product)ctx.getBean("beanHasConstructorArg");
		System.out.println("beanHasConstructorArg = "+productCon);
	}
}
