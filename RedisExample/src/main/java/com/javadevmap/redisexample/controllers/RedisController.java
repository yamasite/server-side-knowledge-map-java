package com.javadevmap.redisexample.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.javadevmap.redisexample.result.Result;
import com.javadevmap.redisexample.result.ResultCode;

@RestController
@RequestMapping(value="/redis")
public class RedisController {
	@RequestMapping(value="/hello",method=RequestMethod.GET)
	public Result<String> hello(){
		Result<String> result = new Result<>(ResultCode.OK,"hello redis!");
		return result;
	}
}
