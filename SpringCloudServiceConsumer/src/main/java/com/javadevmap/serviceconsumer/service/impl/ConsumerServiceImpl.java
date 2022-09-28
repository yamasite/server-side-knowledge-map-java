package com.javadevmap.serviceconsumer.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.javadevmap.serviceconsumer.domain.DomainUser;
import com.javadevmap.serviceconsumer.feigns.ConsumerFeign;
import com.javadevmap.serviceconsumer.result.Result;
import com.javadevmap.serviceconsumer.result.ResultDomainUser;
import com.javadevmap.serviceconsumer.service.ConsumerService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;


@Service
public class ConsumerServiceImpl implements ConsumerService{
	private static final Logger log = LoggerFactory.getLogger(ConsumerServiceImpl.class);
	
	@Autowired
	private ConsumerFeign feign;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Override
	//@HystrixCommand(fallbackMethod="getUserFallback")
	public DomainUser getUserFromProvider(int id) {
//		//test for return data
//		String retString = restTemplate.getForObject("http://SERVICE-PROVIDER/user/"+id, String.class);
//		log.info("consumer receive is " + retString);
//		return null;
		
//		//test for class type get object
//		ResultDomainUser result = restTemplate.getForObject("http://SERVICE-PROVIDER/user/"+id, ResultDomainUser.class);
//		if(result.getResultCode()==200) {
//			return (DomainUser)result.getData();
//		}else {
//			return null;
//		}
		
//		//test for templete
//		ParameterizedTypeReference responseType = new ParameterizedTypeReference<Result<DomainUser>>() {};
//		ResponseEntity<Result<DomainUser>> resp = restTemplate.exchange("http://SERVICE-PROVIDER/user/"+id, HttpMethod.GET, null, responseType);
//		Result<DomainUser> result = resp.getBody();
//		if(result.getResultCode()==200) {
//			return (DomainUser)result.getData();
//		}else {
//			return null;
//		}
		
		Result<DomainUser> result = feign.getUser(id);
		if(result.getResultCode()==200) {
			return (DomainUser)result.getData();
		}else {
			return null;
		}
	}
	
//	public DomainUser getUserFallback(int id) {
//		DomainUser user = new DomainUser();
//		user.setName("hystrix");
//		user.setAge(-1);
//		user.setAddress("hystrix fall back");
//		return user;
//	}

	@Override
	public int saveUserToProvider(DomainUser user) {
		Result<String> result = feign.addUser(user);
		if(result.getResultCode()==200) {
			return 1;
		}else {
			return 0;
		}
	}
}
