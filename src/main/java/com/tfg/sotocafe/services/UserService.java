package com.tfg.sotocafe.services;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
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
	public UserRest saveUser(UserRest user) throws DataAccessException {
		User newUser = User.builder().nombre(user.getNombre()).email(user.getEmail())
				.direccion(user.getDireccion()).dni(user.getDni())
				.password(user.getPassword())
				.username(user.getUsername())
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
	public UserRest getUserByUsername(String username) {
		return modelMapper.map(userRepository.findByUsername(username), UserRest.class);
	}
	
	@Transactional
	public UserRest getUserByEmail(String email) {
		return modelMapper.map(userRepository.findByEmail(email), UserRest.class);
	}
	
	@Transactional
	public void deleteUser(String username) {
		User user = userRepository.findByUsername(username);
		userRepository.deleteById(user.getId());
	}
}
