package com.badas.springboot.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "item_pedido")
@Data @NoArgsConstructor
public class ItemPedido {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long itemPedidoId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pedidoid", referencedColumnName = "pedidoid", nullable = false)
	Pedido pedido;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "productid", referencedColumnName = "productid", nullable = false)
	Produto produto;
	
	@Column
	private Long quantity;
	
	@Column
	private String unitaryValue;

}
