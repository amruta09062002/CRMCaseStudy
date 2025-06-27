package com.crm.customer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crm.customer.dto.CustomerDTO;
import com.crm.customer.exception.CustomerNotFoundException;
import com.crm.customer.service.CustomerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/customer")
@CrossOrigin(origins = "http://localhost:4200")
public class CustomerController {
	@Autowired
	private CustomerService customerService;

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/createCustomer")
	public ResponseEntity<String> createCustomer(@RequestBody @Valid CustomerDTO customer) {
		customerService.createCustomer(customer);
		return ResponseEntity.ok("Customer details added successfully..");
	}

	@PreAuthorize("hasAnyRole('ADMIN', 'SALES', 'SUPPORT')")
	@GetMapping("/getAllCustomers")
	public ResponseEntity<List<CustomerDTO>> getAllCustomers() {
		return new ResponseEntity<>(customerService.getAllCustomers(), HttpStatus.OK);
	}

	@PreAuthorize("hasAnyRole('ADMIN', 'SALES')")
	@PutMapping("/updateCustomerById/{customerId}")
	public ResponseEntity<String> updateCustomerById(@PathVariable Integer customerId,
			@RequestBody CustomerDTO customer) throws CustomerNotFoundException {
		customerService.updateCustomerById(customerId, customer);
		return ResponseEntity.ok("Customer details updated successfully..");
	}

	@PreAuthorize("hasAnyRole('ADMIN', 'SALES', 'SUPPORT')")
	@GetMapping("/getCustomerById/{customerId}")
	public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable Integer customerId)
			throws CustomerNotFoundException {
		return new ResponseEntity<>(customerService.getCustomerById(customerId), HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/deleteCustomerById/{customerId}")
	public ResponseEntity<String> deleteCustomerById(@PathVariable Integer customerId)
			throws CustomerNotFoundException {
		customerService.deleteCustomerById(customerId);
		return ResponseEntity.ok("Customer details deleted successfully..");
	}
}