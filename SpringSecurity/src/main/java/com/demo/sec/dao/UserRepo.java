package com.demo.sec.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.sec.model.User;

public interface UserRepo extends JpaRepository<User, Integer>{
	User findByUsername(String username);
}
