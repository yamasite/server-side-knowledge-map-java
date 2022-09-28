package com.javadevmap.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.javadevmap.dao.IProductDao;
/**
 * 商品业务类  自动装配
 */
@Service
public class ProductServiceBoth {

	@Autowired
	@Qualifier("productDaoOne")
	IProductDao productDao01;
	
	@Autowired
	@Qualifier("productDaoTwo")
	IProductDao productDao02;
	
	public void addProduct(String id,String name) {
		System.out.println("execute addProduct method()");
		String result= productDao01.addProduct(id, name);
		System.out.println(result);
		result = productDao02.addProduct(id, name);
		System.out.println(result);
		
	}
}
