package com.bigbrain.services;

import com.bigbrain.dao.UserDao;
import com.bigbrain.entityBeans.User;

public class UserService {
	
	private UserDao userDao;
	
	public UserService() {
		userDao = new UserDao();
	}
	
	public User validateUser(String email, String password) {
		return userDao.getUser(email, password);
	}
	
	public void saveUser(User user) {
		userDao.saveUser(user);
	}
	
}
