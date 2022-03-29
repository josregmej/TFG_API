package com.tfg.sotocafe.entitites;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import com.tfg.sotocafe.utils.DniConstraint;
import com.tfg.sotocafe.utils.ValidPassword;

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
	@NotEmpty
	@Column(length=60)
	private String username;
	
	@NotEmpty
	private String nombre;
	
	@ValidPassword
	private String password;
	
	@DniConstraint
	private String dni;
	
	@NotEmpty
	private String direccion;
	
	@NotEmpty
	@Pattern(regexp="\\d{9}",message = "Debe contener 9 números")
	private String telefono;
	
	@NotEmpty
	private String email;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "USERNAME", referencedColumnName = "username"), 
		inverseJoinColumns = @JoinColumn(name = "ROL_ID", referencedColumnName = "id"))
	private Set<Rol> roles;
	
	public User() {
		super();
	}

	public User( String nombre, String username, String password, String dni, String direccion, String telefono,
			String email, Set<Rol> roles) {
		super();
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
