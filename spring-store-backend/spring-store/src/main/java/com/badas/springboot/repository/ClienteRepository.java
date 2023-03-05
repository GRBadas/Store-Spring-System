package com.badas.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.badas.springboot.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{

}
