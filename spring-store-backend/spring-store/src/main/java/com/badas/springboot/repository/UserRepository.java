package com.badas.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.badas.springboot.model.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
