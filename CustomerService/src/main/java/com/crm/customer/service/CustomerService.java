package com.crm.customer.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.crm.customer.dto.CustomerDTO;
import com.crm.customer.entities.Customer;
import com.crm.customer.exception.CustomerNotFoundException;

@Service
public interface CustomerService {
	
	CustomerDTO createCustomer(CustomerDTO customerDTO);
	List<CustomerDTO> getAllCustomers();
	CustomerDTO getCustomerById(Integer customerId) throws CustomerNotFoundException;
	CustomerDTO updateCustomerById(Integer customerId, CustomerDTO customerDTO) throws CustomerNotFoundException;
	void deleteCustomerById(Integer customerId) throws CustomerNotFoundException;

}
