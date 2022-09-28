package com.javadevmap.mybatis.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javadevmap.mybatis.dao.UserDao;
import com.javadevmap.mybatis.domain.DomainUser;
import com.javadevmap.mybatis.model.User;
import com.javadevmap.mybatis.service.UserService;


@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserDao dao;
	
	@Override
	public DomainUser getUser(int id) {
		User user = dao.getUser(id);
		if(user!=null) {
			DomainUser domainUser = new DomainUser();
			BeanUtils.copyProperties(user, domainUser);
			return domainUser;
		}
		return null;
	}

	@Override
	public int saveUser(DomainUser domainUser) {
		User user = new User();
		BeanUtils.copyProperties(domainUser, user);
		return dao.saveUser(user);
	}

}
