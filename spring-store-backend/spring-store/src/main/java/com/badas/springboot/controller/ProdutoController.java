package com.badas.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.badas.springboot.model.Produto;
import com.badas.springboot.repository.ProdutoRepository;
import com.badas.springboot.exception.ResourceNotFoundException;

@RestController
@RequestMapping("/api/v1/")
public class ProdutoController {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@GetMapping("/products")
	public List<Produto> getAllProdutos() {
		return produtoRepository.findAll();
	}
	
	@PostMapping("/products")
	public Produto createProduto(@RequestBody Produto produto) {
		return produtoRepository.save(produto);
	}
	
	@GetMapping("/products/{productid}")
	public ResponseEntity<Produto> getTaskById(@PathVariable Long productid) {
		Produto produto = produtoRepository.findById(productid).orElseThrow(() -> new ResourceNotFoundException("Tarefa não encontrada" + productid));
		return ResponseEntity.ok(produto);
	}
	
}
