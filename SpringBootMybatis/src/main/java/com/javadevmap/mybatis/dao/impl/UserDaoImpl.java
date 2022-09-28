package com.javadevmap.mybatis.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javadevmap.mybatis.dao.UserDao;
import com.javadevmap.mybatis.model.User;
import com.javadevmap.mybatis.model.mapper.UserMapper;

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
