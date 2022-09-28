package com.javadevmap.serviceprovider.controllers;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javadevmap.serviceprovider.domain.DomainUser;
import com.javadevmap.serviceprovider.result.Result;
import com.javadevmap.serviceprovider.result.ResultCode;
import com.javadevmap.serviceprovider.service.UserService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RefreshScope
@RestController
@RequestMapping("/user")
public class UserController {
	private static final Logger log = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService service;
	
	@HystrixCommand(commandKey="provider-getuser",groupKey="provider-usercontroller",fallbackMethod="getUserFallBack")
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
	
	public Result<DomainUser> getUserFallBack(int id){
		Result<DomainUser> result = new Result<>(ResultCode.Unavailable);
		return result;
	}
	
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public Result<String> addUser(@RequestBody @Valid DomainUser user) {				
		Result<String> result = null;
		int ret = service.saveUser(user);
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
	
	static String[] ipStrings = {"47.95.113.117",
			"58.30.0.0",
			"58.66.194.0",
			"58.83.156.0",
			"58.207.200.0",
			"58.155.200.0"};
	ObjectMapper mapper = new ObjectMapper();
	
	public class IpInfo{
		public String userIp;
		public int age;
		
		String getJson() {
			int ipindex = (int) (Math.random()*ipStrings.length);
			this.userIp = ipStrings[ipindex];
			
			this.age = 16+ (int)(Math.random()*20);
			String ret = null;
			try {
				ret = mapper.writeValueAsString(this);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return ret;
		}
	}
	
	@HystrixCommand(commandKey="provider-getuser",groupKey="provider-usercontroller")
	@RequestMapping(value="/testInfo",method=RequestMethod.GET)
	public Result<String> testInfo() {
		String ip = (new IpInfo()).getJson();
		log.info(ip);
		return new Result<>(ResultCode.OK);
	}
}
