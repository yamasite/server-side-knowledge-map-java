package com.javadevmap.mongodbexample.repository;

import com.javadevmap.mongodbexample.model.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ProductMongoRepository extends MongoRepository<Product, Integer> {
    Product findByName(String name);
    List<Product> findByPrice(int id);
    List<Product> findByPriceLessThan(int price);
    Product findOneByPrice(Integer price);
    Product findOneByPriceAndName(Integer price,String name);
    List<Product> findByPrice(Integer price,Pageable page);

}