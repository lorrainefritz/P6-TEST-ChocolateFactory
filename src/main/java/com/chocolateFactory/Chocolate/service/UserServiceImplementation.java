package com.chocolateFactory.Chocolate.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.Registration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.chocolateFactory.Chocolate.dto.UserRegistrationDto;
import com.chocolateFactory.Chocolate.entities.User;
import com.chocolateFactory.Chocolate.entities.Role;
import com.chocolateFactory.Chocolate.repository.UserRepository;
import com.chocolateFactory.Chocolate.security.ApplicationSecurityConfig;

@Service
public class UserServiceImplementation implements UserService {

	private final Logger logger = LoggerFactory.getLogger(UserServiceImplementation.class);
	private UserRepository userRepository;
	
	@Autowired
	private RoleService roleService;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	public UserServiceImplementation(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	@Override
	public User save(UserRegistrationDto registrationDto) {
		Role roleUserParDefaut = roleService.getOneRoleById(1);
		logger.info("IN SAVE USER and roleUserParDefaut= " + roleUserParDefaut.getName());
		User user = new User(registrationDto.getName(), passwordEncoder.encode(registrationDto.getPassword()),
				registrationDto.getEmail(), false, Arrays.asList(roleUserParDefaut));
		return userRepository.save(user);
	}	
		
//	
//	@Override
//	public User save(UserRegistrationDto registrationDto) {
//		Role roleUserParDefaut = roleService.getOneRoleById(1);
//		User user = new User(registrationDto.getName(), passwordEncoder.encode(registrationDto.getPassword()),
//				registrationDto.getEmail(), false, Arrays.asList(new Role("ROLE_USER")));
//		return userRepository.save(user);
//	}

//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		logger.info("IN LOADUSERBYUSERNAME");
//		User user = userRepository.findByEmail(username);
//		
//		  UserBuilder builder = null;
//		    if (user != null) {
//		      builder = org.springframework.security.core.userdetails.User.withUsername(username);
//		      String poire = new BCryptPasswordEncoder().encode(user.getPassword());
//		      builder.password(poire);
//		      builder.authorities(user.getAuthorities());
//		      logger.info("utilisateur email "+user.getEmail());
//				logger.info("utilisateur password "+user.getPassword());
//				logger.info("utilisateur password poire "+poire);
//				logger.info("utilisateur role "+user.getAuthorities());
//				logger.info("utilisateur name "+user.getName());
//				logger.info("utilisateur id "+user.getId());      
//		    } else {
//		      throw new UsernameNotFoundException("User not found.");
//		    }
//		
//		
//		
//		return builder.build();
//	}		

//==============================> truc qui ne marche pas mais test en cours	
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		logger.info("IN LOADUSERBYUSERNAME");
//		User user = userRepository.findByEmail(username);
//		logger.info("IN LOADUSERBYUSERNAME AFTER USER CREATION VIA FINDBYEMAIL");
//		if(user== null) {
//			throw new UsernameNotFoundException("Invalid email or Password");
//		}
//		logger.info("email "+username);
//		logger.info("utilisateur email "+user.getEmail());
//		logger.info("utilisateur password "+user.getPassword());
//		logger.info("utilisateur role "+user.getAuthorities());
//		logger.info("utilisateur name "+user.getName());
//		logger.info("utilisateur id "+user.getId());
////		return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getName()
////				,(user.getAuthorities()));
//		return user;
//	}	

//============================>test ac wrapper shopmeUserDetails	
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		logger.info("IN LOADUSERBYUSERNAME");
////		User user = userRepository.getUserByEmail(email);
//		User user = userRepository.findByEmail(username);
//		logger.info("IN LOADUSERBYUSERNAME AFTER USER CREATION VIA FINDBYEMAIL");
//		
//		if(user== null) {
//			throw new UsernameNotFoundException("Invalid email or Password");
//		}
//		logger.info("email "+username);
//		logger.info("utilisateur email "+user.getEmail());
//		logger.info("utilisateur password "+user.getPassword());
//		logger.info("utilisateur name "+user.getName());
//		logger.info("utilisateur id "+user.getId());
////		return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getName()
////				,mapRolesToAuthorities(user.getRoles()));
//		return new ShopMeUserDetails(user);
//	}	

//============================>Truc qui marche
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		logger.info("IN LOADUSERBYUSERNAME");
		User user = userRepository.findByEmail(username);
		logger.info("IN LOADUSERBYUSERNAME AFTER USER CREATION VIA FINDBYEMAIL");
		if (user == null) {
			throw new UsernameNotFoundException("Invalid email or Password");
		}
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
				mapRolesToAuthorities(user.getRoles()));
	}

	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}

	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	public User getOneUserById(Integer id) {
		return userRepository.getOne(id);
	}

	public void deleteUser(Integer id) {
		userRepository.deleteById(id);
	}

	public User findUserOnEmail(String email) {
		return userRepository.findByEmail(email);
	}

//methode de modification à la volée utilisable par la perso connectée
	public User emailModification(User user, String email) {
		user.setEmail(email);
		return userRepository.save(user);
	}

	public User nameModification(User user, String name) {
		user.setName(name);
		return userRepository.save(user);

	}

	public User passwordModification(User user, String password) {
		logger.info("in passwordModification" + password);
		user.setPassword(passwordEncoder.encode(password));
		return userRepository.save(user);
	}

//// méthode d'admin pour admin les roles des user bien changer partout en user
	public User roleUserModification(User user, Collection<Role> roles) {
		logger.info("in roleModification" + roles);
		user.setRoles(roles);
		return userRepository.save(user);
	}

	public User deleteUserRole(User user, Integer id) {
		logger.info("in deleteRole with roleName = " + id);
		List<Role> useroleColle= (List<Role>) user.getRoles();
		Role role = useroleColle.get(id);
		useroleColle.remove(role);
		user.setRoles(useroleColle);
		return userRepository.save(user);
	}	
	
	
	
	
	
	
//	public User deleteUserRole(User user, String roleName) {
//		logger.info("in deleteRole with roleName = " + roleName);
//		Role role =roleService.getOneByName(roleName);
//		logger.info("in deleteRole with role = " + role.getName()+role.getId() +"and user is " + user.getName());
//		List<Role> roles = (List<Role>)user.getRoles();
//		Role rolo = roles.get(1);
//		logger.info("in deleteRole roles "
//				+ "contains role = " + roles.contains(role)+" "+ role.getName()+ " " +role.getId() 
//				+ " contains rolo = " +roles.contains(rolo) +" "+ rolo.getName()+ " " +rolo.getId());			
//		return userRepository.save(user);
//	}
	

	public User addUserRole(User user, Role role) {
		logger.info("in addRole" + role);
		Collection<Role> roles = user.getRoles();	
		roles.add(role);
		return userRepository.save(user);
	}

}
