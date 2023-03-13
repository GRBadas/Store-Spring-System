package com.badas.springboot.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.badas.springboot.model.UserModel;

public interface UserRepository extends JpaRepository<UserModel, Long>{
	
	Optional<UserModel> findByUsername(String string);

}
