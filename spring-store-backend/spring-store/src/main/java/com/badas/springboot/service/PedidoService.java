package com.badas.springboot.service;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.badas.springboot.DTO.PedidoDTO;
import com.badas.springboot.exception.ResourceNotFoundException;
import com.badas.springboot.model.Cliente;
import com.badas.springboot.model.Pedido;
import com.badas.springboot.model.Produto;
import com.badas.springboot.repository.ClienteRepository;
import com.badas.springboot.repository.PedidoRepository;
import com.badas.springboot.repository.ProdutoRepository;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	public Pedido createPedido(@Valid @RequestBody PedidoDTO pedidoDTO) {
		Optional<Cliente> optionalCliente = clienteRepository.findById(pedidoDTO.getClientId());
		Optional<Produto> optionalProduto = produtoRepository.findById(pedidoDTO.getProductId());
	   
	    if(optionalCliente.isEmpty()) {
	    	throw new ResourceNotFoundException("Cliente não encontrado");	
	    }
	    if(optionalProduto.isEmpty()) {
	    	throw new ResourceNotFoundException("Produto não encontrado");	
	    }
	    Produto produto = optionalProduto.get();
	    Cliente cliente = optionalCliente.get();
	    
	    Pedido novoPedido = new Pedido();
	    novoPedido.setDate(pedidoDTO.getDate());
	    novoPedido.setTotalValue(pedidoDTO.getTotalValue());
	    novoPedido.setStatus(pedidoDTO.getStatus());
	    novoPedido.setProduto(produto);
	    novoPedido.setCliente(cliente);

	    return pedidoRepository.save(novoPedido);

	}
	
	public Pedido updatePedido(@PathVariable Long pedidoid, @RequestBody PedidoDTO pedidoDetails) {
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
		
		return pedidoRepository.save(pedido);

	}

}
