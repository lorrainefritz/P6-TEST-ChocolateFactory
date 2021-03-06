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
import com.chocolateFactory.Chocolate.service.RoleService;
import com.chocolateFactory.Chocolate.service.UserServiceImplementation;

@Controller
public class EditUserController {
	@Autowired
	UserServiceImplementation userServiceImp;
	@Autowired
	RoleService roleService;
	private final Logger logger = LoggerFactory.getLogger(IdentificationFormController.class);
	private UserDetails userPrincipal;
	private User user;
	private Collection<Role> userRolesCollection;

	@GetMapping("/editUser")
	public String editUser(Model model, Authentication authentication, Integer id) {
		logger.info("HTTP GET received at /editUser");
		userPrincipal = (UserDetails) authentication.getPrincipal();
		user = userServiceImp.findUserOnEmail(userPrincipal.getUsername());
		logger.info("USER EMAIL=" + user.getEmail());
		model.addAttribute("curentuser", user);
		logger.info("NOMBRE DE ROLES=" + roleService.howManyRolesExists());
		model.addAttribute("globalsRolesList", roleService.getAllRoles());
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
		userPrincipal = (UserDetails) authentication.getPrincipal();
		user = userServiceImp.getOneUserById(id);
		model.addAttribute("curentuser", user);
		logger.info("NOMBRE DE ROLES=" + roleService.howManyRolesExists());
		model.addAttribute("globalsRolesList", roleService.getAllRoles());
		userRolesCollection =user.getRoles();
		logger.info("USER ROLES LISTE SIZE" + userRolesCollection.size()+ " hascode " + userRolesCollection.hashCode()+ userRolesCollection.toString());
		model.addAttribute("roles", userRolesCollection);
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

//Methode Admin	pour les roles ==================> bien verifier que c'est userRole partout 

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/deleteUserRole")
	public String deleteUserRole(Integer id) {
		logger.info("HTTP GET received at deleteUserRole " + id);
		userServiceImp.deleteUserRole(user,id);
		return "redirect:/listeDesUsers";
	}
	
//	@PreAuthorize("hasRole('ADMIN')")
//	@GetMapping("/deleteUserRole")
//	public String deleteUserRole(String roleName) {
//		logger.info("HTTP GET received at deleteUserRole" + roleName);
//		userServiceImp.deleteUserRole(user, roleName);
//		return "redirect:/listeDesUsers";
//	}

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/addUserRole")
	public String addUserRole(Role role) {
		logger.info("HTTP GET received at addUserRole");
		userServiceImp.addUserRole(user, role);
		return "redirect:/listeDesUsers";
	}

}
