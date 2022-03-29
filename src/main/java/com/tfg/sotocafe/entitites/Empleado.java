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
@Table(name = "empleados")
@Data
@EqualsAndHashCode(callSuper = false)
public class Empleado extends Person{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private Double horasTrabajadas;
	private String faltas;
	private String horario;
	private Double salario;
	
	@Builder
	public Empleado(String nombre, String dni, String direccion, String telefono, String email, 
			Long id, Double horas, String faltas, String horario, Double salario) {
		super(nombre,dni,direccion,telefono,email);
		this.id = id;
		this.horasTrabajadas = horas;
		this.faltas= faltas;
		this.horario = horario;
		this.salario = salario;
	}

	public Empleado() {
		super();
	}
}
