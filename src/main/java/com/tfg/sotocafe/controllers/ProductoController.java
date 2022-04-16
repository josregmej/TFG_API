package com.tfg.sotocafe.controllers;

import com.tfg.sotocafe.json.ProductoRest;

import java.util.List;

import org.springframework.http.ResponseEntity;

public interface ProductoController {
    
    public ResponseEntity<ProductoRest> createProducto(ProductoRest producto);
	public ResponseEntity<ProductoRest> getProductoByNombre(String nombre);
    public void deleteProductoByNombre(String nombre);
    public ResponseEntity<List<ProductoRest>> getProductos();
    public ResponseEntity<ProductoRest> editProducto(String nombre, ProductoRest producto);
    
}
