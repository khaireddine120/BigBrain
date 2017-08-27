package com.bigbrain.beans;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.bigbrain.entityBeans.User;
import com.bigbrain.services.UserService;

@ManagedBean(name="loginBean")
@SessionScoped
public class LoginBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private UserService userService;
	private String email = "";
	private String password = "";
	private User userLogged;
	
	public LoginBean() {
		userService = new UserService();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public User getUserLogged() {
		return userLogged;
	}

	public void setUserLogged(User userLogged) {
		this.userLogged = userLogged;
	}

	public String goLogin() {
		return "login";
	}
	
	public String login() {
		userLogged = userService.validateUser(email, password);
		if(userLogged == null) {
			return "errorLogin"; 
		}
		return "welcome";
	}
	
}