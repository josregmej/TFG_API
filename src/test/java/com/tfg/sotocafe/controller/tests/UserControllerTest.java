package com.tfg.sotocafe.controller.tests;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tfg.sotocafe.json.UserRest;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTest {
	
	@LocalServerPort
	int localServerPort;
	
	@Autowired
    private TestRestTemplate restTemplate;
	
    @Test
    public void testGetUsers() throws IOException, URISyntaxException {
		final String baseUrl = "http://localhost:"+localServerPort+"/api/admin/users";
		URI uri = new URI(baseUrl);
    	ResponseEntity<String> response = this.restTemplate.getForEntity(uri, String.class);
    	
    	ObjectMapper mapper = new ObjectMapper();
		JsonNode data = mapper.readTree(response.getBody());
				
		//Comprobamos que el estado y el numero de usuarios son los esperados.
		assertEquals(HttpStatus.OK,response.getStatusCode());
		assertEquals(3,data.size());
    }
    
    @Test
    public void testGetUserByUsername() throws IOException, URISyntaxException {
		final String baseUrl = "http://localhost:"+localServerPort+"/api/admin/users";
		URI uri = new URI(baseUrl+"/pedcarmor");
    	ResponseEntity<String> response = this.restTemplate.getForEntity(uri, String.class);
    	
    	ObjectMapper mapper = new ObjectMapper();
		JsonNode data = mapper.readTree(response.getBody());
		
		assertEquals(HttpStatus.OK,response.getStatusCode());
		assertEquals("1",data.get("id").asText());
		assertEquals("pedcarmor",data.get("username").asText());
		assertEquals("Pedro Pablo",data.get("nombre").asText());
		assertEquals("53772897R",data.get("dni").asText());
		assertEquals("Severo Ochoa",data.get("direccion").asText());
		assertEquals("603635550",data.get("telefono").asText());
		assertEquals("email@email.com",data.get("email").asText());
		assertEquals("Password1234",data.get("password").asText());
    }
    
    @Test
    public void createUserTest() throws IOException, URISyntaxException {
		final String baseUrl = "http://localhost:"+localServerPort+"/api/admin/users";
		URI uri = new URI(baseUrl);
		UserRest user = UserRest.builder().id(4l).username("user1").email("email@email.com").dni("53454323F")
				.nombre("UsuarioPrueba1").telefono("938494392").direccion("Calle Calle").password("Pass1234").build();
    	ResponseEntity<String> response = this.restTemplate.postForEntity(uri, user,String.class);
    	
    	ObjectMapper mapper = new ObjectMapper();
		JsonNode data = mapper.readTree(response.getBody());
		
		assertEquals(HttpStatus.CREATED,response.getStatusCode());
		assertEquals("4",data.get("id").asText());
		assertEquals("user1",data.get("username").asText());
		assertEquals("UsuarioPrueba1",data.get("nombre").asText());
		assertEquals("53454323F",data.get("dni").asText());
		assertEquals("Calle Calle",data.get("direccion").asText());
		assertEquals("938494392",data.get("telefono").asText());
		assertEquals("email@email.com",data.get("email").asText());
		assertEquals("Pass1234",data.get("password").asText());
    }
    
    @Test
    public void deleteUserTest() throws IOException, URISyntaxException {
		final String baseUrl = "http://localhost:"+localServerPort+"/api/admin/users";
		URI uri = new URI(baseUrl+"/pedcarmor/delete");
    	this.restTemplate.delete(uri);
    	
    	URI uri2 = new URI(baseUrl+"/pedcarmor");
    	ResponseEntity<String> response = this.restTemplate.getForEntity(uri2, String.class);
 
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
    
    @Test
   	public void modifyUserTest() throws URISyntaxException, IOException{
   		
   		//base URL del endpoint
       	final String baseUrl = "http://localhost:"+localServerPort+"/api/admin/users";
   		URI uri = new URI(baseUrl+"/user1/edit");
   		
   		//Creamos el usuario con los valores a modificar.
   		UserRest user = UserRest.builder().email("email2@email.com").dni("53454323S")
   				.nombre("Usuario modificado").telefono("938494392").direccion("Calle Modificada")
   				.password("PedritoElDuende1234").username("user1").id(5l).build();
   		
   		//Realizamos la operación put pasandole el actor con sus datos modificados y la url.
   		this.restTemplate.put(uri, user);
   		
   		URI uri2 = new URI(baseUrl+"/user1");
   		//Obtenemos el usuario que acabamos de modificar y a partir de la respuesta comprobamos que se ha modificado.
   		ResponseEntity<String> response = this.restTemplate.getForEntity(uri2,
   				String.class);
   		
   		//Ahora leemos el cuerpo de la respuesta, que es donde están contenidos los valores del objeto.
   		ObjectMapper mapper = new ObjectMapper();
   		JsonNode data = mapper.readTree(response.getBody());
   		
   		//Comprobamos que el estado, y los valores son los esperados.
   		assertEquals(HttpStatus.OK,response.getStatusCode());
   		assertEquals("4",data.get("id").asText());
   		assertEquals("user1",data.get("username").asText());
   		assertEquals("Usuario modificado",data.get("nombre").asText());
   		assertEquals("53454323S",data.get("dni").asText());
   		assertEquals("Calle Modificada",data.get("direccion").asText());
   		assertEquals("938494392",data.get("telefono").asText());
   		assertEquals("email2@email.com",data.get("email").asText());
   		assertEquals("PedritoElDuende1234",data.get("password").asText());
   	}
}
