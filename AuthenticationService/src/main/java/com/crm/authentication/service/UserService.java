package com.crm.authentication.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import com.crm.authentication.dto.RegisterUser;
import com.crm.authentication.exception.InvalidCredentialsException;

@Service
public interface UserService {
	
	RegisterUser registerUser(RegisterUser registerUser);

	String loginUser(String username, String password) throws InvalidCredentialsException;
	
	
}
