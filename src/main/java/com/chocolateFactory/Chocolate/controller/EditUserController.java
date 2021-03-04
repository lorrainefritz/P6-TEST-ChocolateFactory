package com.chocolateFactory.Chocolate.controller;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.chocolateFactory.Chocolate.entities.Role;
import com.chocolateFactory.Chocolate.entities.User;
import com.chocolateFactory.Chocolate.service.UserServiceImplementation;

@Controller
public class EditUserController {
	@Autowired
	UserServiceImplementation userServiceImp;
	private final Logger logger = LoggerFactory.getLogger(IdentificationFormController.class);
	private UserDetails userPrincipal;
	private User user;

	@GetMapping("/editUser")
	public String editUser(Model model, Authentication authentication, Integer id) {
		logger.info("HTTP GET received at /editUser");
		userPrincipal =(UserDetails)authentication.getPrincipal();
		user = userServiceImp.findUserOnEmail(userPrincipal.getUsername());
		logger.info("USER EMAIL=" + user.getEmail());
		model.addAttribute("curentuser", user);
		logger.info("USER EMAIL=" + user.getEmail());
		
//		logger.info("USER EMAIL=" + user.getEmail());
//		logger.info("USER NAME =" + currentLoggedUser.getName());
//		logger.info("USER PASSWORD =" + currentLoggedUser.getPassword());
//		logger.info("USER NAME =" + currentLoggedUser.getId());
//		logger.info("USER AUTHORITHIES =" + currentLoggedUser.getAuthorities());
//		logger.info("USER AUTHORITHIES =" + currentLoggedUser.getRoles());
//		model.addAttribute("curentuser", currentLoggedUser);

		return "editUser";
	}
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/editUserByIdWhenAdmin")
	public String editUserByIdWhenAdmin(Model model, Integer id, Authentication authentication) {
		logger.info("HTTP GET received at /editUserByIdWhenAdmin with user id = " + id);
		userPrincipal =(UserDetails)authentication.getPrincipal();
		user = userServiceImp.getOneUserById(id);
		model.addAttribute("curentuser", user);
		logger.info("USER ROLES"+ user.getRoles());
		model.addAttribute("roles",user.getRoles());
		return "editUser";
	}

	@PostMapping("/editName")
	public String editName(String name) {
		logger.info("HTTP GET received at /editEmail");
		userServiceImp.nameModification(user, name);
		return "redirect:/logSuccess";
	}

	@PostMapping("/editEmail")
	public String editEmail(String email) {
		logger.info("HTTP GET received at /editEmail");
		userServiceImp.emailModification(user, email);
		return "redirect:/logout";
	}

	@PostMapping("/editPassword")
	public String editPassword(String password) {
		logger.info("HTTP GET received at /editPassword ");
		logger.info("PASSWORD  " + password);
		logger.info("NAME  " + user.getName());
		userServiceImp.passwordModification(user, password);
		return "redirect:/logSuccess";
	}
	
//Methode Admin	pour les roles
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/editRole")
	public String editRole(Collection<Role> role, Model model) {
		logger.info("HTTP GET received at /editRole");
		userServiceImp.roleModification(user, role);
		return "redirect:/listeDesUsers";
	}

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/deleteRole")
	public String deleteRole(Integer id) {
		logger.info("HTTP GET received at deleteRole");
		userServiceImp.deleteRole(user,id);
		return "redirect:/listeDesUsers";
	}
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/addRole")
	public String addRole(Role role) {
		logger.info("HTTP GET received at addRole");
		userServiceImp.addRole(user, role);
		return "redirect:/listeDesUsers";
	}
	
}
