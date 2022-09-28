package com.javadevmap.mongodbexample;

import com.javadevmap.mongodbexample.model.Product;
import com.javadevmap.mongodbexample.repository.ProductMongoRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductMapperTest {
    @Autowired
    ProductMongoRepository productMongoRepository;
    
    @Test
    public void addProductOne(){
        productMongoRepository.delete(55);
        Product product = new Product();
        product.setId(55);
        product.setName("java dev map");
        product.setPrice(221);
        product.setHtmlDetail("<p><img src=\"http://t.cn/R3cquJp\" alt=\"\"/><img src=\"http://t.cn/R3c5hfe\" alt=\"\"/></p>");
        Product result = productMongoRepository.insert(product);
        System.out.println(result);
    }


    @Test
    public void testMongoOrigin() {
        System.out.println("delete all mongo data");
        productMongoRepository.deleteAll();
        System.out.println("insert mongo data");
        productMongoRepository.insert(new Product(101, "product01", 18));
        System.out.println("mongo data find by id");
        System.out.println(productMongoRepository.findOne(101));
        System.out.println("mongo data delete by id");
        productMongoRepository.delete(101);
    }

    @Test
    public void testMongo() {
        System.out.println("delete all mongo data");
        productMongoRepository.deleteAll();
        System.out.println("insert mongo data");
        productMongoRepository.save(new Product(1, "product01", 18));
        productMongoRepository.save(new Product(2, "product02", 20));
        productMongoRepository.save(new Product(3, "product03", 50));
        productMongoRepository.save(new Product(4, "product04", 40));
        productMongoRepository.save(new Product(5, "product05", 18));
        productMongoRepository.save(new Product(6, "product06", 110));
        System.out.println("mongo data count");
        System.out.println(productMongoRepository.count());
        System.out.println("mongo data find by name");
        System.out.println(productMongoRepository.findByName("product06"));
        System.out.println("mongo data find by id");
        System.out.println(productMongoRepository.findOne(2));
        System.out.println("mongo data find by price return all");
        System.out.println(productMongoRepository.findByPrice(18).size());
        System.out.println("mongo data find by price and pageable");
        PageRequest pageable = new PageRequest(0, 1);
        System.out.println(productMongoRepository.findByPrice(18,pageable));
        System.out.println("mongo data find by price return one");
        System.out.println(productMongoRepository.findOneByPrice(18));
        System.out.println("mongo data find by price and name");
        System.out.println(productMongoRepository.findOneByPriceAndName(40, "product04"));
        System.out.println("mongo data modify");
        productMongoRepository.save(new Product(2, "product new", 118));
        System.out.println("mongo data find price less than 40 size is ");
        System.out.println(productMongoRepository.findByPriceLessThan(40).size());
        System.out.println("mongo data delete by id");
        productMongoRepository.delete(3);
        System.out.println("mongo data delete by object");
        productMongoRepository.delete(productMongoRepository.findByName("product05"));
        System.out.println("mongo data count");
        System.out.println(productMongoRepository.count());
        System.out.println("mongo data insert data");
        Product product = new Product();
        product.setId(9);
        product.setName("product08");
        product.setPrice(21);
        productMongoRepository.insert(product);
        System.out.println("mongo data count");
        System.out.println(productMongoRepository.count());
    }

}