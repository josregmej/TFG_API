package com.tfg.sotocafe.services;

import java.util.logging.Logger;

import com.tfg.sotocafe.entitites.Producto;
import com.tfg.sotocafe.json.ProductoRest;
import com.tfg.sotocafe.repositories.ProductoRepository;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductoService{

    @Autowired
    private ProductoRepository productoRepository;

    private ModelMapper modelMapper = new ModelMapper();

    @Transactional
	public ProductoRest saveProducto(ProductoRest producto) throws DataAccessException {
		Producto newProducto = 
        Producto.builder().nombre(producto.getNombre())
        .precio(producto.getPrecio())
        .cantidadalmacen(producto.getCantidadalmacen())
        .stockseguridad(producto.getStockseguridad())
        .fechacaducidad(producto.getFechacaducidad())
        .build();
        productoRepository.save(newProducto);
		return modelMapper.map(newProducto, ProductoRest.class);
	}

    @Transactional
    public ProductoRest getProductoByNombre(String nombre){
        return modelMapper.map(productoRepository.findByNombre(nombre), ProductoRest.class);
    }

    @Transactional
    public void deleteProductoByNombre(String nombre){
        Producto producto = productoRepository.findByNombre(nombre);
        productoRepository.deleteById(producto.getId());
        
    }
}