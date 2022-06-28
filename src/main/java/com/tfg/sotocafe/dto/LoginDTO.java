package com.tfg.sotocafe.dto;

import lombok.Data;

@Data
public class LoginDTO {

	private String usernameOrEmail;
	private String password;
}
