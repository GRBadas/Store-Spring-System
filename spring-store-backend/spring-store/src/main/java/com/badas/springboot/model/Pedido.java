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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "pedido")
@Data @NoArgsConstructor
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private Long pedidoid;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "clientid", referencedColumnName = "clientid", nullable = false)
	private Cliente client;
	
	@Column
	private Date date;
		
	@Column
	private String totalValue;
	
	@Column
	private String status;


}
