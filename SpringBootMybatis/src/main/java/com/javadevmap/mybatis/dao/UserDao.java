package com.javadevmap.mybatis.dao;

import com.javadevmap.mybatis.model.User;


public interface UserDao {
	public User getUser(int id);
	public int saveUser(User user);
}
