package com.badas.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.badas.springboot.exception.ResourceNotFoundException;
import com.badas.springboot.model.Produto;
import com.badas.springboot.repository.ProdutoRepository;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	public Produto updateProduto(@PathVariable Long productid, @RequestBody Produto produtoDetails) {
		Produto produto = produtoRepository.findById(productid).orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado" + productid));
		
		produto.setName(produtoDetails.getName());
		produto.setPrice(produtoDetails.getPrice());
		produto.setDescription(produtoDetails.getDescription());
		produto.setQuantity(produtoDetails.getQuantity());
		
		return produtoRepository.save(produto);

	}

}
