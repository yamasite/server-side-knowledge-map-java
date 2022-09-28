package com.javadevmap.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javadevmap.dao.IProductDao;
/**
 * 商品业务类  自动装配
 * 
 * @author javadevmap
 *
 */
@Service
public class ProductService {
	@Autowired
	IProductDao productDaoImpl;
	
	public void addProduct(String id,String name) {
		System.out.println("execute addProduct method()");
		String result= productDaoImpl.addProduct(id, name);
		System.out.println(result);
		
	}
	
	public void doThrowException() {
		System.out.println("Exception raised");
		throw new IllegalArgumentException();
	}
}
