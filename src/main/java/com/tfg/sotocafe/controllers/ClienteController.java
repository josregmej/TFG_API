package com.tfg.sotocafe.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.tfg.sotocafe.json.ClienteRest;

public interface ClienteController {

	public ResponseEntity<ClienteRest> createCliente(ClienteRest cliente);
	public ResponseEntity<ClienteRest> modifyCliente(Long id, ClienteRest cliente);
	public ResponseEntity<ClienteRest> getClienteById(Long id);
	public void deleteCliente(Long id);
	public ResponseEntity<List<ClienteRest>> getClientes();
}
