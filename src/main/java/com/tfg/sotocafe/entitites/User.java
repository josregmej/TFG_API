package com.tfg.sotocafe.entitites;

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

import com.tfg.sotocafe.utils.ValidPassword;

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
	@NotEmpty
	@Column(length=60)
	private String username;	
	
	@ValidPassword
	private String password;

	@ManyToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "USERNAME", referencedColumnName = "username"), 
		inverseJoinColumns = @JoinColumn(name = "ROL_ID", referencedColumnName = "id"))
	private Set<Rol> roles;

	@Builder
	public User(String nombre, String dni, String direccion, String telefono, String email, String username, String password, Set<Rol> roles) {
		super(nombre,dni,direccion,telefono,email);
		this.username = username;
		this.password = password;
		this.roles = roles;
	}
	
	public User() {
		super();
	}
}
