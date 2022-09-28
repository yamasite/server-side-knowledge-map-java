package com.javadevmap.elasticsearch.service;


import com.javadevmap.elasticsearch.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface ProductService {

    Page<Product> findByProductName(String name, PageRequest pageRequest);
    List<Product> findByBrand(String brand);
    Product save(Product book);
    Iterable<Product> findAll();
    Product findOne(String id);
    void delete(Product book);
}