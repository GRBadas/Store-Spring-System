package com.badas.springboot.service;

import java.util.Optional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.badas.springboot.DTO.ItemPedidoDTO;
import com.badas.springboot.exception.ResourceNotFoundException;
import com.badas.springboot.model.ItemPedido;
import com.badas.springboot.model.Pedido;
import com.badas.springboot.model.Produto;
import com.badas.springboot.repository.ItemPedidoRepository;
import com.badas.springboot.repository.PedidoRepository;
import com.badas.springboot.repository.ProdutoRepository;

@Service
public class ItemPedidoService {
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	public ItemPedido getItemPedidoById(@PathVariable Long itemPedidoId) {
		return itemPedidoRepository.findById(itemPedidoId).orElseThrow(() ->
		new ResourceNotFoundException("Item não encontrado"));
	}
	
	public ItemPedido createItemPedido(@Valid @RequestBody ItemPedidoDTO itemPedidoDTO) {
		Optional<Pedido> optionalPedido = pedidoRepository.findById(itemPedidoDTO.getPedidoId());
		Optional<Produto> optionalProduto = produtoRepository.findById(itemPedidoDTO.getProductId());
		
		if(optionalPedido.isEmpty()) {
			throw new ResourceNotFoundException("Item não encontrado");	
			}
		if(optionalProduto.isEmpty()) {
			throw new ResourceNotFoundException("Item não encontrado");			
			}
		Pedido pedido = optionalPedido.get();
		Produto produto = optionalProduto.get();
		
		ItemPedido novoItemPedido = new ItemPedido();
		novoItemPedido.setQuantity(itemPedidoDTO.getQuantity());
		novoItemPedido.setUnitaryValue(itemPedidoDTO.getUnitaryValue());
		novoItemPedido.setPedido(pedido);
		novoItemPedido.setProduto(produto);
		
		return itemPedidoRepository.save(novoItemPedido);
	}
	
	public ItemPedido updateItemPedido(@PathVariable Long itemPedidoId, @RequestBody ItemPedidoDTO itemPedidoDetails) {
		
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
		
		return itemPedidoRepository.save(itemPedido);
	}
	
	public void deleteItemPedido(@PathVariable Long itemPedidoId) {
		
		ItemPedido itemPedido = itemPedidoRepository.findById(itemPedidoId).orElseThrow(() ->
		new ResourceNotFoundException("Item não encontrado"));
		
		itemPedidoRepository.delete(itemPedido);
	}

}
