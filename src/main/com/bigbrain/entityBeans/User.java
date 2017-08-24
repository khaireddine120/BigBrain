package com.bigbrain.entityBeans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class User {
	@Id
	private String username;
    @Column(name = "pwd")
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
    	this.password = password;
    }

	@Transient
	private String repassword;
	
	public User() {
		username = "";
		password = "";
		repassword = "";
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	public String getRepassword() {
		return repassword;
	}

	public void setRepassword(String repassword) {
		this.repassword = repassword;
	}
		
}
