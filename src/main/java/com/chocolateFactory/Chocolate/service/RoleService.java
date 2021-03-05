package com.chocolateFactory.Chocolate.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.chocolateFactory.Chocolate.entities.Role;
import com.chocolateFactory.Chocolate.repository.RoleRepository;

@Service
public class RoleService {
private final Logger logger = LoggerFactory.getLogger(RoleService.class);	

@Autowired
RoleRepository roleRepository;

public List<Role> getAllRoles(){
	List <Role> listOfRoles = roleRepository.findAll();
	return listOfRoles;
}

public Boolean roleExistOrNot(Integer id) {
	logger.info("in RoleService roleExistOrNot");
	return roleRepository.existsById(id);
}
public long howManyRolesExists() {
	return roleRepository.count();
}

public Role addRole (Role role) {
	logger.info("in RoleService addRole");
	return roleRepository.save(role);
}

public void deleteRole (Integer id) {
	logger.info("in RoleService deleteRole");
	roleRepository.deleteById(id);
}
public Role getOneRoleById(Integer id) {
	logger.info("in RoleService getOneRoleById");
	return roleRepository.getOne(id);
}
public Role getOneByName(String name) {
	logger.info("in RoleService getOneRoleByName" +name);
	return roleRepository.findByName(name);
}

public void saveRole(Role role) {
	 roleRepository.save(role);
}



}
