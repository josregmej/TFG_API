package com.tfg.sotocafe.controllers.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.tfg.sotocafe.controllers.EmpleadoController;
import com.tfg.sotocafe.json.EmpleadoRest;
import com.tfg.sotocafe.services.EmpleadoService;

@RestController
@RequestMapping("/api/empleados")
public class EmpleadoControllerImpl implements EmpleadoController{

	@Autowired
	private EmpleadoService empleadoService;
	
	@Override
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(value="/create",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EmpleadoRest> createEmpleado(@RequestBody final EmpleadoRest empleado) {
		return new ResponseEntity<>(empleadoService.saveEmpleado(empleado), HttpStatus.CREATED);
	}


	@Override
	@ResponseStatus(HttpStatus.OK)
	@DeleteMapping(value = "/{id}/delete",produces = MediaType.APPLICATION_JSON_VALUE)
	public void deleteEmpleado(@PathVariable Long id) {
		empleadoService.deleteEmpleado(id);
	}

	@Override
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<EmpleadoRest>> getEmpleados() {
		return new ResponseEntity<>(empleadoService.getAllEmpleados(), HttpStatus.OK);
	}

	@Override
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EmpleadoRest> getEmpleadoById(@PathVariable Long id) {
		return new ResponseEntity<>(empleadoService.getEmpleadoById(id), HttpStatus.OK);
	}


	@Override
	@ResponseStatus(HttpStatus.OK)
	@PutMapping(value = "/{id}/edit",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EmpleadoRest> modifyEmpleado(@PathVariable Long id, @RequestBody final EmpleadoRest empleado) {
		return new ResponseEntity<>(empleadoService.modifyEmpleado(id, empleado), HttpStatus.OK);
	}
}
