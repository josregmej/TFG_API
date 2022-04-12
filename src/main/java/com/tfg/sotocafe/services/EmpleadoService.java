package com.tfg.sotocafe.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tfg.sotocafe.entitites.Empleado;
import com.tfg.sotocafe.json.EmpleadoRest;
import com.tfg.sotocafe.repositories.EmpleadoRepository;

@Service
public class EmpleadoService {
	
	@Autowired
	private EmpleadoRepository empleadoRepository;
	
	private ModelMapper modelMapper = new ModelMapper();
	
	@Transactional
	public EmpleadoRest saveEmpleado(EmpleadoRest empleado) {
		Empleado newEmpleado = Empleado.builder()
				.nombre(empleado.getNombre())
				.direccion(empleado.getDireccion())
				.dni(empleado.getDni())
				.email(empleado.getEmail())
				.telefono(empleado.getTelefono())
				.id(empleado.getId())
				.faltas(empleado.getFaltas())
				.horario(empleado.getHorario())
				.horas(empleado.getHorasTrabajadas())
				.salario(empleado.getSalario())
				.build();
		empleadoRepository.save(newEmpleado);
		return modelMapper.map(newEmpleado, EmpleadoRest.class);
	}
	
	@Transactional
	public EmpleadoRest modifyEmpleado(Long empleadoId, EmpleadoRest empleadoRest) {
		Empleado empleado = empleadoRepository.getById(empleadoId);
		empleado.setDireccion(empleadoRest.getDireccion());
		empleado.setDni(empleadoRest.getDni());
		empleado.setEmail(empleadoRest.getEmail());
		empleado.setNombre(empleadoRest.getNombre());
		empleado.setTelefono(empleadoRest.getTelefono());
		empleado.setFaltas(empleadoRest.getFaltas());
		empleado.setHorario(empleadoRest.getHorario());
		empleado.setHorasTrabajadas(empleadoRest.getHorasTrabajadas());
		empleado.setSalario(empleadoRest.getSalario());
		empleadoRepository.save(empleado);
		return modelMapper.map(empleado, EmpleadoRest.class);
	}
	
	@Transactional
	public EmpleadoRest getEmpleadoByNombre(String nombre){
		return modelMapper.map(empleadoRepository.findByNombre(nombre), EmpleadoRest.class);
	}
	
	@Transactional
	public EmpleadoRest getEmpleadoById(Long id){
		return modelMapper.map(empleadoRepository.getById(id), EmpleadoRest.class);
	}
	
	@Transactional
	public List<EmpleadoRest> getAllEmpleados() {
		return empleadoRepository.findAll().stream().map(empleado -> modelMapper.map(empleado, EmpleadoRest.class))
				.collect(Collectors.toList());
	}
	
	@Transactional
	public void deleteEmpleado(Long id){
		Empleado empleado = empleadoRepository.getById(id);
		empleadoRepository.delete(empleado);
	}

}
