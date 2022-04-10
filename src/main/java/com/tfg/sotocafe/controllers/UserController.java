package com.tfg.sotocafe.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.tfg.sotocafe.json.UserRest;

public interface UserController {

	public ResponseEntity<UserRest> createUser(UserRest user);
	public ResponseEntity<UserRest> getUserByUsername(String username);
	public void deleteUser(String username);
	public ResponseEntity<List<UserRest>> getUsers();
}
