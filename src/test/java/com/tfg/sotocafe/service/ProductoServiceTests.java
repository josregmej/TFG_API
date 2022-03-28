package com.tfg.sotocafe.service;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import com.tfg.sotocafe.json.ProductoRest;
import com.tfg.sotocafe.services.ProductoService;

@SpringBootTest
@ActiveProfiles("test")
public class ProductoServiceTests {
	
	@Autowired
	private ProductoService productoService;
	
	private ProductoRest productoRest;
	
	@BeforeEach
	public void setUp() {
		Long dos = 2L;
		productoRest = ProductoRest.builder().id(dos).nombre("mermelada")
				.precio(64.25).cantidadalmacen(10)
				.stockseguridad(2).build();
		this.productoService.saveProducto(productoRest);
	}
	
	@Test
    @Transactional
    void testFindProductoByNombre() {
		ProductoRest producto = this.productoService.getProductoByNombre("mermelada");
		System.out.println("==============================================================================================================="+producto);
		assertEquals(producto, productoRest);
	}
/*	
 * Arreglar test error problema con el ProductoRest
	@Test
    @Transactional
    void testFindProductoByNombreError() {
		assertEquals(true, true);
	}
*/
}
