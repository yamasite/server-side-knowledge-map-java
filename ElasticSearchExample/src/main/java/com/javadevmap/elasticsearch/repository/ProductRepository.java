package com.javadevmap.elasticsearch.repository;


import com.javadevmap.elasticsearch.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface ProductRepository extends ElasticsearchRepository<Product, String> {

    Page<Product> findByProductName(String productName, Pageable pageable);
    List<Product> findByBrand(String brand);
    List<Product>  findByPriceLessThan(double price);
    List<Product>  findByPriceGreaterThan(double price);

}