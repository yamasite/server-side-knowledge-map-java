package com.javadevmap.mysqlexample.service.impl;

import com.javadevmap.mysqlexample.dao.ProductDao;
import com.javadevmap.mysqlexample.domain.ProductDomain;
import com.javadevmap.mysqlexample.model.Product;
import com.javadevmap.mysqlexample.service.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * product impl
 */
@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductDao productDao;

	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 30, rollbackFor = Exception.class)
	@Override
	public void addProduct(String name, int price, String desc) {
		Product product = new Product();
		product.setPrice(price);
		product.setProductName(name);
		product.setProductDesc(desc);
		productDao.save(product);
	}

	@Override
	public void modifyProducts() {
		Product product = new Product();
		product.setPrice(233);
		product.setProductName("not transactions java dev map version 02");
		product.setProductDesc("商品描述");
		productDao.save(product);

		int i = 4 / 0;
		product = new Product();
		product.setPrice(800);
		product.setProductName("java dev map version 03");
		product.setProductDesc("商品描述 03");
		productDao.save(product);

	}

	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 30, rollbackFor = Exception.class)
	@Override
	public void modifyProductsByTransaction() {
		Product product = new Product();
		product.setPrice(253);
		product.setProductName("Transactional java dev map version 02");
		product.setProductDesc("商品描述");
		productDao.save(product);

		int i = 4 / 0;
		product = new Product();
		product.setPrice(800);
		product.setProductName("Transactional java dev map version 03");
		product.setProductDesc("商品描述 03");
		productDao.save(product);

	}

	@Override
	public List<ProductDomain> getAllProducts() {

		List<Product> allProducts = productDao.getAllProducts();

		List<ProductDomain> retList = new ArrayList<>();
		for (Product item : allProducts) {
			ProductDomain domain = new ProductDomain();
			BeanUtils.copyProperties(item, domain);
			retList.add(domain);
		}

		return retList;
	}

	@Override
	public ProductDomain findProductById(int proId) {

		ProductDomain domain = null;
		Product product = productDao.findById(proId);
		if (null != product) {
			domain = new ProductDomain();
			BeanUtils.copyProperties(product, domain);
		}

		return domain;
	}

}
