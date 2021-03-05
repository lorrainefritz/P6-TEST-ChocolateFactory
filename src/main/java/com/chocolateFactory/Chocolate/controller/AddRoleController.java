package com.chocolateFactory.Chocolate.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.chocolateFactory.Chocolate.entities.Role;
import com.chocolateFactory.Chocolate.service.RoleService;

@Controller
public class AddRoleController {
	@Autowired
	RoleService roleService;
	private final Logger logger = LoggerFactory.getLogger(RoleController.class);

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/addRole")
	public String showRoleForm(Model model) {
		logger.info("HTTP GET received at /addRoleToList ");
		model.addAttribute("role", new Role());
		return ("addRole");
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/addRole")
	public String submitRoleForm(@ModelAttribute("role") Role role) {
		logger.info("HTTP POST received at /addRoleToList");
		logger.info(
				" On ajoute le role avec les valeurs suivantes ; id : " + role.getId() + " nom : " + role.getName());
		roleService.addRole(role);
		return ("redirect:/editRole");
	}
}
