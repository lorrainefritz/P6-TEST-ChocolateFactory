package com.chocolateFactory.Chocolate.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//@AllArgsConstructor @Getter @Setter @NoArgsConstructor
public class UserRegistrationDto {
private String name;
private String password;
private String email;

public UserRegistrationDto(String name, String password, String email) {
	super();
	this.name = name;
	this.password = password;
	this.email = email;
}

public UserRegistrationDto() {
	super();
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public String getPassword() {
	return password;
}

public void setPassword(String password) {
	this.password = password;
}

public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}



}
