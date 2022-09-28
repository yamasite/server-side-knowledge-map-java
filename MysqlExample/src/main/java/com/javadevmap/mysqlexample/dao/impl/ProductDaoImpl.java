package com.javadevmap.mysqlexample.dao.impl;

import com.javadevmap.mysqlexample.dao.ProductDao;
import com.javadevmap.mysqlexample.mapper.ProductManualMapper;
import com.javadevmap.mysqlexample.mapper.ProductMapper;
import com.javadevmap.mysqlexample.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductDaoImpl implements ProductDao{

    @Autowired
    ProductMapper productMapper;
    @Autowired
    ProductManualMapper productManualMapper;

    @Override
    public List<Product> getAllProducts() {
        return productMapper.selectAll();
    }

    @Override
    public int save(Product product) {
        return productMapper.insert(product);
    }

    @Override
    public Product findById(int id) {

        Product product = productMapper.selectByPrimaryKey(id);
        return product;
    }

    @Override
    public int saveProduct(Product product) {
        return productManualMapper.insertProduct(product);
    }
}
