package com.badas.springboot.configuration.security;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.badas.springboot.model.UserModel;
import com.badas.springboot.repository.UserRepository;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;

	public UserDetailsServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserModel userModel = userRepository.findByUsername(username).
				orElseThrow(() -> new UsernameNotFoundException("Usuário " + username + "Não encontrado."));
		return User.builder()
				.username(userModel.getUsername())
				.password(userModel.getPassword())
				.authorities(userModel.getAuthorities())
				.build();
	}

}
