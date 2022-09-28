package com.javadevmap.controller;

import com.javadevmap.domain.ResultBean;
import com.javadevmap.domain.Product;
import com.javadevmap.mybatis.dao.ProductModelMapper;
import com.javadevmap.mybatis.model.ProductModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/rest")
public class RestController {

	@Autowired
	ProductModelMapper productModelMapper;

	/**
	 * 查询产品信息
	 * 
	 * @param proId
	 * @return
	 */
	@RequestMapping(value = "/product/{proId}", method = RequestMethod.GET)
	public @ResponseBody ResultBean getProductInfo(@PathVariable("proId") Integer proId) {
		System.out.println("getProductInfo() called with: proId = [" + proId + "]");

		ProductModel productModel = productModelMapper.selectByPrimaryKey(proId);
		if (null == productModel) {
			return ResultBean.ofFail(501, "未查询到此商品");
		}
		Product product = new Product();
		BeanUtils.copyProperties(productModel, product);
		return ResultBean.ofSuccess(product, "查询成功");

	}

	/**
	 * 增加商品
	 * 
	 * @param product
	 * @return
	 */
	@RequestMapping(value = "/product", method = RequestMethod.POST)
	public @ResponseBody ResultBean addProduct(@RequestBody Product product) {
		System.out.println("addProduct() called with: product = [" + product + "]");
		// 业务逻辑代码 …..
		ProductModel productModel = new ProductModel();
		BeanUtils.copyProperties(product, productModel);

		productModelMapper.insert(productModel);
		return ResultBean.ofSuccess("增加成功");

	}

	/**
	 * 更新商品信息
	 * 
	 * @param product
	 * @return
	 */
	@RequestMapping(value = "/product", method = RequestMethod.PUT)
	public @ResponseBody ResultBean updateProduct(@RequestBody Product product) {
		System.out.println("updateProduct() called with: product = [" + product + "]");

		if (product.getId() == null) {
			return ResultBean.ofFail(502, "参数不正确");
		}

		ProductModel productModel = new ProductModel();
		BeanUtils.copyProperties(product, productModel);

		productModelMapper.updateByPrimaryKeySelective(productModel);
		return ResultBean.ofSuccess("更新商品成功");

	}

	/**
	 * 删除商品信息
	 * 
	 * @param proId
	 * @return
	 */
	@RequestMapping(value = "/product/{proId}", method = RequestMethod.DELETE)
	public @ResponseBody ResultBean delProduct(@PathVariable("proId") Integer proId) {
		System.out.println("delProduct() called with: proId = [" + proId + "]");

		int i = productModelMapper.deleteByPrimaryKey(proId);
		if (i <= 0) {
			return ResultBean.ofFail(503, "删除失败");
		}
		return ResultBean.ofSuccess("删除成功");

	}

}
