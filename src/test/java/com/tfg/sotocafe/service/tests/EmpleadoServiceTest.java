package com.tfg.sotocafe.service.tests;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import com.tfg.sotocafe.json.EmpleadoRest;
import com.tfg.sotocafe.services.EmpleadoService;

@SpringBootTest
@ActiveProfiles("test")
public class EmpleadoServiceTest {
	
	@Autowired
	private EmpleadoService empleadoService;
	
	private EmpleadoRest empleado;
	private EmpleadoRest rest;
	
	@BeforeEach
	public void setUp() {
		empleado = EmpleadoRest.builder().email("email@email.com").dni("53454323F")
				.nombre("UsuarioPrueba1").telefono("938494392").direccion("Calle Calle")
				.faltas("10 faltas").horario("10:00 a 19:00").horas(8.5).salario(1000.34).build();
		rest = this.empleadoService.saveEmpleado(empleado);
	}
	
	@Test
    @Transactional
    void testFindEmpleadoById() {
		Long id = rest.getId();
		EmpleadoRest empleado1 = this.empleadoService.getEmpleadoById(id);
		assertNotNull(id);
		assertNotNull(empleado1);
		assertEquals(rest.getNombre(), empleado1.getNombre());
		assertEquals(rest.getDireccion(), empleado1.getDireccion());
		assertEquals(rest.getDni(), empleado1.getDni());
		assertEquals(rest.getEmail(), empleado1.getEmail());
		assertEquals(rest.getTelefono(), empleado1.getTelefono());
		assertEquals(rest.getFaltas(), empleado1.getFaltas());
		assertEquals(rest.getHorario(), empleado1.getHorario());
		assertEquals(rest.getHorasTrabajadas(), empleado1.getHorasTrabajadas());
		assertEquals(rest.getSalario(), empleado1.getSalario());
	}
	
	
	@Test
    @Transactional
    void testDeleteEmpleado() {
		List<EmpleadoRest> empleados = this.empleadoService.getAllEmpleados();
		
		EmpleadoRest empleadoElm = EmpleadoRest.builder().email("email2@email.com").dni("53454323G")
				.nombre("UsuarioPrueba2").telefono("938494393").direccion("Calle Calle")
				.faltas("10 faltas").horario("10:00 a 19:00").horas(8.5).salario(1000.34).build();
		EmpleadoRest empleadoGuard = this.empleadoService.saveEmpleado(empleadoElm);
		List<EmpleadoRest> empleadosGuard = this.empleadoService.getAllEmpleados();
		
		this.empleadoService.deleteEmpleado(empleadoGuard.getId());
		List<EmpleadoRest> empleadosDesp = this.empleadoService.getAllEmpleados();
		assertNotNull(empleadoElm);
		assertNotNull(empleadoGuard);
		assertEquals(1,empleados.size());
		assertEquals(2,empleadosGuard.size());
		assertEquals(empleadosDesp.size(),empleados.size());
	}
	
	@Test
    @Transactional
    void testGetAllEmpleados() {
		List<EmpleadoRest> empleados = this.empleadoService.getAllEmpleados();
		assertNotNull(empleados);
		assertEquals(4,empleados.size());
	}
	
	@Test
    @Transactional
    void testModifyEmpleado() {
		EmpleadoRest empleado1 = EmpleadoRest.builder().email("email2@email.com").dni("53454323G")
				.nombre("UsuarioPrueba2").telefono("938494393").direccion("Calle Calle")
				.faltas("56 faltas").horario("08:00 a 18:00").horas(8.5).salario(1456.34).build();
		EmpleadoRest empleadoMod = this.empleadoService.saveEmpleado(empleado1);
		empleado = this.empleadoService.modifyEmpleado(empleadoMod.getId(), empleadoMod);
		assertNotNull(empleadoMod);
		assertNotNull(empleado);
		assertEquals(empleado.getNombre(), empleado1.getNombre());
		assertEquals(empleado.getDireccion(), empleado1.getDireccion());
		assertEquals(empleado.getDni(), empleado1.getDni());
		assertEquals(empleado.getEmail(), empleado1.getEmail());
		assertEquals(empleado.getTelefono(), empleado1.getTelefono());
		assertEquals(empleado.getFaltas(), empleado1.getFaltas());
		assertEquals(empleado.getHorario(), empleado1.getHorario());
		assertEquals(empleado.getHorasTrabajadas(), empleado1.getHorasTrabajadas());
		assertEquals(empleado.getSalario(), empleado1.getSalario());
	}
}
