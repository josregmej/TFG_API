package com.tfg.sotocafe.controllers.impl;

import com.tfg.sotocafe.controllers.ProductoController;
import com.tfg.sotocafe.entitites.Producto;
import com.tfg.sotocafe.json.ProductoRest;
import com.tfg.sotocafe.services.ProductoService;
import com.tfg.sotocafe.services.UserService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/products")
public class ProductoControllerImpl implements ProductoController{

    @Autowired
    private ProductoService productoService;

    
    @Override
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value="/create",produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductoRest> createProducto(
        @RequestBody final ProductoRest producto) {
    	System.out.println("=======================================================================\n");
    	System.out.println("ANTES DE GUARDAR: \n"+ producto);
        return 
        new ResponseEntity<>(productoService.saveProducto(producto), HttpStatus.CREATED);
        
    }

	@Override
	@ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)	   
	public ResponseEntity<ProductoRest> getProductoById(@PathVariable("id") final Long id) {
        return 
        new ResponseEntity<>(productoService.getProductoById(id), HttpStatus.OK);
	}

	@Override
	@ResponseStatus(HttpStatus.OK)
	@DeleteMapping(value = "/{id}/delete",produces = MediaType.APPLICATION_JSON_VALUE)
	public void deleteProductoById(@PathVariable("id") final Long id) {
		ProductoRest prod = productoService.getProductoById(id);
		System.out.println("=======================================================================\n");
    	System.out.println("ANTES DE BORRAR: \n"+ prod);
        productoService.deleteProductoById(id);
        prod.setNombre("Borrado producto " + prod.getNombre());
        System.out.println("=======================================================================\n");
    	System.out.println("DESPUES DE BORRAR: \n"+ prod);
        
	}

	@Override
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ProductoRest>> getProductos() {
		System.out.println("=======================================================================");
    	System.out.println("ANTES DE MOSTRAR PRODUCTOS: \n"+ productoService.getAllProductos());
		return new ResponseEntity<>(productoService.getAllProductos(), HttpStatus.OK);
	}

	@ResponseStatus(HttpStatus.OK)
	@PutMapping(value = "/{id}/edit",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ProductoRest> editProducto(@PathVariable("id") final Long id, ProductoRest producto) {
		System.out.println("=======================================================================\n");
    	System.out.println("ANTES DE EDITAR: \n"+ producto);
    	this.productoService.editProducto(id, producto);
    	System.out.println("=======================================================================");
    	System.out.println("ANTES DE EDITAR: \n"+ producto);
    	return new ResponseEntity<>(productoService.getProductoById(id), HttpStatus.OK);
	}
}
