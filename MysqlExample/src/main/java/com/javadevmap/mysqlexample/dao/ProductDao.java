package com.javadevmap.mysqlexample.dao;

import com.javadevmap.mysqlexample.model.Product;

import java.util.List;

/**
 * Product Dao
 */
public interface ProductDao {

	List<Product> getAllProducts();

	int save(Product product);

	Product findById(int id);

	int saveProduct(Product product);

}
