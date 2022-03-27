package com.tfg.sotocafe.service.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
	
	@BeforeEach
	public void setUp() {
		user = UserRest.builder().username("user1").email("email@email.com").dni("53454323F")
				.nombre("UsuarioPrueba1").telefono("938494392").direccion("Calle Calle").password("pass1234").build();
		rol = Rol.builder().id(1l).nombre("ROLE_USER").build();
		
		this.rolService.saveRol(rol);
		this.userService.saveUser(user);
	}
	
	@Test
    @Transactional
    void testFindUserByUsername() {
		String username = user.getUsername();
		UserRest user = this.userService.getUserByUsername(username);
		assertEquals(username, user.getUsername());
		}

}
