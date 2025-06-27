package com.crm.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CrmApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrmApiGatewayApplication.class, args);
		System.out.println("Api Gateway Working...");
	}

}
