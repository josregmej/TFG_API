package com.tfg.sotocafe.services;


import com.tfg.sotocafe.entitites.Producto;
import com.tfg.sotocafe.json.ProductoRest;
import com.tfg.sotocafe.repositories.ProductoRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.jboss.logging.Logger;
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
    
    private static Logger LOG = Logger.getLogger("com.tfg.sotocafe.services");

    @Transactional
	public ProductoRest saveProducto(ProductoRest producto) throws DataAccessException {
		Producto newProducto = 
		        Producto.builder().nombre(producto.getNombre())
		        .precio(producto.getPrecio())
		        .cantidadalmacen(producto.getCantidadalmacen())
		        .stockseguridad(producto.getStockseguridad())
		        .cercaStock(false)
		        .build();
        productoRepository.save(newProducto);
		return modelMapper.map(newProducto, ProductoRest.class);
	}
    
    @Transactional
    public ProductoRest editProducto(Long id, ProductoRest productoRest) {
    	Producto producto = productoRepository.getById(id);
    	producto.setNombre(productoRest.getNombre());
    	producto.setPrecio(productoRest.getPrecio());
    	producto.setCantidadalmacen(productoRest.getCantidadalmacen());
    	producto.setStockseguridad(productoRest.getStockseguridad());
    	productoRepository.save(producto);
    	ProductoRest prodR = this.getProductoById(id);
		return modelMapper.map(producto, ProductoRest.class);
    }
    @Transactional
	public List<ProductoRest> getAllProductos() {
		return productoRepository.findAll().stream().map(producto -> modelMapper.map(producto, ProductoRest.class))
				.collect(Collectors.toList());
	}

    @Transactional
    public ProductoRest getProductoById(Long id){
        return modelMapper.map(productoRepository.getById(id), ProductoRest.class);
    }

    @Transactional
    public void deleteProductoById(Long id){
        productoRepository.deleteById(id);
        
    }
}