package com.tfg.sotocafe.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tfg.sotocafe.entitites.User;

public interface UserRepository extends JpaRepository<User, Long>{

	public User findByEmail(String email);
	public User findByUsernameOrEmail(String username,String email);
	public User findByUsername(String username);
	public Boolean existsByUsername(String username);
	public Boolean existsByEmail(String email);
}
