package com.crm.authentication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.crm.authentication.dto.RegisterUser;
import com.crm.authentication.entities.User;
import com.crm.authentication.exception.InvalidCredentialsException;
import com.crm.authentication.jwt.JwtUtil;
import com.crm.authentication.mapper.UserMapper;
import com.crm.authentication.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	 
	    @Autowired
	    private UserRepository userRepository;
	 
	    @Autowired
	    private PasswordEncoder passwordEncoder;
	    @Autowired
	    private JwtUtil jwtUtil;
	    @Override
		public RegisterUser registerUser(RegisterUser registerUser) {
			User user = UserMapper.toRegisterEntity(registerUser);
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			User savedUser = userRepository.save(user);
			return UserMapper.toRegisterDTO(savedUser);
		}
	 
	    @Override
	    public String loginUser(String username, String password) throws InvalidCredentialsException {
	        User user = userRepository.findByUsername(username);
	        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
	            return jwtUtil.generateToken(user.getUsername(), user.getRole());
	        } else {
	        	throw new InvalidCredentialsException("Invalid credentials...");
	        }
	    }
}
