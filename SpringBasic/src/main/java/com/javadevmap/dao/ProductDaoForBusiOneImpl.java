package com.javadevmap.dao;

import org.springframework.stereotype.Repository;

@Repository("productDaoOne")
public class ProductDaoForBusiOneImpl implements IProductDao {

	@Override
	public String addProduct(String id, String name) {
		String result = String.format(ProductDaoForBusiOneImpl.class.getSimpleName()
				+" 添加商品id=%s，商品名称为 %s，成功！", id, name);

		return result;
	}

}
