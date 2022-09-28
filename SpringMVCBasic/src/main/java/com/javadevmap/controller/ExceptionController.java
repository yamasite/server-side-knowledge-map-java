package com.javadevmap.controller;

import com.javadevmap.domain.Product;
import com.javadevmap.domain.ResultBean;
import com.javadevmap.exception.CustomException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/exception")
public class ExceptionController {

	@RequestMapping("/web")
	public String index() throws CustomException {
		//业务处理
		if(1==1){// 抛出自定义异常
			throw new CustomException("页面请求出现了异常");
		}
		return "demo";
	}

	@RequestMapping(value="/json",method = RequestMethod.POST)
	public @ResponseBody ResultBean addProduct(@RequestBody Product product)throws CustomException {
		System.out.println("addProduct() called with: product = [" + product + "]");
		//业务逻辑代码 …..
		if(1==1){// 抛出自定义异常
			throw new CustomException("Ajax请求出现了异常");
		}
		return ResultBean.ofSuccess();
	}

}
