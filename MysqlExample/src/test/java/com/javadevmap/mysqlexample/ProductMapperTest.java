package com.javadevmap.mysqlexample;

import com.javadevmap.mysqlexample.dao.ProductDao;
import com.javadevmap.mysqlexample.mapper.ProductMapper;
import com.javadevmap.mysqlexample.mapper.ProductManualMapper;
import com.javadevmap.mysqlexample.model.OrderAndProductModel;
import com.javadevmap.mysqlexample.model.Product;
import com.javadevmap.mysqlexample.service.ProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductMapperTest {

	@Autowired
	private ProductManualMapper productManualMapper;
	List<OrderAndProductModel> orderProductList = null;
	@Autowired
	private ProductMapper mapper;

	@Autowired
	ProductService productServiceImpl;

	@Autowired
	private ProductDao productDao;

	@Test
	public void testSelfMapper() {
		orderProductList = productManualMapper.getOrderProductList();

		System.out.println("result size() = " + orderProductList.size());

		if (orderProductList.size() > 0) {
			for (OrderAndProductModel item : orderProductList) {
				System.out.println(item);
			}
		}
	}

	@Test
	public void testGetInsertDataId() {
		Product product = new Product();
		product.setPrice(2332);
		product.setProductName("java dev map " + System.currentTimeMillis());
		product.setProductDesc("商品描述 dao save ");
		Integer num = productDao.saveProduct(product);
		System.out.println(product);
		System.out.println("insertProductId: " + product.getId());
	}

	@Test
	public void testTransaction() {
		productServiceImpl.modifyProducts();
	}

	@Test
	public void testTransaction02() {
		productServiceImpl.modifyProductsByTransaction();
	}

	@Test
	public void selectByCategoryType() {
		List<Product> products = mapper.selectAll();
		if (null != products) {
			for (Product pro : products) {
				System.out.println(pro);
			}
		}
	}

	@Test
	public void insert() {
		Product product = new Product();
		product.setPrice(233);
		product.setProductName("java dev map version 02");
		product.setProductDesc("商品描述");
		int insert = mapper.insert(product);
		System.out.println(product);
	}

	@Test
	public void insert22() {
		Product product = new Product();
		product.setPrice(233);
		product.setProductName("java dev map version 02");
		product.setProductDesc("商品描述");
		int insert = productManualMapper.insertProduct(product);
		System.out.println(product);
	}

	@Test
	public void update() {
		Product product = new Product();
		product.setId(108);
		product.setProductName("java dev map version 02 update ");
		product.setProductDesc("商品描述");
		int i = mapper.updateByPrimaryKey(product);
		System.out.println(i);
	}

	@Test
	public void delete() {
		int i = mapper.deleteByPrimaryKey(107);
		System.out.println(i);
	}

}