package com.javadevmap.serviceprovider.service;

import com.javadevmap.serviceprovider.domain.DomainUser;

public interface UserService {
	public DomainUser getUser(int id);
	public int saveUser(DomainUser user);
}
