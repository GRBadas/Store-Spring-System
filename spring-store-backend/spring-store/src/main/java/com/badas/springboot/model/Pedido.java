package com.badas.springboot.model;

import java.sql.Date;

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
@Table(name = "pedido")
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long pedidoid;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "clientid", referencedColumnName = "clientid")
	private Cliente client;
	
	@Column
	private Date date;
		
	@Column
	private String totalValue;
	
	@Column
	private String status;

	public Pedido(Long pedidoid, Cliente client, Date date, String totalValue, String status) {
		super();
		this.pedidoid = pedidoid;
		this.client = client;
		this.date = date;
		this.totalValue = totalValue;
		this.status = status;
	}

	public Pedido() {
		super();
	}

	public Long getPedidoid() {
		return pedidoid;
	}

	public void setPedidoid(Long pedidoid) {
		this.pedidoid = pedidoid;
	}

	public Cliente getClient() {
		return client;
	}

	public void setClient(Cliente client) {
		this.client = client;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getTotalValue() {
		return totalValue;
	}

	public void setTotalValue(String totalValue) {
		this.totalValue = totalValue;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	

	
	
}
