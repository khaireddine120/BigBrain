package com.bigbrain.beans;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.bigbrain.entityBeans.User;
import com.bigbrain.services.UserService;

@ManagedBean(name="userBean")
@SessionScoped
public class UserBean implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private UserService userService;
	private User user;
	
	public UserBean() {
		user = new User();
		userService = new UserService();
	}

	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}

	public String goLogin() {
		return "login";
	}
	
	public String login() {
		if(!userService.validateUser(user)) {
			return "errorLogin"; 
		}
		return "welcome";
	}
	
	public String goRegister() {
		return "register";
	}
	
	public String register() {
		userService.saveUser(user);
		return "registred";
	}
	
}