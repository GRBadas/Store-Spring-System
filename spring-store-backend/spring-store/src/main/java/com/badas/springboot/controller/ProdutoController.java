package com.badas.springboot.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.badas.springboot.model.Produto;
import com.badas.springboot.repository.ProdutoRepository;
import com.badas.springboot.service.ProdutoService;
import com.badas.springboot.exception.ResourceNotFoundException;

@RestController
@RequestMapping("/api/v1/")
public class ProdutoController {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired ProdutoService produtoService;
	
	@GetMapping("/products")
	public List<Produto> getAllProdutos() {
		return produtoRepository.findAll();
	}
	
	@PostMapping("/products")
	public Produto createProduto(@RequestBody Produto produto) {
		return produtoRepository.save(produto);
	}
	
	@GetMapping("/products/{productid}")
	public ResponseEntity<Produto> getProductById(@PathVariable Long productid) {
		Produto produto = produtoRepository.findById(productid).orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado" + productid));
		return ResponseEntity.ok(produto);
	}
	
	@PutMapping("/products/{productid}")
	public ResponseEntity<Produto> updateProduto(@PathVariable Long productid, @RequestBody Produto produtoDetails) {
		Produto produto = produtoService.updateProduto(productid, produtoDetails);
		return ResponseEntity.ok(produto);
	}
	
	@DeleteMapping("/products/{productid}")
	public ResponseEntity<Map<String, Boolean>> deleteProduct(@PathVariable long productid) {
		Produto produto = produtoRepository.findById(productid).orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado" + productid));
		produtoRepository.delete(produto);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
}
