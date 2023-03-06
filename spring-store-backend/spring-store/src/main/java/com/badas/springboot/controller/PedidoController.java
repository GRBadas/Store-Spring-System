package com.badas.springboot.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.badas.springboot.exception.ResourceNotFoundException;
import com.badas.springboot.model.Pedido;
import com.badas.springboot.repository.ClienteRepository;
import com.badas.springboot.repository.PedidoRepository;
import com.badas.springboot.model.Cliente;

@RestController
@RequestMapping("/api/v1/")
public class PedidoController {
	
	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired ClienteRepository clienteRepository;
	
	@GetMapping("/pedidos")
	public List<Pedido> getAllProdutos() {
		return pedidoRepository.findAll();
	}
	
	@PostMapping("/pedidos")
	public ResponseEntity<Pedido> createProduto(@RequestParam Long clientid, @RequestBody Pedido pedido) {
	    Optional<Cliente> optionalClient = clienteRepository.findById(clientid);
	    if (!optionalClient.isPresent()) {
	        throw new ResourceNotFoundException("Cliente não encontrado com o id :" + clientid);
	    }
	    pedido.setClient(optionalClient.get());
	    Pedido novoPedido = pedidoRepository.save(pedido);
	    return ResponseEntity.status(HttpStatus.CREATED).body(novoPedido);
	}
	
	@GetMapping("/pedidos/{pedidoid}")
	public ResponseEntity<Pedido> getProductById(@PathVariable Long pedidoid) {
		Pedido pedido = pedidoRepository.findById(pedidoid).orElseThrow(() -> new ResourceNotFoundException("Pedido não encontrado" + pedidoid));
		return ResponseEntity.ok(pedido);
	}
	
	@PutMapping("/pedidos/{pedidoid}")
	public ResponseEntity<Pedido> updateProduto(@PathVariable Long pedidoid, @RequestBody Pedido pedidoDetails) {
		Pedido pedido = pedidoRepository.findById(pedidoid).orElseThrow(() -> new ResourceNotFoundException("Pedido não encontrado" + pedidoid));
		
		pedido.setClient(pedidoDetails.getClient());
		pedido.setDate(pedidoDetails.getDate());
		pedido.setStatus(pedidoDetails.getStatus());
		pedido.setTotalValue(pedidoDetails.getTotalValue());
		
		Pedido updatedOrder = pedidoRepository.save(pedido);
		return ResponseEntity.ok(updatedOrder);
	}
	
	@DeleteMapping("/pedidos/{pedidoid}")
	public ResponseEntity<Map<String, Boolean>> deleteProduct(@PathVariable long pedidoid) {
		Pedido pedido = pedidoRepository.findById(pedidoid).orElseThrow(() -> new ResourceNotFoundException("Pedido não encontrado" + pedidoid));
		pedidoRepository.delete(pedido);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}

}
