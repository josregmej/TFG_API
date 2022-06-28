package com.tfg.sotocafe.services;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
	
	private ModelMapper modelMapper = new ModelMapper();
	
	@Transactional
	public UserRest saveUser(UserRest user) {
		User newUser = User.builder()
				.nombre(user.getNombre())
				.email(user.getEmail())
				.direccion(user.getDireccion())
				.dni(user.getDni())
				.password(user.getPassword())
				.username(user.getUsername())
				.telefono(user.getTelefono())
				.build();
		Set<Rol> roles = new HashSet<Rol>();
		Optional<Rol> rol = rolRepository.findByNombre("ROLE_USER");
		roles.add(rol.get());
		rolRepository.save(rol.get());
		newUser.setRoles(roles);
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
		userRepository.save(modUser);
		return modelMapper.map(modUser, UserRest.class);
	}
}
