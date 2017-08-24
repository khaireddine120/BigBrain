package com.bigbrain.services;

import com.bigbrain.dao.UserDao;
import com.bigbrain.entityBeans.User;

public class UserService {
	
	private UserDao userDao;
	
	public UserService() {
		userDao = new UserDao();
	}
	
	public boolean validateUser(User user) {
		return userDao.getUser(user);
	}
	
	public void saveUser(User user) {
		userDao.saveUser(user);
	}
	
}
