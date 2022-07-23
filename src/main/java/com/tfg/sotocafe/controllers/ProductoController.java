package com.tfg.sotocafe.controllers;

import com.tfg.sotocafe.json.ProductoRest;

import java.util.List;

import org.springframework.http.ResponseEntity;

public interface ProductoController {
    
    public ResponseEntity<ProductoRest> createProducto(ProductoRest producto);
	public ResponseEntity<ProductoRest> getProductoById(Long id);
    public void deleteProductoById(Long id);
    public ResponseEntity<List<ProductoRest>> getProductos();
    public ResponseEntity<ProductoRest> editProducto(Long id, ProductoRest producto);
    
}
