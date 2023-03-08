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
	
	@GetMapping("/pedidos")
	public List<Pedido> getAllProdutos() {
		return pedidoRepository.findAll();
	}
	
	@PostMapping("/pedidos")
	public ResponseEntity<Pedido> createPedido(@Valid @RequestBody PedidoDTO pedidoDTO) {
		Optional<Cliente> optionalCliente = clienteRepository.findById(pedidoDTO.getClientId());
		Optional<Produto> optionalProduto = produtoRepository.findById(pedidoDTO.getProductId());
	   
	    if(optionalCliente.isEmpty()) {
	    	return ResponseEntity.notFound().build();
	    }
	    if(optionalProduto.isEmpty()) {
	    	return ResponseEntity.notFound().build();
	    }
	    Produto produto = optionalProduto.get();
	    Cliente cliente = optionalCliente.get();
	    
	    Pedido novoPedido = new Pedido();
	    novoPedido.setDate(pedidoDTO.getDate());
	    novoPedido.setTotalValue(pedidoDTO.getTotalValue());
	    novoPedido.setStatus(pedidoDTO.getStatus());
	    novoPedido.setProduto(produto);
	    novoPedido.setCliente(cliente);

	    Pedido pedidoCriado = pedidoRepository.save(novoPedido);
	    return ResponseEntity.ok().body(pedidoCriado);
	}
	
	@GetMapping("/pedidos/{pedidoid}")
	public ResponseEntity<Pedido> getPedidoById(@PathVariable Long pedidoid) {
		Pedido pedido = pedidoRepository.findById(pedidoid).orElseThrow(() -> new ResourceNotFoundException("Pedido não encontrado" + pedidoid));
		return ResponseEntity.ok(pedido);
	}
	
	@PutMapping("/pedidos/{pedidoid}")
	public ResponseEntity<Pedido> updatePedido(@PathVariable Long pedidoid, @RequestBody PedidoDTO pedidoDetails) {
		Pedido pedido = pedidoRepository.findById(pedidoid).orElseThrow(() -> new ResourceNotFoundException("Pedido não encontrado" + pedidoid));
		
		Cliente cliente = clienteRepository.findById(pedidoDetails.getClientId())
			    .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado" + pedidoDetails.getClientId()));
		Produto produto = produtoRepository.findById(pedidoDetails.getProductId())
				.orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado " + pedidoDetails.getProductId()));
		pedido.setProduto(produto);
		pedido.setCliente(cliente);
		pedido.setDate(pedidoDetails.getDate());
		pedido.setStatus(pedidoDetails.getStatus());
		pedido.setTotalValue(pedidoDetails.getTotalValue());
		
		Pedido updatedOrder = pedidoRepository.save(pedido);
		return ResponseEntity.ok(updatedOrder);
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
