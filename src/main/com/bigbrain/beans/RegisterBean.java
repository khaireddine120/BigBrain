package com.bigbrain.beans;

import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import com.bigbrain.entityBeans.User;
import com.bigbrain.enums.RoleEnum;
import com.bigbrain.enums.UserLevelEnum;
import com.bigbrain.services.UserService;
import com.bigbrain.utils.EmailUtils;

@ManagedBean(name="registerBean")
@RequestScoped
public class RegisterBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private UserService userService;
	private String email;
    private String password;
    private String firstname;
    private String lastname;
    private RoleEnum role;
    private UserLevelEnum userLevel;
    private Date date;
    private String active;
	
	public RegisterBean() {
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

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public RoleEnum getRole() {
		return role;
	}

	public void setRole(RoleEnum role) {
		this.role = role;
	}

	public UserLevelEnum getUserLevel() {
		return userLevel;
	}

	public void setUserLevel(UserLevelEnum userLevel) {
		this.userLevel = userLevel;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public String goRegister() {
		return "register";
	}
	
	public String register() throws FileNotFoundException {
		User user = new User();
		user.setEmail(email);
		user.setPassword(password);
		user.setLastname(lastname);
		user.setFirstname(firstname);
		user.setActive(false);
		user.setDate(new Date());
		user.setUserLevel(UserLevelEnum.JUNIOR);
		user.setRole(RoleEnum.USER);
		userService.saveUser(user);
		EmailUtils emailUtils = new EmailUtils();
		emailUtils.sendEmail("khaireddine.rezgui@gmail.com", "subject Test", "body text");
		return "registred";
	}
	
}