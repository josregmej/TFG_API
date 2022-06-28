package com.tfg.sotocafe.json;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@EqualsAndHashCode(callSuper = false)
public class UserRest extends PersonRest{
	 
	private static final long serialVersionUID = 1L;
	private Long id;
	private String username;
	private String password;
	
	@Builder
	public UserRest(String nombre, String dni, String direccion, String telefono, String email, Long id,String username, String password) {
		super(nombre,dni,direccion,telefono,email);
		this.username = username;
		this.password = password;
		this.id=id;
	}

	public UserRest() {
		super();
	}
}
