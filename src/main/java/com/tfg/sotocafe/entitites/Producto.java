package com.tfg.sotocafe.entitites;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Builder;
import lombok.Data;

@Entity
@Table(name ="PRODUCTO")
@Data
@Builder
public class Producto implements Serializable{
 
    private static final long serialVersionUID = 2L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

    private String nombre;
    private Double precio;
    private Integer cantidadalmacen;
    private Integer stockseguridad;
    private Boolean cercaStock;
    
    @ManyToOne
	@JoinColumn(name = "user_id")
    @JsonIgnore
	private User user;

    public Producto(){
        super();
    }

    public Producto(Long id, String nombre, Double precio,
    Integer cantidadalmacen, Integer stockseguridad, Boolean cercaStock) {
        super();
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.cantidadalmacen = cantidadalmacen;
        this.stockseguridad = stockseguridad;
        this.cercaStock = cercaStock;
    
    }

    public Producto(Long id, String nombre, Double precio,
    Integer cantidadalmacen, Integer stockseguridad) {
        super();
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.cantidadalmacen = cantidadalmacen;
        this.stockseguridad = stockseguridad;
    
    }
    
    public Producto(Long id, String nombre, Double precio,
    Integer cantidadalmacen, Integer stockseguridad, Boolean cercaStock, User user) {
    	super();
    	this.id = id;
    	this.nombre = nombre;
    	this.precio = precio;
    	this.cantidadalmacen = cantidadalmacen;
    	this.stockseguridad = stockseguridad;
    	this.cercaStock = cercaStock;
    	this.user = user;
    	    
    }
}