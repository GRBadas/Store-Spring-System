package com.badas.springboot.service;

import javax.transaction.Transactional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.badas.springboot.model.User;
import com.badas.springboot.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	private UserRepository userRepository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Usuário" + username + "Não encontrado"));
		
		return UserDetailsImpl.build(user);
	}
}
