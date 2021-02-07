package com.chocolateFactory.Chocolate.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.chocolateFactory.Chocolate.entities.User;


@Controller
public class IdentificationFormController {

	private final Logger logger = LoggerFactory.getLogger(IdentificationFormController.class);


	@GetMapping("/login")
	public String showLoginForm() {
		logger.info(" HTTP GET received at /login");
		return "login";
	}

//	@GetMapping("/logSuccess")
//	public String showSuccessPage() {
//		logger.info(" HTTP GET received at /logSuccess");
//		return "logSuccess";
//	}
}
