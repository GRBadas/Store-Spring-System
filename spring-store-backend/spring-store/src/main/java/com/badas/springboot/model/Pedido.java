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

@Data
@NoArgsConstructor
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
	
	
}
