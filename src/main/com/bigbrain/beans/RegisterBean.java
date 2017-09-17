package com.bigbrain.beans;

import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import com.bigbrain.entityBeans.User;
import com.bigbrain.enums.RoleEnum;
import com.bigbrain.enums.UserLevelEnum;
import com.bigbrain.services.UserService;
import com.bigbrain.utils.EmailUtils;

@ManagedBean(name="registerBean")
@RequestScoped
public class RegisterBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final String ACTIVATION_ACCOUNT_SUBJECT = "Activate you BigBrain account";
	private static final String ACTIVATION_ACCOUNT_BODY = "<html>"
			+ "<body><h2>Big Brain</h2>"
			+ "<p>Click the link to activate your account : "
			+ "<a href=\"$\">Activate</a></p>"
			+ "</body></html>";
	private UserService userService;
	private String email="";
    private String password="";
    private String firstname="";
    private String lastname="";
    private RoleEnum role;
    private UserLevelEnum userLevel;
    private Date date;
    private String active;
    private String errorMessage = "";
	
	public RegisterBean() {
		userService = new UserService();
	}

	public String getEmail() {
		return email.toLowerCase();
	}

	public void setEmail(String email) {
		this.email = email.toLowerCase();
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

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String goRegister() {
		errorMessage = "";
		return "register";
	}
	
	public String register() {
		String codeActivation = UUID.randomUUID().toString();
		String urlActivator = getUrlActivator(codeActivation);
		User userFromDB = userService.checkExistantUser(email);
		if(userFromDB != null) {
			errorMessage = "User already exist or not activated (Check your email)";
			return "register";
		}
		EmailUtils emailUtils;
		try {
			emailUtils = new EmailUtils();
		} catch (FileNotFoundException e) {
			errorMessage = "Sorry, unknown error, try later!";
			return "register";
		}
		emailUtils.sendEmail(email, ACTIVATION_ACCOUNT_SUBJECT, ACTIVATION_ACCOUNT_BODY.replace("$", urlActivator));
		User user = new User();
		user.setEmail(email);
		user.setPassword(password);
		user.setLastname(lastname);
		user.setFirstname(firstname);
		user.setActive(false);
		user.setDate(new Date());
		user.setUserLevel(UserLevelEnum.JUNIOR);
		user.setRole(RoleEnum.USER);
		user.setCodeActivation(codeActivation);
		userService.saveUser(user);
		return "registred";
	}

	private String getUrlActivator(String codeActivation) {
		HttpServletRequest origRequest = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
		String urlActivator = origRequest.getScheme() 
				+ "://" 
				+ origRequest.getServerName() 
				+ ((origRequest.getServerPort() != 80) ? ":" + origRequest.getServerPort() : "") 
				+ origRequest.getContextPath() 
				+ "/api/account/activation/" 
				+ email + "/" +  codeActivation;
		System.out.println(urlActivator);
		return urlActivator;
	}
	
}