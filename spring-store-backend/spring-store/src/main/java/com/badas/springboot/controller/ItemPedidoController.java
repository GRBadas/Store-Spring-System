package com.badas.springboot.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.badas.springboot.DTO.ItemPedidoDTO;
import com.badas.springboot.model.ItemPedido;
import com.badas.springboot.repository.ItemPedidoRepository;
import com.badas.springboot.repository.PedidoRepository;
import com.badas.springboot.repository.ProdutoRepository;
import com.badas.springboot.service.ItemPedidoService;

@RestController
@RequestMapping("/api/v1/")
public class ItemPedidoController {
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	@Autowired
	private ItemPedidoService itemPedidoService;
	
	@GetMapping("/itempedido")
	public List<ItemPedido> getAllPedidos() {
		return itemPedidoRepository.findAll();
	}
	
	@GetMapping("/itempedido/{itemPedidoId}")
	public ResponseEntity<ItemPedido> getItemPedidoById(@PathVariable Long itemPedidoId) {
		ItemPedido itemPedido = itemPedidoService.getItemPedidoById(itemPedidoId);
		return ResponseEntity.ok(itemPedido);
	}
	
	@PostMapping("/itempedido")
	public ResponseEntity<ItemPedido> createItemPedido(@Valid @RequestBody ItemPedidoDTO itemPedidoDTO) {
		ItemPedido itemCriado = itemPedidoService.createItemPedido(itemPedidoDTO);
		return ResponseEntity.ok().body(itemCriado);
	}
	
	@PutMapping("/itempedido/{itemPedidoId}")
	public ResponseEntity<ItemPedido> updateItemPedido(@PathVariable Long itemPedidoId, @RequestBody ItemPedidoDTO itemPedidoDetails) {
		ItemPedido itemPedidoAtualizado = itemPedidoService.updateItemPedido(itemPedidoId, itemPedidoDetails);
		return ResponseEntity.ok(itemPedidoAtualizado);
	}
	
	@DeleteMapping("/itempedido/{itemPedidoId}")
	public ResponseEntity<Map<String, Boolean>> deleteItemPedido(@PathVariable Long itemPedidoId) {
		itemPedidoService.deleteItemPedido(itemPedidoId);
		
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}

}
