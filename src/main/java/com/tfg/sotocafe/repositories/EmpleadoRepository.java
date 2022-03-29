package com.tfg.sotocafe.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tfg.sotocafe.entitites.Empleado;

public interface EmpleadoRepository extends JpaRepository<Empleado, Long>{
	
	public Empleado findByEmail(String email);
	public Empleado findByNombre(String nombre);
}
