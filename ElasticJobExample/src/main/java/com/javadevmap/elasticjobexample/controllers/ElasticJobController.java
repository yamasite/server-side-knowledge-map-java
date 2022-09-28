package com.javadevmap.elasticjobexample.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.javadevmap.elasticjobexample.result.Result;
import com.javadevmap.elasticjobexample.result.ResultCode;

@RestController
@RequestMapping(value="/esjob")
public class ElasticJobController {
	@RequestMapping(value="/hello",method=RequestMethod.GET)
	public Result<String> hello(){
		Result<String> result = new Result<>(ResultCode.OK,"hello esjob!");
		return result;
	}
}
