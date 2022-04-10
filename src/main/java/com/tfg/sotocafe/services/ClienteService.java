package com.tfg.sotocafe.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tfg.sotocafe.entitites.Cliente;
import com.tfg.sotocafe.json.ClienteRest;
import com.tfg.sotocafe.repositories.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	private ModelMapper modelMapper = new ModelMapper();
	
	@Transactional
	public ClienteRest savecliente(ClienteRest cliente) {
		Cliente newcliente = Cliente.builder()
				.nombre(cliente.getNombre())
				.email(cliente.getEmail())
				.direccion(cliente.getDireccion())
				.dni(cliente.getDni())
				.telefono(cliente.getTelefono())
				.iva(cliente.getTieneIVA())
				.build();
		clienteRepository.save(newcliente);
		return modelMapper.map(newcliente, ClienteRest.class);
	}
	
	@Transactional
	public ClienteRest modifyCliente(Long clienteId, ClienteRest clienteRest) {
		Cliente cliente = clienteRepository.getById(clienteId);
		System.out.println("Cliente nombre antes: "+cliente.getNombre());
		cliente.setDireccion(clienteRest.getDireccion());
		cliente.setDni(clienteRest.getDni());
		cliente.setEmail(clienteRest.getEmail());
		cliente.setNombre(clienteRest.getNombre());
		cliente.setTelefono(clienteRest.getTelefono());
		cliente.setTieneIVA(clienteRest.getTieneIVA());
		clienteRepository.save(cliente);
		System.out.println("Cliente IVA: "+cliente.getTieneIVA());
		return modelMapper.map(cliente, ClienteRest.class);
	}
	
	@Transactional
	public ClienteRest getClienteByNombre(String nombre){
		return modelMapper.map(clienteRepository.findByNombre(nombre), ClienteRest.class);
	}
	
	@Transactional
	public ClienteRest getClienteById(Long id){
		return modelMapper.map(clienteRepository.getById(id), ClienteRest.class);
	}
	
	@Transactional
	public List<ClienteRest> getAllClientes() {
		return clienteRepository.findAll().stream().map(cliente -> modelMapper.map(cliente, ClienteRest.class))
				.collect(Collectors.toList());
	}
	
	@Transactional
	public void deleteCliente(Long id){
		Cliente cliente = clienteRepository.getById(id);
		clienteRepository.delete(cliente);
	}
}
