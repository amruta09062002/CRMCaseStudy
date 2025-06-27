package com.crm.gateway.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class FallbackController {

	
    public FallbackController() {
		super();
		System.out.println("FallbackController loaded....");
	}
	@GetMapping("/fallback")
    public String fallback() {
        return "Service is unavailable, please try again later.";
    }
   
}