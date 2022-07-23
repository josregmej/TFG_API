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
import com.tfg.sotocafe.json.ClienteRest;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ClienteControllerTest {
	
	@LocalServerPort
	int localServerPort;
	
	@Autowired
    private TestRestTemplate restTemplate;
	
    @Test
    public void testGetClientes() throws IOException, URISyntaxException {
		final String baseUrl = "http://localhost:"+localServerPort+"/api/clientes";
		URI uri = new URI(baseUrl);
    	ResponseEntity<String> response = this.restTemplate.getForEntity(uri, String.class);
    	
    	ObjectMapper mapper = new ObjectMapper();
		JsonNode data = mapper.readTree(response.getBody());
				
		//Comprobamos que el estado y el numero de usuarios son los esperados.
		assertEquals(HttpStatus.OK,response.getStatusCode());
		assertEquals(12,data.size());
    }
    
    @Test
    public void testGetClienteById() throws IOException, URISyntaxException {
		final String baseUrl = "http://localhost:"+localServerPort+"/api/clientes";
		URI uri = new URI(baseUrl+"/1");
    	ResponseEntity<String> response = this.restTemplate.getForEntity(uri, String.class);
    	
    	ObjectMapper mapper = new ObjectMapper();
		JsonNode data = mapper.readTree(response.getBody());
		
		assertEquals(HttpStatus.OK,response.getStatusCode());
		assertEquals("1",data.get("id").asText());
		assertEquals("Pedro Pablo",data.get("nombre").asText());
		assertEquals("53772897R",data.get("dni").asText());
		assertEquals("Severo Ochoa",data.get("direccion").asText());
		assertEquals("603635550",data.get("telefono").asText());
		assertEquals("email@email.com",data.get("email").asText());
		assertEquals("false",data.get("tieneIVA").asText());
    }
    
    @Test
    public void createClienteTest() throws IOException, URISyntaxException {
		final String baseUrl = "http://localhost:"+localServerPort+"/api/clientes";
		URI uri = new URI(baseUrl+"/create");
		ClienteRest cliente = ClienteRest.builder().email("email@email.com").dni("53454323F")
				.nombre("UsuarioPrueba1").telefono("938494392").direccion("Calle Calle").iva(true).build();

    	ResponseEntity<String> response = this.restTemplate.postForEntity(uri, cliente,String.class);
    	
    	ObjectMapper mapper = new ObjectMapper();
		JsonNode data = mapper.readTree(response.getBody());
		
		assertEquals(HttpStatus.CREATED,response.getStatusCode());
		assertEquals("8",data.get("id").asText());
		assertEquals("UsuarioPrueba1",data.get("nombre").asText());
		assertEquals("53454323F",data.get("dni").asText());
		assertEquals("Calle Calle",data.get("direccion").asText());
		assertEquals("938494392",data.get("telefono").asText());
		assertEquals("email@email.com",data.get("email").asText());
		assertEquals("true",data.get("tieneIVA").asText());
    }
    
    @Test
    public void deleteClienteTest() throws IOException, URISyntaxException {
    	//base URL del endpoint
		final String baseUrl = "http://localhost:"+localServerPort+"/api/clientes";
		URI uri = new URI(baseUrl+"/7/delete");
		
		//Borramos el cliente
    	this.restTemplate.delete(uri);
    	
    	//base URL del endpoint para buscar el cliente borrado.
    	URI uri2 = new URI(baseUrl+"/clientes/7");
    	ResponseEntity<String> response = this.restTemplate.getForEntity(uri2, String.class);
 
    	//Comprobamos que efectivamente no existe y no lo encuentra.
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
    
    @Test
	public void modifyClienteTest() throws URISyntaxException, IOException{
		
		//base URL del endpoint
    	final String baseUrl = "http://localhost:"+localServerPort+"/api/clientes";
		URI uri = new URI(baseUrl+"/1/edit");
		
		//Creamos el cliente con los valores a modificar.
		ClienteRest cliente = ClienteRest.builder().email("direccion@email.com").dni("53454323D")
				.nombre("UsuarioPrueba3").telefono("938494392").direccion("Calle Calle2").iva(false).build();
		
		//Realizamos la operación put pasandole el actor con sus datos modificados y la url.
		this.restTemplate.put(uri, cliente);
		
		URI uri2 = new URI(baseUrl+"/1");
		//Obtenemos el cliente que acabamos de modificar y a partir de la respuesta comprobamos que se ha modificado.
		ResponseEntity<String> response = this.restTemplate.getForEntity(uri2,
				String.class);
		
		//Ahora leemos el cuerpo de la respuesta, que es donde están contenidos los valores del objeto.
		ObjectMapper mapper = new ObjectMapper();
		JsonNode data = mapper.readTree(response.getBody());
		
		//Comprobamos que el estado, y los valores son los esperados.
		assertEquals(HttpStatus.OK,response.getStatusCode());
		assertEquals("1",data.get("id").asText());
		assertEquals("UsuarioPrueba3",data.get("nombre").asText());
		assertEquals("53454323D",data.get("dni").asText());
		assertEquals("Calle Calle2",data.get("direccion").asText());
		assertEquals("938494392",data.get("telefono").asText());
		assertEquals("direccion@email.com",data.get("email").asText());
		assertEquals("false",data.get("tieneIVA").asText());
	}
}
