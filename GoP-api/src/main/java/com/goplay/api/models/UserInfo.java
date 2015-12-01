package com.goplay.api.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "user_info")
public class UserInfo {

  @Id
  @NotNull
  private String username;
  
  @NotNull
  private String first_name;
  
  @NotNull
  private String last_name;
  
  @NotNull
  private String email;
  
  @NotNull
  private String phone;
  
  @NotNull
  private String address;
  
  @NotNull
  private String city;

  @NotNull
  private String state;
  
  @NotNull
  private String zipcode;
  
  public UserInfo() { }

  public UserInfo(String username, 
		  	String firstname,
		  	String lastname,
		  	String email,
		  	String phone,
		  	String address,
		  	String city,
		  	String state,
		  	String zipcode) {
	  
	  	this.username = username;
	    this.first_name = firstname;
	    this.last_name = lastname;
	    this.email = email;
	    this.phone = phone;
	    this.address = address;
	    this.city = city;
	    this.state = state;
	    this.zipcode = zipcode;
	    
  }

  

  public String getEmail() {
    return email;
  }
  
  public void setEmail(String value) {
    this.email = value;
  }
  
  public String getUserName() {
    return username;
  }

  public void setName(String value) {
    this.username = value;
  }
  
} 
