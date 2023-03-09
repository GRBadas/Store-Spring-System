package com.badas.springboot.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

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

import com.badas.springboot.DTO.PedidoDTO;
import com.badas.springboot.exception.ResourceNotFoundException;
import com.badas.springboot.model.Pedido;
import com.badas.springboot.model.Produto;
import com.badas.springboot.repository.ClienteRepository;
import com.badas.springboot.repository.PedidoRepository;
import com.badas.springboot.repository.ProdutoRepository;
import com.badas.springboot.service.PedidoService;
import com.badas.springboot.model.Cliente;

@RestController
@RequestMapping("/api/v1/")
public class PedidoController {
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private PedidoService pedidoService;
	
	@GetMapping("/pedidos")
	public List<Pedido> getAllProdutos() {
		return pedidoRepository.findAll();
	}
	
	@PostMapping("/pedidos")
	public ResponseEntity<Pedido> createPedido(@Valid @RequestBody PedidoDTO pedidoDTO) {
	    Pedido pedidoCriado = pedidoService.createPedido(pedidoDTO);
	    return ResponseEntity.ok().body(pedidoCriado);
	}
	
	@GetMapping("/pedidos/{pedidoid}")
	public ResponseEntity<Pedido> getPedidoById(@PathVariable Long pedidoid) {
		Pedido pedido = pedidoRepository.findById(pedidoid).orElseThrow(() -> new ResourceNotFoundException("Pedido não encontrado" + pedidoid));
		return ResponseEntity.ok(pedido);
	}
	
	@PutMapping("/pedidos/{pedidoid}")
	public ResponseEntity<Pedido> updatePedido(@PathVariable Long pedidoid, @RequestBody PedidoDTO pedidoDetails) {
		Pedido pedido = pedidoService.updatePedido(pedidoid, pedidoDetails);
		return ResponseEntity.ok(pedido);
	}
	
	@DeleteMapping("/pedidos/{pedidoid}")
	public ResponseEntity<Map<String, Boolean>> deletePedido(@PathVariable long pedidoid) {
		Pedido pedido = pedidoRepository.findById(pedidoid).orElseThrow(() -> new ResourceNotFoundException("Pedido não encontrado" + pedidoid));
		pedidoRepository.delete(pedido);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}

}
