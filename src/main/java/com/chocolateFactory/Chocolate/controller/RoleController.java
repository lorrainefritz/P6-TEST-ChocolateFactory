package com.chocolateFactory.Chocolate.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.chocolateFactory.Chocolate.entities.Chocolate;
import com.chocolateFactory.Chocolate.entities.Role;
import com.chocolateFactory.Chocolate.service.RoleService;

@Controller
public class RoleController {
	@Autowired
	RoleService roleService;
	private final Logger logger = LoggerFactory.getLogger(RoleController.class);

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/editRole")
	public String showRoleList(Model model) {
		logger.info("HTTP GET received at /editRoleList");
		model.addAttribute("roles", roleService.getAllRoles());
		return ("editRole");
	}

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/deleteRole")
	public String deleteRole(Integer id) {
		logger.info("HTTP GET received at /deleteRole");
		roleService.deleteRole(id);
		return "redirect:/editRole";
	}
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping(path="/editRoleName")
	public String editRoleName(Integer id,Model model) {
		logger.info("HTTP GET received at /editRole" + id);
		Role role = roleService.getOneRoleById(id);
		model.addAttribute("role",role);
		return "addRole"; 
	}
	
}
