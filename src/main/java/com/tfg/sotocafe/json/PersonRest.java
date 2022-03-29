package com.tfg.sotocafe.json;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@Data
public class PersonRest implements Serializable{

	private static final long serialVersionUID = 1L;
	private String nombre;	
	private String dni;
	private String direccion;
	private String telefono;
	private String email;
	
}
