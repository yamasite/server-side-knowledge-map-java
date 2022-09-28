package com.javadevmap.mybatis.controllers;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.javadevmap.mybatis.domain.DomainUser;
import com.javadevmap.mybatis.result.Result;
import com.javadevmap.mybatis.result.ResultCode;
import com.javadevmap.mybatis.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import springfox.documentation.annotations.ApiIgnore;

@Api(value="用户服务")
@RestController
@RequestMapping("/user")
public class UserController {
	private static final Logger log = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService service;

	@ApiOperation(value="获取用户信息", notes="根据id获取用户信息")
	@ApiImplicitParam(name = "Id", value = "用户Id", required = true, dataType = "integer",paramType = "path")
	@RequestMapping(value="/{Id}",method=RequestMethod.GET)
	public Result<DomainUser> getUser(@PathVariable("Id") int id) {
		DomainUser user = service.getUser(id);
		Result<DomainUser> result = null;
		if(user!=null) {
			result = new Result<>(ResultCode.OK, user);
		}else {
			result = new Result<>(ResultCode.Not_Found);
		}
		//log.info(result.toString());
		return result;
	}
	
	@ApiOperation(value="添加用户", notes="获取用户信息并保存")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "user", value = "用户信息", required = true, dataType = "DomainUser",paramType = "body")
	})
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public Result<String> addUser(@RequestBody @Valid DomainUser user) {				
		int ret = service.saveUser(user);
		Result<String> result = null;
		if(ret == 1) {
			result = new Result<>(ResultCode.OK);
		}else {
			result = new Result<>(ResultCode.ERROR);
		}
		return result;
	}
	
	@ApiIgnore()
	@RequestMapping(value="/ignore",method=RequestMethod.GET)
	public Result<String> ignore() {		
		return new Result<>(ResultCode.OK);
	}
}
