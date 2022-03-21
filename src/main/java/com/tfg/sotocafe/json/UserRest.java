package com.tfg.sotocafe.json;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@Data
public class UserRest implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String nombre;
	private String username;
	private String password;
	private String dni;
	private String direccion;
	private String telefono;
	private String email;
	
	public UserRest(Long id, String nombre, String username, String password, String dni, String direccion,
			String telefono, String email) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.username = username;
		this.password = password;
		this.dni = dni;
		this.direccion = direccion;
		this.telefono = telefono;
		this.email = email;
	}
	public UserRest() {
		super();
	}

}
