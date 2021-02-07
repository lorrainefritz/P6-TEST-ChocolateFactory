package com.chocolateFactory.Chocolate.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.chocolateFactory.Chocolate.dto.UserRegistrationDto;
import com.chocolateFactory.Chocolate.entities.User;

public interface UserService extends UserDetailsService {
	User save(UserRegistrationDto registrationDto); 
}
