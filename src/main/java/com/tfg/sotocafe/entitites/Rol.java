package com.tfg.sotocafe.entitites;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Data;

@Entity
@Table(name="ROLES")
@Builder
@Data
public class Rol implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(length=60)
	private String nombre;
	
	public Rol() {
		super();
	}

	public Rol(Long id, String nombre) {
		super();
		this.id = id;
		this.nombre = nombre;
	}
}
