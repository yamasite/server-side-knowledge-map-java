package com.javadevmap.serviceconsumer.feigns;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javadevmap.serviceconsumer.domain.DomainUser;
import com.javadevmap.serviceconsumer.result.Result;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@FeignClient(value="SERVICE-PROVIDER",fallback=ConsumerFeignFallBack.class)
public interface ConsumerFeign {
	@RequestMapping(value="/user/{id}",method=RequestMethod.GET)
	public Result<DomainUser> getUser(@RequestParam("id") int id);
	
	@RequestMapping(value="/user/add",method=RequestMethod.POST)
	public Result<String> addUser(@RequestBody DomainUser user);
}
