package com.tfg.sotocafe.controllers.impl;

import com.tfg.sotocafe.controllers.ProductoController;
import com.tfg.sotocafe.json.ProductoRest;
import com.tfg.sotocafe.services.ProductoService;

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
        return 
        new ResponseEntity<>(productoService.saveProducto(producto), HttpStatus.CREATED);
    }

    @Override
	@ResponseStatus(HttpStatus.OK)
	@PutMapping(value = "/{nombre}/edit",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ProductoRest> editProducto(@PathVariable String nombre, @RequestBody final ProductoRest producto) {
		return new ResponseEntity<>(productoService.editProducto(nombre, producto), HttpStatus.OK);
	}
    
    @Override
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{nombre}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductoRest> getProductoByNombre(@PathVariable String nombre) {
        return 
        new ResponseEntity<>(productoService.getProductoByNombre(nombre), HttpStatus.OK);
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(value = "/{nombre}/delete",produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteProductoByNombre(@PathVariable String nombre) {
        productoService.deleteProductoByNombre(nombre);
        
    }

	@Override
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ProductoRest>> getProductos() {
		return new ResponseEntity<>(productoService.getAllProductos(), HttpStatus.OK);
	}
}
