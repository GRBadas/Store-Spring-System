package com.badas.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.badas.springboot.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long>{

}
