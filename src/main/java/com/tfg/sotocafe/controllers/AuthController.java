package com.tfg.sotocafe.controllers;

import org.springframework.http.ResponseEntity;

import com.tfg.sotocafe.dto.LoginDTO;

public interface AuthController {
	
	public ResponseEntity<String> authenticateUser(LoginDTO loginDTO);
}
