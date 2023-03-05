package com.badas.springboot.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "client")
@Entity
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
	
	public Cliente(Long clientid, String name, String surname, String email, String password) {
		super();
		this.clientid = clientid;
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.password = password;
	}
	
	public Cliente() {
		super();
	}

	public Long getClientid() {
		return clientid;
	}

	public void setClientid(Long clientid) {
		this.clientid = clientid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
