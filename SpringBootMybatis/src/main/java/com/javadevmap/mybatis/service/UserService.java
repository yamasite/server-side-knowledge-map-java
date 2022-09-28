package com.javadevmap.mybatis.service;

import com.javadevmap.mybatis.domain.DomainUser;

public interface UserService {
	public DomainUser getUser(int id);
	public int saveUser(DomainUser user);
}
