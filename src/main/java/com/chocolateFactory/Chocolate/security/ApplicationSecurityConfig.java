package com.chocolateFactory.Chocolate.security;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.chocolateFactory.Chocolate.service.UserService;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {
	private final Logger logger = LoggerFactory.getLogger(ApplicationSecurityConfig.class);

	@Autowired
private UserService userService;
	
	@Bean 
public 	BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	@Bean
public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
		auth.setUserDetailsService(userService);
		auth.setPasswordEncoder(passwordEncoder());
		return auth;
	}
	

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		logger.info("DANS LA CONFIGURATION DANS CONFIGURE DU AUTH MANAGER BUILDER JUSTE AVANT AUTHENTIFICATION PROVIDER");
		auth.authenticationProvider(authenticationProvider());
	}
	
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		logger.info("DANS LA CONFIGURATION DANS CONFIGURE DU AUTH MANAGER BUILDER JUSTE AVANT AUTHENTIFICATION PROVIDER");
//		
//		
//		auth.authenticationProvider(authenticationProvider()); 
//		auth.jdbcAuthentication().passwordEncoder(new PasswordEncoder(){
//				
//				BCryptPasswordEncoder bcryptPasswordEncoder = new BCryptPasswordEncoder(); 
//				CharSequence decryptedPassword;
//				 @Override
//			        public boolean matches(CharSequence rawPassword, String encodedPassword) {
//
//			            // Decoding stuff on the encrypted password sended by the client (rawPassword)
//					 logger.info("Config matches rawPassword : "+rawPassword);
//					 logger.info("Config matches rawPassword : "+encodedPassword);   
//						return bcryptPasswordEncoder.matches(decryptedPassword, encodedPassword);
//			        }
//
//			        @Override
//			        public String encode(CharSequence rawPassword) {
//			        	logger.info("Config matches rawPassword : "+rawPassword);
//			            //Same crypto operation to get the plain password
//			            return bcryptPasswordEncoder.encode(decryptedPassword);
//			        }
//			    });
//	}	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
	
		http
			.csrf().disable()	
			.authorizeRequests()
				.antMatchers(
						"/",
						"/ajouterDesChocolats**",
						"/editChocolate**",
						"/deleteChocolate**",
						"/listeDesChocolats**",
						"/registration**",
						"/login**",
//						"/listeDesUsers**",
						"/imagesAndLogos/**",
						"/style.css",
						"/logos/**"
						).permitAll()
//				.antMatchers("/listeDesUsers**").hasRole("USER")
				.antMatchers("/editUser").authenticated()
				.antMatchers("/listeDesUsers**").hasRole("ADMIN")
				.anyRequest().authenticated()
//				.and()
//				.httpBasic()
				.and()
			.formLogin()
//				.usernameParameter("username").passwordParameter("password")
//				.loginPage("/badLogin") 
				.loginPage("/login") 
//				.loginPage("/login.html") 
//				.loginProcessingUrl("/perform_login")
				.defaultSuccessUrl("/logSuccess")
//				.failureUrl("/login.html?error=true")
				.permitAll()
				.and()
			.logout()
				.invalidateHttpSession(true)
				.clearAuthentication(true)
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.logoutSuccessUrl("/login?logout")
				.permitAll();
//		and()
//		.exceptionHandling()
//		.accessDeniedPage("/accessDenied");
		
	}

}
