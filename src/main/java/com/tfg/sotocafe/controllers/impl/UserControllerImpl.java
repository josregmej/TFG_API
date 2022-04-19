package com.tfg.sotocafe.controllers.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.tfg.sotocafe.controllers.UserController;
import com.tfg.sotocafe.json.UserRest;
import com.tfg.sotocafe.services.UserService;

@RestController
@RequestMapping("/api/admin/users")
public class UserControllerImpl implements UserController{

	@Autowired
	private UserService userService;
	
	@Override
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserRest> createUser(
			@RequestBody final UserRest user
			) {
		return new ResponseEntity<>(userService.saveUser(user), HttpStatus.CREATED);
	}

	@Override
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/{username}",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserRest> getUserByUsername(@PathVariable String username) {
		return new ResponseEntity<>(userService.getUserByUsername(username), HttpStatus.OK);
	}

	@Override
	@ResponseStatus(HttpStatus.OK)
	@DeleteMapping(value = "/{username}/delete",produces = MediaType.APPLICATION_JSON_VALUE)
	public void deleteUser(@PathVariable String username) {
		userService.deleteUser(username);
	}

	@Override
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<UserRest>> getUsers() {
		return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
	}

	@Override
	@ResponseStatus(HttpStatus.OK)
	@PutMapping(value = "/{username}/edit",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserRest> modifyUser(@RequestBody final UserRest user, @PathVariable String username) {
		return new ResponseEntity<>(userService.modifyUser(user, username),HttpStatus.OK);
	}
}
