package com.tfg.sotocafe.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.tfg.sotocafe.json.EmpleadoRest;

public interface EmpleadoController {

	public ResponseEntity<EmpleadoRest> createEmpleado(EmpleadoRest empleado);
	public ResponseEntity<EmpleadoRest> modifyEmpleado(Long id, EmpleadoRest empleado);
	public ResponseEntity<EmpleadoRest> getEmpleadoById(Long id);
	public void deleteEmpleado(Long id);
	public ResponseEntity<List<EmpleadoRest>> getEmpleados();
}
