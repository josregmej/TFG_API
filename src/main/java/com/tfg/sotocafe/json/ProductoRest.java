package com.tfg.sotocafe.json;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@Data
public class ProductoRest implements Serializable {

    private static final long serialVersionUID = 2L;

    /* Atributos de la clase producto para Json */
	private Long id;
    private String nombre;
    private Double precio;
    private Integer cantidadalmacen;
    private Integer stockseguridad;
    private Boolean cercaStock;
    
    public ProductoRest(Long id, String nombre, Double precio,
    Integer cantidadalmacen, Integer stockseguridad, Boolean cercaStock) {
        super();
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.cantidadalmacen = cantidadalmacen;
        this.stockseguridad = stockseguridad;
        this.cercaStock = cercaStock;
    }

    public ProductoRest(){
        super();
    }

    public ProductoRest(Long id, String nombre, Double precio,
    Integer cantidadalmacen, Integer stockseguridad) {
        super();
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.cantidadalmacen = cantidadalmacen;
        this.stockseguridad = stockseguridad;
    }
}
