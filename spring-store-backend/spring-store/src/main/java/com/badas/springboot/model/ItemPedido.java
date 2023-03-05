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

@Entity
@Table(name = "item_pedido")
public class ItemPedido {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long itemPedidoId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pedidoid", referencedColumnName = "pedidoid")
	Pedido pedido;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "productid", referencedColumnName = "productid")
	Produto produto;
	
	@Column
	private Long quantity;
	
	@Column
	private String unitaryValue;

	public ItemPedido(Long itemPedidoId, Pedido pedido, Produto produto, Long quantity, String unitaryValue) {
		super();
		this.itemPedidoId = itemPedidoId;
		this.pedido = pedido;
		this.produto = produto;
		this.quantity = quantity;
		this.unitaryValue = unitaryValue;
	}

	public ItemPedido() {
		super();
	}

	public Long getItemPedidoId() {
		return itemPedidoId;
	}

	public void setItemPedidoId(Long itemPedidoId) {
		this.itemPedidoId = itemPedidoId;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	public String getUnitaryValue() {
		return unitaryValue;
	}

	public void setUnitaryValue(String unitaryValue) {
		this.unitaryValue = unitaryValue;
	}
	
	

}
