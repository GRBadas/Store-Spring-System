package com.badas.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.badas.springboot.model.Cliente;
import com.badas.springboot.repository.ClienteRepository;

@RequestMapping("/api/v1/")
@RestController
public class ClienteController {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@GetMapping("/clients")
	public List<Cliente> getAllClientes(){
		return clienteRepository.findAll();
	}
	
	@PostMapping("/clients")
	public Cliente createClient(@RequestBody Cliente client) {
		return clienteRepository.save(client);
	}

}
