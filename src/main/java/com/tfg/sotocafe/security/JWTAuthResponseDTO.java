package com.tfg.sotocafe.security;

import lombok.Data;

@Data
public class JWTAuthResponseDTO {

	private String jwt;
	private String tipoToken = "Bearer";
	
	public JWTAuthResponseDTO(String jwt) {
		this.jwt = jwt;
	}
}
