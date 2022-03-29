package com.tfg.sotocafe.json;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@EqualsAndHashCode(callSuper = false)
public class ClienteRest extends PersonRest{

	private static final long serialVersionUID = 1L;
	private Long id;
	private Boolean tieneIVA;

	@Builder
	public ClienteRest(String nombre, String dni, String direccion, String telefono, String email, Long id, Boolean tieneIva) {
		super(nombre,dni,direccion,telefono,email);
		this.id = id;
		this.tieneIVA = tieneIva;
	}
}
