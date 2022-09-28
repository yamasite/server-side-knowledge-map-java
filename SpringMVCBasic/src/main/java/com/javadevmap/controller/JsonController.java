package com.javadevmap.controller;

import com.javadevmap.domain.Product;
import com.javadevmap.domain.ResultBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/json")
public class JsonController {

	@RequestMapping(value = "/reqByJson", method = RequestMethod.POST)
	@ResponseBody
	public ResultBean requestJson(@RequestBody Product product) {

		System.out.println("requestJson() called with: product = [" + product + "]");

		return ResultBean.ofSuccess(product);
	}

}
