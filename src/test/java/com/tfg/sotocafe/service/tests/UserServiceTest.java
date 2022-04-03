package com.tfg.sotocafe.service.tests;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import com.tfg.sotocafe.entitites.Rol;
import com.tfg.sotocafe.json.UserRest;
import com.tfg.sotocafe.services.RolService;
import com.tfg.sotocafe.services.UserService;

@SpringBootTest
@ActiveProfiles("test")
public class UserServiceTest {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RolService rolService;
	
	private UserRest user;
	private Rol rol;
	private UserRest rest;
	
	@BeforeEach
	public void setUp() {
		user = UserRest.builder().username("user1").email("email@email.com").dni("53454323F")
				.nombre("UsuarioPrueba1").telefono("938494392").direccion("Calle Calle").password("Pass1234").build();
		rol = Rol.builder().id(1l).nombre("ROLE_USER").build();
		
		this.rolService.saveRol(rol);
		rest = this.userService.saveUser(user);
	}
	
	@Test
    @Transactional
    void testFindUserByUsername() {
		String username = rest.getUsername();
		UserRest user1 = this.userService.getUserByUsername(username);
		assertNotNull(username);
		assertNotNull(user1);
		assertEquals(rest.getUsername(), user1.getUsername());
		assertEquals(rest.getNombre(), user1.getNombre());
		assertEquals(rest.getDireccion(), user1.getDireccion());
		assertEquals(rest.getDni(), user1.getDni());
		assertEquals(rest.getEmail(), user1.getEmail());
		assertEquals(rest.getTelefono(), user1.getTelefono());
	}
	
	@Test
    @Transactional
    void testFindUserByEmail() {
		String email = rest.getEmail();
		UserRest user1 = this.userService.getUserByEmail(email);
		assertNotNull(user1);
		assertNotNull(email);
		assertEquals(rest.getUsername(),user1.getUsername());
		assertEquals(rest.getNombre(), user1.getNombre());
		assertEquals(rest.getDireccion(), user1.getDireccion());
		assertEquals(rest.getDni(), user1.getDni());
		assertEquals(rest.getEmail(), user1.getEmail());
		assertEquals(rest.getTelefono(), user1.getTelefono());
	}
	
	@Test
    @Transactional
    void testDeleteUser() {
		List<UserRest> usuarios = this.userService.getAllUsers();
		
		UserRest userElm = UserRest.builder().username("user2").email("email2@email.com").dni("53454323G")
				.nombre("UsuarioPrueba2").telefono("938494393").direccion("Calle Calle").password("Pass1234").build();
		UserRest userGuard = this.userService.saveUser(userElm);
		List<UserRest> usuariosGuard = this.userService.getAllUsers();
		
		this.userService.deleteUser(userGuard.getUsername());
		List<UserRest> usuariosDesp = this.userService.getAllUsers();
		
		assertEquals(1,usuarios.size());
		assertEquals(2,usuariosGuard.size());
		assertEquals(usuariosDesp.size(),usuarios.size());
	}
	
	@Test
    @Transactional
    void testGetAllUsers() {
		List<UserRest> users = this.userService.getAllUsers();
		assertNotNull(users);
		assertEquals(1,users.size());
	}
}
