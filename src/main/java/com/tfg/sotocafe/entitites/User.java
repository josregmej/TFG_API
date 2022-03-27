package com.tfg.sotocafe.entitites;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Data;

@Entity
@Table(name = "users")
@Data
@Builder
public class User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nombre;
	private String username;
	private String password;
	private String dni;
	private String direccion;
	private String telefono;
	private String email;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "USER_ID", referencedColumnName = "id"), 
		inverseJoinColumns = @JoinColumn(name = "ROL_ID", referencedColumnName = "id"))
	private Set<Rol> roles;
	
	public User() {
		super();
	}

	public User(Long id, String nombre, String username, String password, String dni, String direccion, String telefono,
			String email, Set<Rol> roles) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.username = username;
		this.password = password;
		this.dni = dni;
		this.direccion = direccion;
		this.telefono = telefono;
		this.email = email;
		this.roles = roles;
	}
}
