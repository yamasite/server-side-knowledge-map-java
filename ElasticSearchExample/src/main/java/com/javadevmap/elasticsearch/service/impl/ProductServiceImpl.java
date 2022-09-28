package com.javadevmap.elasticsearch.service.impl;


import com.javadevmap.elasticsearch.model.Product;
import com.javadevmap.elasticsearch.repository.ProductRepository;
import com.javadevmap.elasticsearch.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    public void delete(Product book) {
        productRepository.delete(book);
    }

    @Override
    public Page<Product> findByProductName(String name, PageRequest pageRequest) {
        return productRepository.findByProductName(name,pageRequest);
    }

    @Override
    public List<Product> findByBrand(String brand) {
        return productRepository.findByBrand(brand);
    }

    @Override
    public Product save(Product book) {
        return productRepository.save(book);
    }

    public Product findOne(String id) {
        return productRepository.findOne(id);
    }

    public Iterable<Product> findAll() {
        return productRepository.findAll();
    }

}