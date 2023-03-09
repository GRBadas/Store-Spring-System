package com.badas.springboot.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.badas.springboot.exception.ResourceNotFoundException;
import com.badas.springboot.model.Cliente;
import com.badas.springboot.repository.ClienteRepository;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	public Cliente getClientById(@PathVariable Long clientid) {
		return clienteRepository.findById(clientid).orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado" + clientid));

	}
	
	public Cliente updateProduto(@PathVariable Long clientid, @RequestBody Cliente clienteDetails) {
		Cliente cliente = clienteRepository.findById(clientid).orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado" + clientid));
		
		cliente.setName(clienteDetails.getName());
		cliente.setEmail(clienteDetails.getEmail());
		cliente.setPassword(clienteDetails.getPassword());
		cliente.setSurname(clienteDetails.getSurname());
		
		return clienteRepository.save(cliente);
	}
	
	public void deleteClient(@PathVariable long clientid) {
		Cliente cliente = clienteRepository.findById(clientid).orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado" + clientid));
		clienteRepository.delete(cliente);
	}
}
