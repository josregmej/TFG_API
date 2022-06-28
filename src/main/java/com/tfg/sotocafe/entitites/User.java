package com.tfg.sotocafe.entitites;

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
import javax.validation.constraints.NotEmpty;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "users")
@Data
@EqualsAndHashCode(callSuper = false)
public class User extends Person{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String username;	
	
	@NotEmpty
	private String password;

	@ManyToMany(fetch = FetchType.EAGER, cascade=CascadeType.PERSIST)
	@JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "USER_ID", referencedColumnName = "id"), 
		inverseJoinColumns = @JoinColumn(name = "ROL_ID", referencedColumnName = "id"))
	private Set<Rol> roles;

	@Builder
	public User(String nombre, String dni, String direccion, String telefono, String email, String username,Long id, String password, Set<Rol> roles) {
		super(nombre,dni,direccion,telefono,email);
		this.username = username;
		this.password = password;
		this.id=id;
		this.roles = roles;
	}
	
	public User() {
		super();
	}
}
