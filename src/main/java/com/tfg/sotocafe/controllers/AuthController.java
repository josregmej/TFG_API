package com.tfg.sotocafe.controllers;

import org.springframework.http.ResponseEntity;

import com.tfg.sotocafe.dto.LoginDTO;
import com.tfg.sotocafe.security.JWTAuthResponseDTO;

public interface AuthController {
	
	public ResponseEntity<JWTAuthResponseDTO> authenticateUser(LoginDTO loginDTO);
}
