package com.tfg.sotocafe.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tfg.sotocafe.entitites.Rol;

public interface RolRepository extends JpaRepository<Rol, Long>{

	public Optional<Rol> findByNombre(String nombre);
}
