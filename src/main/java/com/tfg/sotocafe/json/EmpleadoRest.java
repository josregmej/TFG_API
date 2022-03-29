package com.tfg.sotocafe.json;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@EqualsAndHashCode(callSuper = false)
public class EmpleadoRest extends PersonRest{

	private static final long serialVersionUID = 1L;
	private Long id;
	private Double horasTrabajadas;
	private String faltas;
	private String horario;
	private Double salario;
	
	@Builder
	public EmpleadoRest(String nombre, String dni, String direccion, String telefono, String email, Long id, Double horas, String faltas, String horario, Double salario) {
		super(nombre,dni,direccion,telefono,email);
		this.id = id;
		this.horasTrabajadas = horas;
		this.faltas = faltas;
		this.horario = horario;
		this.salario = salario;
	}
	
}
