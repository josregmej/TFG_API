package com.tfg.sotocafe.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.tfg.sotocafe.entitites.Rol;
import com.tfg.sotocafe.repositories.RolRepository;

@Service
public class RolService {

	@Autowired
	private RolRepository rolRepository;
	
	@Transactional
	public void saveRol(Rol rol) throws DataAccessException {
		rolRepository.save(rol);
	}
}
