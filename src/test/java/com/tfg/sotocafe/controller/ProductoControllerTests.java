package com.tfg.sotocafe.controller;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
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
import com.tfg.sotocafe.json.ProductoRest;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductoControllerTests {
	private static final Long TEST_PRODUCTO_ID = 1L;
	@LocalServerPort
	int localServerPort;
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	@SuppressWarnings("deprecation")
	@Test
    public void testGetProductByName() throws IOException, URISyntaxException {
		final String baseUrl = "http://localhost:"+localServerPort+"/api/products";
		URI uri = new URI(baseUrl + "miel");
    	ResponseEntity<String> response = this.restTemplate.getForEntity(uri, String.class);
    	
    	ObjectMapper mapper = new ObjectMapper();
		JsonNode data = mapper.readTree(response.getBody());
				
		//Comprobamos que el estado y el numero de usuarios son los esperados.
		assertEquals(HttpStatus.OK,response.getStatusCode());
		assertEquals("miel",data.get("nombre").asText());
		assertEquals(5.00, data.get("precio").asDouble());
		assertEquals(666, data.get("cantidadalmacen").asInt());
		assertEquals(66, data.get("stockseguridad").asInt());
		assertEquals(null, data.get("cerca_stock").asBoolean());
    }
	
	@Test
	public void createProductoTest() throws IOException, URISyntaxException {
		final String baseUrl = "http://localhost:"+localServerPort+"/api/products";
		URI uri = new URI(baseUrl + "/create");
		
		ProductoRest producto = ProductoRest.builder().nombre("zapato")
				.precio(51.25).cantidadalmacen(100)
				.stockseguridad(2).cercaStock(false).build();
    	ResponseEntity<String> response = this.restTemplate.postForEntity(uri, producto, String.class);
    	
    	ObjectMapper mapper = new ObjectMapper();
		JsonNode data = mapper.readTree(response.getBody());
		
		assertEquals(HttpStatus.CREATED,response.getStatusCode());
		assertEquals("zapato",data.get("nombre").asText());
		assertEquals(51.25, data.get("precio").asDouble());
		assertEquals(100, data.get("cantidadalmacen").asInt());
		assertEquals(2, data.get("stockseguridad").asInt());
		assertEquals(false, data.get("cerca_stock").asBoolean());
			
	}
	
	@Test
    public void deleteProductoTest() throws IOException, URISyntaxException {
		final String baseUrl = "http://localhost:"+localServerPort+"/api/products";
		URI uriBorrar = new URI(baseUrl+"/miel/delete");
    	this.restTemplate.delete(uriBorrar);
    	
    	URI uriBuscar = new URI(baseUrl+"/miel");
    	ResponseEntity<String> response = this.restTemplate.getForEntity(uriBuscar, String.class);
 
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

}
