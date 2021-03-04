package com.chocolateFactory.Chocolate.controller;

import java.security.Principal;
import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.chocolateFactory.Chocolate.entities.Chocolate;
import com.chocolateFactory.Chocolate.entities.Role;
import com.chocolateFactory.Chocolate.entities.User;
import com.chocolateFactory.Chocolate.service.UserServiceImplementation;

@Controller
public class IdentificationFormController {
	@Autowired
	UserServiceImplementation userServiceImp;
	private final Logger logger = LoggerFactory.getLogger(IdentificationFormController.class);
	private UserDetails userPrincipal;
	private User currentLoggedUser ;

	@GetMapping("/login")
	public String showLoginForm() {
		logger.info(" HTTP GET received at /login");
		return "login";
	}


	@GetMapping("/logSuccess")
	public String managersStatusCheck(Authentication authentication, Model model) {
		logger.info(" HTTP GET received at /logSuccess");
	    userPrincipal = (UserDetails)authentication.getPrincipal();
	    currentLoggedUser = userServiceImp.findUserOnEmail(userPrincipal.getUsername());;
	    model.addAttribute("curentuser",currentLoggedUser);
	    model.addAttribute("principal",userPrincipal);
//	    logger.info("USERNAME ="+ userPrincipal.getUsername());  
//	    logger.info("PASSWORD ="+ userPrincipal.getPassword());
//	    logger.info("AUTHORITIES ="+ userPrincipal.getAuthorities());
//	    logger.info("NAME En passant par authentication ="+ authentication.getName());
//	    logger.info("DETAILS En passant par authentication ="+ authentication.getDetails());
//	    logger.info("USER EMAIL=" + currentLoggedUser.getEmail() );
//	    logger.info("USER NAME ="+ currentLoggedUser.getName() );
//	    logger.info("USER PASSWORD ="+ currentLoggedUser.getPassword() );
//	    logger.info("USER NAME ="+ currentLoggedUser.getId());
	  	    return "logSuccess";
	}
	

	
//	@GetMapping("/logSuccess")
//	public ModelAndView showSuccessPage(Principal principal) {
//		logger.info(" HTTP GET received at /logSuccess");
//		UserDetails userdetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//		ModelAndView mv = new ModelAndView("logSucess");
//		mv.addObject("fullname",userdetails.getUsername());
//		final String loggedInUserName = principal.getName() ;
//		return mv;
//	}

//	@GetMapping("/logSuccess")
//	public String showSuccessPage() {
//		logger.info(" HTTP GET received at /logSuccess");
//		return "logSuccess";
//	}
}
