package com.assignment.employeeManagement.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.assignment.employeeManagement.service.UserDetailsServiceImpl;

@SuppressWarnings("deprecation")
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


	@Bean
	public UserDetailsService userDetailsService() {
		return new UserDetailsServiceImpl();
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder());

		return authProvider;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers(HttpMethod.POST, "/user", "/role").hasAuthority("ADMIN")
		.antMatchers(HttpMethod.GET, "/listHeaders/*").hasAnyAuthority("ADMIN")
		.antMatchers(HttpMethod.GET, "/employees/*").hasAnyAuthority("USER","ADMIN")
		.antMatchers(HttpMethod.POST, "/addemployees").hasAuthority("ADMIN")				
		.antMatchers(HttpMethod.PUT, "/employees").hasAuthority("ADMIN")
		.antMatchers(HttpMethod.DELETE, "/employees/*").hasAuthority("ADMIN")
		.antMatchers(HttpMethod.GET, "/employees/search/*").hasAuthority("ADMIN")
		.anyRequest().authenticated().and().httpBasic()
		.and().cors().and().csrf().disable();
	}
}
