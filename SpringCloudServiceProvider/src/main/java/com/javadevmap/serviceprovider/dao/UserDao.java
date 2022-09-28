package com.javadevmap.serviceprovider.dao;

import com.javadevmap.serviceprovider.model.User;


public interface UserDao {
	public User getUser(int id);
	public int saveUser(User user);
}
