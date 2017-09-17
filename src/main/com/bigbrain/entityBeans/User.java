package com.bigbrain.entityBeans;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.bigbrain.enums.RoleEnum;
import com.bigbrain.enums.UserLevelEnum;

@Entity
public class User implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id @Column(name = "eml")
	private String email;
    @Column(name = "pwd")
    private String password;
    @Column(name = "fnm")
    private String firstname;
    @Column(name = "lnm")
    private String lastname;
    @Column(name = "rle")
    private RoleEnum role;
    @Column(name = "ule")
    private UserLevelEnum userLevel;
    @Column(name = "dtc")
    private Date date;
    @Column(name = "atv")
    private Boolean active;
    @Column(name = "cAct")
    private String codeActivation;

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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public UserLevelEnum getUserLevel() {
		return userLevel;
	}

	public void setUserLevel(UserLevelEnum userLevel) {
		this.userLevel = userLevel;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public String getCodeActivation() {
		return codeActivation;
	}

	public void setCodeActivation(String codeActivation) {
		this.codeActivation = codeActivation;
	}

}
