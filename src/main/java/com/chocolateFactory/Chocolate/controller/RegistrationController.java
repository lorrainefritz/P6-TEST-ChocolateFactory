package com.chocolateFactory.Chocolate.controller;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.chocolateFactory.Chocolate.dto.UserRegistrationDto;
import com.chocolateFactory.Chocolate.entities.Role;
import com.chocolateFactory.Chocolate.service.UserService;

import lombok.AllArgsConstructor;

@Controller
@RequestMapping("/registration")
public class RegistrationController {
	private UserService userService;
	private final Logger logger = LoggerFactory.getLogger(IdentificationFormController.class);

	
	
	public RegistrationController(UserService userService) {
		super();
		this.userService = userService;
	}

	@ModelAttribute("user")
	public UserRegistrationDto userRegistrationDto() {
		return new UserRegistrationDto();
	}

	@GetMapping
	public String showRegistrationForm() {
		logger.info(" HTTP GET received at /registration");
		return "registration";
	}

	@PostMapping
	public String registerUserAccount(@ModelAttribute("user") UserRegistrationDto registrationDto) {
		logger.info(" HTTP POST received at /registration");
		userService.save(registrationDto);
		return "redirect:/registration?success";
	}
}
