package com.badas.springboot.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "client")
@Entity
@Data @NoArgsConstructor
public class Cliente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long clientid;
	@Column
	private String name;
	@Column
	private String surname;
	@Column
	private String email;
	@Column
	private String password;

}
