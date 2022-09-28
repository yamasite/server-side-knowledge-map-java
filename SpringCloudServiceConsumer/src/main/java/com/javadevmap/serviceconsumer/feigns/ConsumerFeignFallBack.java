package com.javadevmap.serviceconsumer.feigns;

import org.springframework.stereotype.Component;

import com.javadevmap.serviceconsumer.domain.DomainUser;
import com.javadevmap.serviceconsumer.result.Result;
import com.javadevmap.serviceconsumer.result.ResultCode;

@Component
public class ConsumerFeignFallBack implements ConsumerFeign{

	@Override
	public Result<DomainUser> getUser(int id) {
		Result<DomainUser> result = new Result<>(ResultCode.Unavailable);
		return result;
	}

	@Override
	public Result<String> addUser(DomainUser user) {
		Result<String> result = new Result<>(ResultCode.Unavailable);
		return result;
	}
}
