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

import com.tfg.sotocafe.json.ClienteRest;
import com.tfg.sotocafe.services.ClienteService;

@SpringBootTest
@ActiveProfiles("test")
public class ClienteServiceTest {
	
	@Autowired
	private ClienteService clienteService;
	
	private ClienteRest cliente;
	private ClienteRest rest;
	
	@BeforeEach
	public void setUp() {
		cliente = ClienteRest.builder().email("email@email.com").dni("53454323F")
				.nombre("UsuarioPrueba1").telefono("938494392").direccion("Calle Calle").iva(true).build();
		rest = this.clienteService.savecliente(cliente);
	}
	
	@Test
    @Transactional
    void testFindClienteById() {
		Long id = rest.getId();
		ClienteRest cliente1 = this.clienteService.getClienteById(id);
		assertNotNull(id);
		assertNotNull(cliente1);
		assertEquals(rest.getNombre(), cliente1.getNombre());
		assertEquals(rest.getDireccion(), cliente1.getDireccion());
		assertEquals(rest.getDni(), cliente1.getDni());
		assertEquals(rest.getEmail(), cliente1.getEmail());
		assertEquals(rest.getTelefono(), cliente1.getTelefono());
		assertEquals(rest.getTieneIVA(), cliente1.getTieneIVA());
	}
	
	
	@Test
    @Transactional
    void testDeleteCliente() {
		List<ClienteRest> clientes = this.clienteService.getAllClientes();
		
		ClienteRest clienteElm = ClienteRest.builder().email("email2@email.com").dni("53454323G")
				.nombre("UsuarioPrueba2").telefono("938494393").direccion("Calle Calle").iva(false).build();
		ClienteRest clienteGuard = this.clienteService.savecliente(clienteElm);
		List<ClienteRest> clientesGuard = this.clienteService.getAllClientes();
		
		this.clienteService.deleteCliente(clienteGuard.getId());
		List<ClienteRest> clientesDesp = this.clienteService.getAllClientes();
		assertNotNull(clienteElm);
		assertNotNull(clienteGuard);
		assertEquals(1,clientes.size());
		assertEquals(2,clientesGuard.size());
		assertEquals(clientesDesp.size(),clientes.size());
	}
	
	@Test
    @Transactional
    void testGetAllClientes() {
		List<ClienteRest> clientes = this.clienteService.getAllClientes();
		assertNotNull(clientes);
		assertEquals(12,clientes.size());
	}
	
	@Test
    @Transactional
    void testModifyCliente() {
		ClienteRest cliente1 = ClienteRest.builder().email("email2@email.com").dni("53454323G")
				.nombre("UsuarioPrueba2").telefono("938494393").direccion("Calle Calle").iva(false).build();
		ClienteRest clienteMod = this.clienteService.savecliente(cliente1);
		cliente = this.clienteService.modifyCliente(clienteMod.getId(), clienteMod);
		assertNotNull(clienteMod);
		assertNotNull(cliente);
		assertEquals(cliente.getNombre(), cliente1.getNombre());
		assertEquals(cliente.getDireccion(), cliente1.getDireccion());
		assertEquals(cliente.getDni(), cliente1.getDni());
		assertEquals(cliente.getEmail(), cliente1.getEmail());
		assertEquals(cliente.getTelefono(), cliente1.getTelefono());
		assertEquals(cliente.getTieneIVA(), cliente1.getTieneIVA());
	}
}
