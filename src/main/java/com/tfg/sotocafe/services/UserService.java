package com.tfg.sotocafe.services;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tfg.sotocafe.entitites.Rol;
import com.tfg.sotocafe.entitites.User;
import com.tfg.sotocafe.json.UserRest;
import com.tfg.sotocafe.repositories.RolRepository;
import com.tfg.sotocafe.repositories.UserRepository;

@Service
public class UserService {


	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RolRepository rolRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	private ModelMapper modelMapper = new ModelMapper();
	
	@Transactional
	public UserRest saveUser(UserRest user) {
		User newUser = User.builder()
				.nombre(user.getNombre())
				.email(user.getEmail())
				.direccion(user.getDireccion())
				.dni(user.getDni())
				.password(passwordEncoder.encode(user.getPassword()))
				.username(user.getUsername())
				.telefono(user.getTelefono())
				.id(user.getId())
				.build();
		Rol rol = rolRepository.findByNombre("ROLE_USER").get();
		newUser.setRoles(Collections.singleton(rol));
		userRepository.save(newUser);
		return modelMapper.map(newUser, UserRest.class);
	}
	
	@Transactional
	public UserRest getUserByUsername(String username){
		return modelMapper.map(userRepository.findByUsername(username), UserRest.class);
	}
	
	@Transactional
	public UserRest getUserByEmail(String email){
		return modelMapper.map(userRepository.findByEmail(email), UserRest.class);
	}
	
	@Transactional
	public List<UserRest> getAllUsers() {
		return userRepository.findAll().stream().map(user -> modelMapper.map(user, UserRest.class))
				.collect(Collectors.toList());
	}
	
	@Transactional
	public void deleteUser(String username){
		User user = userRepository.findByUsername(username);
		userRepository.delete(user);
	}
	
	@Transactional
	public UserRest modifyUser(UserRest userRest, String username) {
		User modUser = userRepository.findByUsername(username);
		modUser.setDireccion(userRest.getDireccion());
		modUser.setDni(userRest.getDni());
		modUser.setEmail(userRest.getEmail());
		modUser.setNombre(userRest.getNombre());
		modUser.setPassword(userRest.getPassword());
		modUser.setTelefono(userRest.getTelefono());
		modUser.setUsername(userRest.getUsername());
		modUser.setId(userRest.getId());
		userRepository.save(modUser);
		return modelMapper.map(modUser, UserRest.class);
	}
}
