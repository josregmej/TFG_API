package com.tfg.sotocafe.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tfg.sotocafe.entitites.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{
	
	public Cliente findByEmail(String email);
	public Cliente findByNombre(String nombre);

}
