package com.badas.springboot.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.badas.springboot.DTO.ItemPedidoDTO;
import com.badas.springboot.exception.ResourceNotFoundException;
import com.badas.springboot.model.ItemPedido;
import com.badas.springboot.model.Pedido;
import com.badas.springboot.model.Produto;
import com.badas.springboot.repository.ItemPedidoRepository;
import com.badas.springboot.repository.PedidoRepository;
import com.badas.springboot.repository.ProdutoRepository;

@RestController
@RequestMapping("/api/v1/")
public class ItemPedidoController {
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@GetMapping("/itempedido")
	public List<ItemPedido> getAllPedidos() {
		return itemPedidoRepository.findAll();
	}
	
	@GetMapping("/itempedido/{itemPedidoId}")
	public ResponseEntity<ItemPedido> getItemPedidoById(@PathVariable Long itemPedidoId) {
		ItemPedido itemPedido = itemPedidoRepository.findById(itemPedidoId).orElseThrow(() ->
		new ResourceNotFoundException("Item não encontrado"));
		return ResponseEntity.ok(itemPedido);
	}
	
	@PostMapping("/itempedido")
	public ResponseEntity<ItemPedido> createItemPedido(@Valid @RequestBody ItemPedidoDTO itemPedidoDTO) {
		Optional<Pedido> optionalPedido = pedidoRepository.findById(itemPedidoDTO.getPedidoId());
		Optional<Produto> optionalProduto = produtoRepository.findById(itemPedidoDTO.getProductId());
		
		if(optionalPedido.isEmpty()) {
			return ResponseEntity.notFound().build();		
			}
		if(optionalProduto.isEmpty()) {
			return ResponseEntity.notFound().build();		
			}
		Pedido pedido = optionalPedido.get();
		Produto produto = optionalProduto.get();
		
		ItemPedido novoItemPedido = new ItemPedido();
		novoItemPedido.setQuantity(itemPedidoDTO.getQuantity());
		novoItemPedido.setUnitaryValue(itemPedidoDTO.getUnitaryValue());
		novoItemPedido.setPedido(pedido);
		novoItemPedido.setProduto(produto);
		
		ItemPedido itemCriado = itemPedidoRepository.save(novoItemPedido);
		return ResponseEntity.ok().body(itemCriado);
	}
	
	@PutMapping("/itempedido/{itemPedidoId}")
	public ResponseEntity<ItemPedido> updateItemPedido(@PathVariable Long itemPedidoId, @RequestBody ItemPedidoDTO itemPedidoDetails) {
		
		ItemPedido itemPedido = itemPedidoRepository.findById(itemPedidoId).orElseThrow(() ->
		new ResourceNotFoundException("Item não encontrado"));
		
		Pedido pedido = pedidoRepository.findById(itemPedidoDetails.getPedidoId()).orElseThrow(() ->
		new ResourceNotFoundException("Pedido não encontrado"));
		Produto produto = produtoRepository.findById(itemPedidoDetails.getProductId())
				.orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado " + itemPedidoDetails.getProductId()));
		pedido.setProduto(produto);
		
		itemPedido.setPedido(pedido);
		itemPedido.setProduto(produto);
		itemPedido.setQuantity(itemPedidoDetails.getQuantity());
		itemPedido.setUnitaryValue(itemPedidoDetails.getUnitaryValue());
		
		ItemPedido itemPedidoAtualizado = itemPedidoRepository.save(itemPedido);
		return ResponseEntity.ok(itemPedidoAtualizado);
	}
	
	@DeleteMapping("/itempedido/{itemPedidoId}")
	public ResponseEntity<Map<String, Boolean>> deleteItemPedido(@PathVariable Long itemPedidoId) {
	
		ItemPedido itemPedido = itemPedidoRepository.findById(itemPedidoId).orElseThrow(() ->
		new ResourceNotFoundException("Item não encontrado"));
		itemPedidoRepository.delete(itemPedido);
		
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}

}
