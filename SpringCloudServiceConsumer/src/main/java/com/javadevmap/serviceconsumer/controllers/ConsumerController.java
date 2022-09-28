package com.javadevmap.serviceconsumer.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.javadevmap.serviceconsumer.domain.DomainUser;
import com.javadevmap.serviceconsumer.result.Result;
import com.javadevmap.serviceconsumer.result.ResultCode;
import com.javadevmap.serviceconsumer.service.ConsumerService;

@RefreshScope
@RestController
@RequestMapping(value="/consumer")
public class ConsumerController {
	@Autowired
	private ConsumerService service;
	
	@RequestMapping(value="/{Id}",method=RequestMethod.GET)
	public Result<DomainUser> getUser(@PathVariable("Id") int id) {
		DomainUser user = service.getUserFromProvider(id);
		Result<DomainUser> result = null;
		if(user!=null) {
			result = new Result<>(ResultCode.OK, user);
		}else {
			result = new Result<>(ResultCode.Not_Found);
		}
		return result;
	}
	
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public Result<String> addUser(@RequestBody @Valid DomainUser user) {				
		Result<String> result = null;
		int ret = service.saveUserToProvider(user);
		if(ret == 1) {
			result = new Result<>(ResultCode.OK);
		}else {
			result = new Result<>(ResultCode.ERROR);
		}
		return result;
	}
	
	@Value("${custom.foo}")
	String fooValue;
	@RequestMapping(value="/foo",method=RequestMethod.GET)
	public Result<String> getFoo() {
		Result<String> result = null;
		if(fooValue!=null) {
			result = new Result<>(ResultCode.OK, fooValue);
		}else {
			result = new Result<>(ResultCode.Not_Found);
		}
		return result;
	}
}
