package com.chocolateFactory.Chocolate.service;

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
	private BCryptPasswordEncoder passwordEncoder;

	public UserServiceImplementation(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	@Override
	public User save(UserRegistrationDto registrationDto) {
		User user = new User(registrationDto.getName(), passwordEncoder.encode(registrationDto.getPassword()),
				registrationDto.getEmail(), false, Arrays.asList(new Role("ROLE_USER")));
		return userRepository.save(user);
	}

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
	public User emailModification(UserDetails userPrincipal, String email) {
		User user = findUserOnEmail(userPrincipal.getUsername());
		user.setEmail(email);
		return userRepository.save(user);
	}

	public User nameModification(UserDetails userPrincipal, String name) {
		User user = findUserOnEmail(userPrincipal.getUsername());
		user.setName(name);
		return userRepository.save(user);
		
	}
	public User passwordModification(UserDetails userPrincipal, String password) {
		User user = findUserOnEmail(userPrincipal.getUsername());
		
		user.setPassword(passwordEncoder.encode(password));
		return userRepository.save(user);
	}
// méthode d'admin pour admin les roles
	public User roleModification(UserDetails userPrincipal, Collection<Role> role) {
		User user = findUserOnEmail(userPrincipal.getUsername());
		user.setRoles(role);
		return userRepository.save(user);
	}
	public User roleAdd(UserDetails userPrincipal, Role role) {
		User user = findUserOnEmail(userPrincipal.getUsername());
		Collection<Role> collectionOfrole= user.getRoles();
		collectionOfrole.add(role);
		return userRepository.save(user);
	}
	
	

}
