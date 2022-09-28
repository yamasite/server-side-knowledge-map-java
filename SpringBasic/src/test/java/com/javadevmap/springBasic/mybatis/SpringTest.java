package com.javadevmap.springBasic.mybatis;

import com.javadevmap.mybatis.dao.ProductBeanMapper;
import com.javadevmap.mybatis.domain.ProductBean;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-mybatis-base.xml", "classpath:spring-mybatis.xml" })
public class SpringTest {

	// 注入
	@Autowired
	private ProductBeanMapper productBeanMapper;

	@Test
	public void insert() {
		ProductBean record=new ProductBean();
		record.setPrice(99);
		record.setProductName("java dev map");
		record.setProductDesc("产品描述，产品描述");
		int affectsNums = productBeanMapper.insert(record);
		System.out.println("insert affects row num is "+affectsNums);
	}
	@Test
	public void selectByPrimaryKey() {
		int id=113;
		ProductBean bean = productBeanMapper.selectByPrimaryKey(id);
		System.out.println(bean.getId() +"  ");
		System.out.println(bean.getProductName() +"  ");
		System.out.println(bean.getProductDesc() +"  ");
		System.out.println(bean.getPrice() +"  ");
	}
	@Test
	public void updateByPrimaryKeySelective() {
		ProductBean record=new ProductBean();
		record.setId(113);
		record.setPrice(80);
		record.setProductName("java dev map -update");
		int affectsNums = productBeanMapper.updateByPrimaryKey(record);
		System.out.println("updateByPrimaryKeySelective affects row num is "+affectsNums);
	}
	@Test
	public void deleteByPrimaryKey() {
		int affectsNums = productBeanMapper.deleteByPrimaryKey(1);
		System.out.println("deleteByPrimaryKey affects row num is "+affectsNums);
	}

}