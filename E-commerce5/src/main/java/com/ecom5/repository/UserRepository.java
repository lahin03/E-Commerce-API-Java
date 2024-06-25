package com.ecom5.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecom5.model.User;

public interface UserRepository extends JpaRepository<User,Long> {
	
	public User findByEmail(String email);
}
