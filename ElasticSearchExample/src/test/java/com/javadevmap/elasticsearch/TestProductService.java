package com.javadevmap.elasticsearch;

import com.javadevmap.elasticsearch.model.Product;
import com.javadevmap.elasticsearch.service.ProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ElasticSearchExampleApplication.class)
public class TestProductService {

    @Autowired
    private ProductService productService;

    @Autowired
    private ElasticsearchTemplate esTemplate;

    @Test
    public void testIndex() {
        esTemplate.deleteIndex(Product.class);
        esTemplate.createIndex(Product.class);
    }
    @Test
    public void mappings() {
        esTemplate.putMapping(Product.class);
        esTemplate.refresh(Product.class);
    }
//    @Before
    public void before() {
        esTemplate.deleteIndex(Product.class);
        esTemplate.createIndex(Product.class);
        esTemplate.putMapping(Product.class);
        esTemplate.refresh(Product.class);
    }
    @Test
    public void deleteIndex() {
        esTemplate.deleteIndex(Product.class);
    }

    @Test
    public void testCRUD(){

        Product product = new Product("1001", "JavaDevMap learn Elasticsearch", 67d, "计算机网络");
        Product insertProduct = productService.save(product);
        System.out.println("insertResult:"+insertProduct);
        insertProduct.setBrand("brand品牌");
        productService.save(insertProduct);
    }

    @Test
    public void testSave() {

        Product product = new Product("1001", "JavaDevMap learn Elasticsearch", 67d, "计算机网络");
        Product insertProduct = productService.save(product);
        assertNotNull(insertProduct.getId());
        assertEquals(insertProduct.getProductName(), product.getProductName());
        assertEquals(insertProduct.getBrand(), product.getBrand());
        assertEquals(insertProduct.getPrice(), product.getPrice());
    }

    @Test
    public void testFindOne() {

        Product product = new Product("1001", "JavaDevMap learn Elasticsearch", 67d, "计算机网络");
        productService.save(product);
        Product insertProduct = productService.findOne(product.getId());
        assertNotNull(product.getId());
        assertEquals(insertProduct.getProductName(), product.getProductName());
        assertEquals(insertProduct.getBrand(), product.getBrand());
        assertEquals(insertProduct.getPrice(), product.getPrice());
    }

    @Test
    public void testFindByBrand() {

        Product product = new Product("1001", "JavaDevMap learn Elasticsearch", 67d, "计算机网络");
        productService.save(product);
        List<Product> list = productService.findByBrand(product.getBrand());
        System.out.println(list.get(0).toString());
        assertThat(list.size(), is(1));
    }

    @Test
    public void testFindByProductName() {

        List<Product> productList = new ArrayList<>();
        productList.add(new Product("1011", "JavaDevMap 昨天发布 learn Elasticsearch version 01 ", 67d, "计算机"));
        productList.add(new Product("1022", "JavaDevMap 昨天早上北京learn Elasticsearch version 02", 67d, "计算机网络"));
        productList.add(new Product("1033", "JavaDevMap 在本文中，我们将讨论“如何创建Spring Boot + Spring Data + Elasticsearch范例”。", 67d, "计算机网络"));
        productList.add(new Product("1044", "JavaDevMap 在本文中，我们将讨论“如何创建Spring Boot + Spring Data + Elasticsearch范例”。", 67d, "计算机网络"));
        productList.add(new Product("1055", "JavaDevMap learn Elasticsearch version 05", 67d, "计算机网络"));
        for (Product item : productList) {
            productService.save(item);
        }
        Page<Product> pageProducts = productService.findByProductName("Elasticsearch", new PageRequest(0, 10));
        assertThat(pageProducts.getTotalElements(), is(5L));
        System.out.println(pageProducts.getContent().get(0).toString());
    }

    @Test
    public void testDelete() {

        Product product = new Product("1001", "JavaDevMap learn Elasticsearch version 01 ", 67d, "计算机网络");
        productService.save(product);
        productService.delete(product);
        Product findProduct = productService.findOne(product.getId());
        assertNull(findProduct);
    }

}