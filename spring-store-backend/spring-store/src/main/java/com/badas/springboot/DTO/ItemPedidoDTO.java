package com.badas.springboot.DTO;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class ItemPedidoDTO {
	
	private String unitaryValue;
	
	private Long quantity;
	
	@NotNull(message = "O id do produto não pode ser nulo")
	private Long productId;
	
	@NotNull(message = "O id do pedido não pode ser nulo")
	private Long pedidoId;
	


}
