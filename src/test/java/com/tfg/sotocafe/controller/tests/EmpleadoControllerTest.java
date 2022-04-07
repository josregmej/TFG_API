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
import com.tfg.sotocafe.json.EmpleadoRest;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EmpleadoControllerTest {
	
	@LocalServerPort
	int localServerPort;
	
	@Autowired
    private TestRestTemplate restTemplate;
	
    @Test
    public void testGetEmpleados() throws IOException, URISyntaxException {
		final String baseUrl = "http://localhost:"+localServerPort+"/empleados";
		URI uri = new URI(baseUrl);
    	ResponseEntity<String> response = this.restTemplate.getForEntity(uri, String.class);
    	
    	ObjectMapper mapper = new ObjectMapper();
		JsonNode data = mapper.readTree(response.getBody());
				
		//Comprobamos que el estado y el numero de usuarios son los esperados.
		assertEquals(HttpStatus.OK,response.getStatusCode());
		assertEquals(3,data.size());
    }
    
    @Test
    public void testGetEmpleadoById() throws IOException, URISyntaxException {
		final String baseUrl = "http://localhost:"+localServerPort+"/empleados";
		URI uri = new URI(baseUrl+"/1");
    	ResponseEntity<String> response = this.restTemplate.getForEntity(uri, String.class);
    	
    	ObjectMapper mapper = new ObjectMapper();
		JsonNode data = mapper.readTree(response.getBody());
		
		assertEquals(HttpStatus.OK,response.getStatusCode());
		assertEquals("1",data.get("id").asText());
		assertEquals("Pedro Pablo",data.get("nombre").asText());
		assertEquals("53772897D",data.get("dni").asText());
		assertEquals("Severo Ochoa",data.get("direccion").asText());
		assertEquals("603635550",data.get("telefono").asText());
		assertEquals("email@email.com",data.get("email").asText());
		assertEquals("10 faltas",data.get("faltas").asText());
		assertEquals("Lunes a Viernes de 9:00 a 18:00",data.get("horario").asText());
		assertEquals("8.0",data.get("horasTrabajadas").asText());
		assertEquals("1050.9",data.get("salario").asText());
    }
    
    @Test
    public void createEmpleadoTest() throws IOException, URISyntaxException {
		final String baseUrl = "http://localhost:"+localServerPort+"/empleados";
		URI uri = new URI(baseUrl+"/create");
		EmpleadoRest empleado = EmpleadoRest.builder().email("email@email.com").dni("53454323F")
				.nombre("UsuarioPrueba1").telefono("938494392").direccion("Calle Calle")
				.faltas("10 faltas").horario("10:00 a 19:00").horas(8.5).salario(1000.34).build();

    	ResponseEntity<String> response = this.restTemplate.postForEntity(uri, empleado,String.class);
    	
    	ObjectMapper mapper = new ObjectMapper();
		JsonNode data = mapper.readTree(response.getBody());
		
		assertEquals(HttpStatus.CREATED,response.getStatusCode());
		assertEquals("4",data.get("id").asText());
		assertEquals("UsuarioPrueba1",data.get("nombre").asText());
		assertEquals("53454323F",data.get("dni").asText());
		assertEquals("Calle Calle",data.get("direccion").asText());
		assertEquals("938494392",data.get("telefono").asText());
		assertEquals("email@email.com",data.get("email").asText());
		assertEquals("10 faltas",data.get("faltas").asText());
		assertEquals("10:00 a 19:00",data.get("horario").asText());
		assertEquals("8.5",data.get("horasTrabajadas").asText());
		assertEquals("1000.34",data.get("salario").asText());
    }
    
    @Test
    public void deleteEmpleadoTest() throws IOException, URISyntaxException {
    	//base URL del endpoint
		final String baseUrl = "http://localhost:"+localServerPort+"/empleados";
		URI uri = new URI(baseUrl+"/4/delete");
		
		//Borramos el empleado
    	this.restTemplate.delete(uri);
    	
    	//base URL del endpoint para buscar el empleado borrado.
    	URI uri2 = new URI(baseUrl+"/empleados/4");
    	ResponseEntity<String> response = this.restTemplate.getForEntity(uri2, String.class);
 
    	//Comprobamos que efectivamente no existe y no lo encuentra.
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
    
    @Test
	public void modifyEmpleadoTest() throws URISyntaxException, IOException{
		
		//base URL del endpoint
    	final String baseUrl = "http://localhost:"+localServerPort+"/empleados";
		URI uri = new URI(baseUrl+"/1/edit");
		
		//Creamos el empleado con los valores a modificar.
		EmpleadoRest empleado = EmpleadoRest.builder().email("email2@email.com").dni("53454323S")
				.nombre("UsuarioPrueba2").telefono("938494392").direccion("Calle Calle2")
				.faltas("16 faltas").horario("15:00 a 19:00").horas(4.0).salario(1958.34).build();
		
		//Realizamos la operación put pasandole el actor con sus datos modificados y la url.
		this.restTemplate.put(uri, empleado);
		
		URI uri2 = new URI(baseUrl+"/1");
		//Obtenemos el empleado que acabamos de modificar y a partir de la respuesta comprobamos que se ha modificado.
		ResponseEntity<String> response = this.restTemplate.getForEntity(uri2,
				String.class);
		
		//Ahora leemos el cuerpo de la respuesta, que es donde están contenidos los valores del objeto.
		ObjectMapper mapper = new ObjectMapper();
		JsonNode data = mapper.readTree(response.getBody());
		
		//Comprobamos que el estado, y los valores son los esperados.
		assertEquals(HttpStatus.OK,response.getStatusCode());
		assertEquals("1",data.get("id").asText());
		assertEquals("UsuarioPrueba2",data.get("nombre").asText());
		assertEquals("53454323S",data.get("dni").asText());
		assertEquals("Calle Calle2",data.get("direccion").asText());
		assertEquals("938494392",data.get("telefono").asText());
		assertEquals("email2@email.com",data.get("email").asText());
		assertEquals("16 faltas",data.get("faltas").asText());
		assertEquals("15:00 a 19:00",data.get("horario").asText());
		assertEquals("4.0",data.get("horasTrabajadas").asText());
		assertEquals("1958.34",data.get("salario").asText());
	}
}
