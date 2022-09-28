package com.javadevmap.dao;

import org.springframework.stereotype.Repository;

@Repository("productDaoImpl")
//@Scope("prototype")
public class ProductDaoImpl implements IProductDao {

	public String addProduct(String id, String name) {
		String result=String.format("添加商品id=%s，商品名称为 %s，成功！", id,name);
		return result;
	}

}
