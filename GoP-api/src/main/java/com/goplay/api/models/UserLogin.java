package com.goplay.api.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "user_login")
public class UserLogin {
	
	@Id
	@NotNull
	private String username;
	
	@NotNull
	private String password;
	
	
	
	UserLogin() {
		
	}
	
	public UserLogin(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	public String getPassword() {
		return this.password;
	}

	
	public void setPassword(String value) {
		this.password = value;
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public void setUserName(String username) {
		this.username = username;
	}
	
}
