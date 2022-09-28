package com.javadevmap.zuul.feigns;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javadevmap.zuul.result.Result;

@FeignClient(name="auth",url="http://172.17.238.237:18070")
public interface AuthFeign {
	@RequestMapping(value="/auth/gettoken",method=RequestMethod.GET)
	public Result<String> getToken(@RequestParam("name") String name,@RequestParam("password") String password);
	
	@RequestMapping(value="/auth/validtoken",method=RequestMethod.GET)
	public Result<String> validToken(@RequestParam("token") String token);
}
