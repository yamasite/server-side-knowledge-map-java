package com.javadevmap.elasticsearch;

import com.javadevmap.elasticsearch.model.Product;
import com.javadevmap.elasticsearch.repository.ProductRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Random;

/**
 * TestProductRepository
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ElasticSearchExampleApplication.class)
public class TestProductRepository {

	@Autowired
	ProductRepository productRepository;
	@Autowired
	private ElasticsearchTemplate esTemplate;

	@Before
	public void before() {
		esTemplate.deleteIndex(Product.class);
		esTemplate.createIndex(Product.class);
		esTemplate.putMapping(Product.class);
		esTemplate.refresh(Product.class);
	}

	@Test
	public void test() {
		Product product = new Product("1001", "JavaDevMap learn Elasticsearch", 67d, "计算机网络");
		Product saveBean = productRepository.save(product);
		System.out.println("save id is :" + saveBean.getId());
		Product findBean = productRepository.findOne(saveBean.getId());
		System.out.println("findBean is :" + findBean);
		findBean.setBrand("update brand");
		productRepository.save(findBean);
		Product updateBean = productRepository.findOne(findBean.getId());
		System.out.println("updateBean is " + updateBean);
		productRepository.delete(updateBean.getId());
		Product searchBean = productRepository.findOne(findBean.getId());
		System.out.println("delete search result is " + searchBean);
	}

	@Test
	public void testSearch() {
		for (int i = 0; i < 40; i++) {
			double price = new Random().nextDouble() * 100;
			System.out.println("price is " + price);

			Product product = new Product(i + "",
					"J在“Spring Boot应用企业级博客系统”课程中（http://coding.imooc.com/class/125.html），所有的博客功能都已经完成了。读者朋友们开始愉快地使用博客来发表博客了。但如果朋友们足够细心的话，发现在输入中文的标签的时候，存在一定的问题。",
					price, "计算机网络");
			productRepository.save(product);
		}
		Page<Product> pageProducts = productRepository.findByProductName("Boot 课程", new PageRequest(0, 10));
		System.out.println("getTotalElements is :" + pageProducts.getTotalElements());
		System.out.println("getTotalPages is :" + pageProducts.getTotalPages());
		System.out.println("getContent().size() is :" + pageProducts.getContent().size());
	}

	@Test
	public void testSearchLessThan() {
		for (int i = 0; i < 40; i++) {
			double price = new Random().nextDouble() * 100;
			System.out.println("price is " + price);
			Product product = new Product(i + "", "JavaDevMap learn Elasticsearch", price, "计算机网络");
			productRepository.save(product);
		}
		List<Product> byPriceLessThan = productRepository.findByPriceLessThan(20d);
		System.out.println("size is " + byPriceLessThan.size());
		byPriceLessThan = productRepository.findByPriceGreaterThan(20d);
		System.out.println("size is " + byPriceLessThan.size());
	}

	@Test
	public void addTestDatas() {
		for (int i = 0; i < 40; i++) {
			double price = new Random().nextDouble() * 100;
			System.out.println("price is " + price);
			Product product = new Product(i + "", "JavaDevMap learn Elasticsearch", price, "计算机网络");
			productRepository.save(product);
		}
	}

}
