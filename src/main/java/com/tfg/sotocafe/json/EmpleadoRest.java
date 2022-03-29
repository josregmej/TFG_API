package com.tfg.sotocafe.json;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmpleadoRest extends UserRest{

	private static final long serialVersionUID = 1L;
	private Long id;
	private Double horasTrabajadas;
	private String faltas;
	private String horario;
	private Double salario;
	
	public EmpleadoRest(Long id, Double horas, String faltas, String horario, Double salario) {
		super();
		this.id = id;
		this.horasTrabajadas = horas;
		this.faltas = faltas;
		this.horario = horario;
		this.salario = salario;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getHorasTrabajadas() {
		return horasTrabajadas;
	}

	public void setHorasTrabajadas(Double horasTrabajadas) {
		this.horasTrabajadas = horasTrabajadas;
	}

	public String getFaltas() {
		return faltas;
	}

	public void setFaltas(String faltas) {
		this.faltas = faltas;
	}

	public String getHorario() {
		return horario;
	}

	public void setHorario(String horario) {
		this.horario = horario;
	}

	public Double getSalario() {
		return salario;
	}

	public void setSalario(Double salario) {
		this.salario = salario;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((faltas == null) ? 0 : faltas.hashCode());
		result = prime * result + ((horario == null) ? 0 : horario.hashCode());
		result = prime * result + ((horasTrabajadas == null) ? 0 : horasTrabajadas.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((salario == null) ? 0 : salario.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		EmpleadoRest other = (EmpleadoRest) obj;
		if (faltas == null) {
			if (other.faltas != null)
				return false;
		} else if (!faltas.equals(other.faltas))
			return false;
		if (horario == null) {
			if (other.horario != null)
				return false;
		} else if (!horario.equals(other.horario))
			return false;
		if (horasTrabajadas == null) {
			if (other.horasTrabajadas != null)
				return false;
		} else if (!horasTrabajadas.equals(other.horasTrabajadas))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (salario == null) {
			if (other.salario != null)
				return false;
		} else if (!salario.equals(other.salario))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "EmpleadoRest [id=" + id + ", horasTrabajadas=" + horasTrabajadas + ", faltas=" + faltas + ", horario="
				+ horario + ", salario=" + salario + "]";
	}

}
