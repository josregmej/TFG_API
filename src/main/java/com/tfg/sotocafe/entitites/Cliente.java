package com.tfg.sotocafe.entitites;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "clientes")
@Data
@EqualsAndHashCode(callSuper = false)
public class Cliente extends Person{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private Boolean tieneIVA;
	
	@Builder
	public Cliente(String nombre, String dni, String direccion, String telefono, String email, Long id, Boolean iva) {
		super(nombre,dni,direccion,telefono,email);
		this.id = id;
		this.tieneIVA = iva;
	}

}
