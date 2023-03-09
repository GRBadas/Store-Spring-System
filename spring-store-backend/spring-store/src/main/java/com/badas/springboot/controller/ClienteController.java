package com.badas.springboot.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.badas.springboot.exception.ResourceNotFoundException;
import com.badas.springboot.model.Cliente;
import com.badas.springboot.repository.ClienteRepository;
import com.badas.springboot.service.ClienteService;

@RequestMapping("/api/v1/")
@RestController
public class ClienteController {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired ClienteService clienteService;
	
	@GetMapping("/clients")
	public List<Cliente> getAllClientes(){
		return clienteRepository.findAll();
	}
	
	@PostMapping("/clients")
	public Cliente createClient(@RequestBody Cliente client) {
		return clienteRepository.save(client);
	}
	
	@GetMapping("/clients/{clientid}")
	public ResponseEntity<Cliente> getClientById(@PathVariable Long clientid) {
		Cliente cliente= clienteService.getClientById(clientid);
		return ResponseEntity.ok(cliente);
	}
	
	@PutMapping("/clients/{clientid}")
	public ResponseEntity<Cliente> updateProduto(@PathVariable Long clientid, @RequestBody Cliente clienteDetails) {
		Cliente updatedClient = clienteService.updateProduto(clientid, clienteDetails);
		return ResponseEntity.ok(updatedClient);
	}
	
	@DeleteMapping("/clients/{clientid}")
	public ResponseEntity<Map<String, Boolean>> deleteClient(@PathVariable long clientid) {
		clienteService.deleteClient(clientid);
		
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}

}
