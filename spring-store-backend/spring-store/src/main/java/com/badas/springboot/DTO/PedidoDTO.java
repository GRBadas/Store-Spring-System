package com.badas.springboot.DTO;

import java.sql.Date;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class PedidoDTO {
	
	private Date date;
	
	private String totalValue;

	private String status;
	
	@NotNull(message = "O id do cliente não pode ser nulo")
	private Long clientId;
	
	@NotNull(message = "O id do produto não pode ser nulo")
	private Long productId;

}
