package com.crm.authentication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.crm.authentication.dto.RegisterUser;
import com.crm.authentication.entities.User;
import com.crm.authentication.exception.InvalidCredentialsException;
import com.crm.authentication.jwt.JwtUtil;
import com.crm.authentication.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private UserService userService;

	@PostMapping("/register")
	public ResponseEntity<String> registerUser(@RequestBody @Valid RegisterUser registerUser) {
		RegisterUser savedUser = userService.registerUser(registerUser);
		return ResponseEntity.ok("Registered successfully");
	}

	@PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody User user) throws InvalidCredentialsException {
        String token = userService.loginUser(user.getUsername(), user.getPassword());
            return ResponseEntity.ok(token);
	}
}
