package com.tfg.sotocafe.repositories;


import java.util.Optional;

import com.tfg.sotocafe.entitites.Producto;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Long>{
    
    public Producto findByNombre(String nombre);
    public Boolean existsByNombre(String nombre);
    public Optional<Producto> findById(Long id);

}