package com.javadevmap.serviceprovider.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javadevmap.serviceprovider.dao.UserDao;
import com.javadevmap.serviceprovider.model.User;
import com.javadevmap.serviceprovider.model.mapper.UserMapper;

@Repository
public class UserDaoImpl implements UserDao{
	@Autowired
	private UserMapper mapper;

	@Override
	public User getUser(int id) {
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public int saveUser(User user) {
		return mapper.insert(user);
	}

}
