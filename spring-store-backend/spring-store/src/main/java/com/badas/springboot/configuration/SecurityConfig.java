package com.badas.springboot.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.badas.springboot.service.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Autowired
	private UserDetailsServiceImpl userDetailsService;
	
	protected void configure(HttpSecurity http) throws Exception {
		 http
         .authorizeRequests()
             .antMatchers("/admin/**").hasRole("ADMIN")
             .antMatchers("/user/**").hasRole("USER")
             .anyRequest().authenticated()
             .and()
         .formLogin()
             .loginPage("/login")
             .defaultSuccessUrl("/home")
             .permitAll()
             .and()
         .logout()
             .permitAll();
	}
	
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }
	
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
