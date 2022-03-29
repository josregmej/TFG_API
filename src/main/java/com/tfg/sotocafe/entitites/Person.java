package com.tfg.sotocafe.entitites;

import java.io.Serializable;

import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import com.tfg.sotocafe.utils.DniConstraint;

import lombok.AllArgsConstructor;
import lombok.Data;

@MappedSuperclass
@Data
@AllArgsConstructor
public class Person implements Serializable{

	private static final long serialVersionUID = 1L;

	@NotEmpty
	private String nombre;
	
	@DniConstraint
	private String dni;
	
	@NotEmpty
	private String direccion;
	
	@NotEmpty
	@Pattern(regexp="\\d{9}",message = "Debe contener 9 números")
	private String telefono;
	
	@NotEmpty
	private String email;

}
