package com.javadevmap.controller;

import com.javadevmap.exception.CustomException;
import com.javadevmap.mybatis.dao.ProductModelMapper;
import com.javadevmap.mybatis.model.ProductModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/demo")
public class WebController {

	@Autowired
	ProductModelMapper productModelMapper;

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index() throws CustomException {

		ProductModel productModel = productModelMapper.selectByPrimaryKey(100);
		System.out.println(productModel);

		return "demo";
	}

	@RequestMapping("/normalpage")
	public String normalpage() throws CustomException {

		return "normalPage";
	}

	@RequestMapping(value = "/redirect", method = RequestMethod.GET)
	public String redirect() {
		return "redirect:redirectPage";
	}

	@RequestMapping(value = "/redirectPage", method = RequestMethod.GET)
	public String finalPage() {
		return "redirectPage";
	}

}
