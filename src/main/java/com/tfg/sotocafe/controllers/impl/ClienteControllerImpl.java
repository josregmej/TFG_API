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

import com.tfg.sotocafe.controllers.ClienteController;
import com.tfg.sotocafe.json.ClienteRest;
import com.tfg.sotocafe.services.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteControllerImpl implements ClienteController{

	@Autowired
	private ClienteService clienteService;
	
	@Override
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(value = "/create",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ClienteRest> createCliente(@RequestBody final ClienteRest cliente) {
		return new ResponseEntity<>(clienteService.savecliente(cliente), HttpStatus.CREATED);
	}

	@Override
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ClienteRest> getClienteById(@PathVariable Long id) {
		return new ResponseEntity<>(clienteService.getClienteById(id), HttpStatus.OK);
	}

	@Override
	@ResponseStatus(HttpStatus.OK)
	@DeleteMapping(value = "/{id}/delete",produces = MediaType.APPLICATION_JSON_VALUE)
	public void deleteCliente(@PathVariable Long id) {
		clienteService.deleteCliente(id);
	}

	@Override
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ClienteRest>> getClientes() {
		return new ResponseEntity<>(clienteService.getAllClientes(), HttpStatus.OK);
	}

	@Override
	@ResponseStatus(HttpStatus.OK)
	@PutMapping(value = "/{id}/edit",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ClienteRest> modifyCliente(@PathVariable Long id, @RequestBody final ClienteRest cliente) {
		return new ResponseEntity<>(clienteService.modifyCliente(id, cliente), HttpStatus.OK);
	}

}
