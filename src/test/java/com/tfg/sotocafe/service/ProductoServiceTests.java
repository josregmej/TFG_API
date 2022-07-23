package com.tfg.sotocafe.service;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

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
				.precio(64.25).cantidadalmacen(100)
				.stockseguridad(2).cercaStock(false).build();
		this.productoService.saveProducto(productoRest);
	}
	
	@Test
    @Transactional
    void testFindProductoByNombre() {
		ProductoRest producto = this.productoService.getProductoById(2L);
		assertEquals(producto, productoRest);
	}
	
	@Test
	@Transactional
	void testDeleteProducto() {
		ProductoRest producto1 = ProductoRest.builder().id(2L).nombre("mermelada")
				.precio(64.25).cantidadalmacen(100)
				.stockseguridad(2).cercaStock(false).build();

		ProductoRest producto2 = ProductoRest.builder().id(10L).nombre("zapato")
				.precio(51.25).cantidadalmacen(100)
				.stockseguridad(2).cercaStock(false).build();
		
		this.productoService.saveProducto(producto1);
		this.productoService.saveProducto(producto2);
		
		List<ProductoRest> productos = new ArrayList<ProductoRest>();
		productos.add(producto1);
		productos.add(producto2);
	
		assertEquals(2, productos.size());
		
		this.productoService.deleteProductoById(2L);
		
		assertEquals(1, productos.size());
		
		this.productoService.deleteProductoById(10L);
		
		assertEquals(0, productos.size());
	}
}
