package com.javadevmap.serviceconsumer.service;

import com.javadevmap.serviceconsumer.domain.DomainUser;

public interface ConsumerService {
	public DomainUser getUserFromProvider(int id);
	public int saveUserToProvider(DomainUser user);
}
