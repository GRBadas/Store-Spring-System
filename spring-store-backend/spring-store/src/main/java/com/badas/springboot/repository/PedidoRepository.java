package com.badas.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.badas.springboot.model.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long>{

}
