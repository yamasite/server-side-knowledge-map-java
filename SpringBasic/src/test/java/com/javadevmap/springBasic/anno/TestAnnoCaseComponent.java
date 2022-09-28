package com.javadevmap.springBasic.anno;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.javadevmap.dao.IProductDao;


/**
 * 单元测试
 * @author Administrator
 *
 */
public class TestAnnoCaseComponent {

	IProductDao productDao;
	ApplicationContext ctx;
	@Test
	public void testCase() {
		 //容器
        ctx=new ClassPathXmlApplicationContext("spring-bean-scan.xml");
        productDao=(IProductDao)ctx.getBean("productDaoImpl");
        System.out.println("productDao is "+productDao);
		String result = productDao.addProduct("2", "javaDevmap anno");
		System.out.println(result);
	}
	
}
