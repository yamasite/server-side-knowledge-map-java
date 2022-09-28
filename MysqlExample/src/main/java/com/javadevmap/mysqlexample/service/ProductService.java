package com.javadevmap.mysqlexample.service;

import com.javadevmap.mysqlexample.domain.ProductDomain;

import java.util.List;

/**
 * product interface
 */
public interface ProductService {

    void addProduct(String name,int price,String desc);
    void modifyProducts();
    void modifyProductsByTransaction();
    List<ProductDomain> getAllProducts();
    ProductDomain findProductById(int proId);

}
