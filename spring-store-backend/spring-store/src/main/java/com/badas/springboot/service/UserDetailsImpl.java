package com.badas.springboot.service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.badas.springboot.model.User;

import lombok.Data;

@Data
public class UserDetailsImpl implements UserDetails {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	
	private String username;
	 
	private String password;
	
	private Collection<? extends GrantedAuthority> authorities;
	
	public UserDetailsImpl(Long id, String username, String password, Collection<? extends GrantedAuthority> authorities) {
		this.id = id;
	    this.username = username;
	    this.password = password;
	    this.authorities = authorities;
	}
	
	public static UserDetailsImpl build(User user) {
		 List<GrantedAuthority> authorities = user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
		 
		 return new UserDetailsImpl(
				 	user.getId(),
		            user.getUsername(),
		            user.getPassword(),
		            authorities);
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}
}
