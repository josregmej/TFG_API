package com.tfg.sotocafe.services;


import com.tfg.sotocafe.entitites.Producto;
import com.tfg.sotocafe.json.ProductoRest;
import com.tfg.sotocafe.repositories.ProductoRepository;

import java.util.List;
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
    	String nombre = producto.getNombre();
    	Producto rep = productoRepository.findByNombre(nombre);
		if(producto.getStockseguridad()>producto.getCantidadalmacen()) {
			LOG.error("La cantidad del almacén debe ser superior o igual al stock de seguridad");
		}
		if(producto.getPrecio()<0) {
			LOG.error("El precio no puede ser negativo");
		}
		Producto newProducto = 
		        Producto.builder().nombre(producto.getNombre())
		        .precio(producto.getPrecio())
		        .cantidadalmacen(producto.getCantidadalmacen())
		        .stockseguridad(producto.getStockseguridad())
		        .cercaStock(false)
		        .build();
		if(rep.getNombre().equals(newProducto.getNombre())) {
			LOG.error("No puede haber productos repetidos");
		}
		if(newProducto.getCantidadalmacen()>=newProducto.getStockseguridad()+5) {
			newProducto.setCercaStock(true);
		}
        productoRepository.save(newProducto);
		return modelMapper.map(newProducto, ProductoRest.class);
	}
    
    @Transactional
    public ProductoRest editProducto(String nombre, ProductoRest productoRest) {
    	Producto producto = productoRepository.findByNombre(nombre);
    	producto.setNombre(productoRest.getNombre());
    	producto.setPrecio(productoRest.getPrecio());
    	producto.setCantidadalmacen(productoRest.getCantidadalmacen());
    	producto.setStockseguridad(productoRest.getStockseguridad());
    	productoRepository.save(producto);
		return modelMapper.map(producto, ProductoRest.class);
    }
    @Transactional
	public List<ProductoRest> getAllProductos() {
		return productoRepository.findAll().stream().map(producto -> modelMapper.map(producto, ProductoRest.class))
				.collect(Collectors.toList());
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