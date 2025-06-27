package com.crm.customer.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crm.customer.dto.CustomerDTO;
import com.crm.customer.entities.Customer;
import com.crm.customer.exception.CustomerNotFoundException;
import com.crm.customer.mapper.CustomerMapper;
import com.crm.customer.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
    private CustomerRepository customerRepo;
 
    @Override
    public CustomerDTO createCustomer(CustomerDTO customerDTO) {
        Customer customer = CustomerMapper.toEntity(customerDTO);
        Customer savedCustomer = customerRepo.save(customer);
        return CustomerMapper.toDTO(savedCustomer);
    }
 
    @Override
    public List<CustomerDTO> getAllCustomers()
    {
       return customerRepo.findAll().stream().map(CustomerMapper::toDTO).collect(Collectors.toList());
    }
 
    @Override
    public CustomerDTO getCustomerById(Integer customerId) throws CustomerNotFoundException
    {
        Optional<Customer> optionalCustomer = customerRepo.findById(customerId);
        if (optionalCustomer.isPresent())
        {
            return CustomerMapper.toDTO(optionalCustomer.get());
        }
        else
        {
            throw new CustomerNotFoundException("Customer not found");
        }
    }
 
    @Override
    public CustomerDTO updateCustomerById(Integer customerId, CustomerDTO customerDTO) throws CustomerNotFoundException
    {
        Optional<Customer> optionalCustomer = customerRepo.findById(customerId);
        if (optionalCustomer.isPresent())
        {
            Customer customer = CustomerMapper.toEntity(customerDTO);
            customer.setCustomerId(customerId);
            Customer updatedCustomer = customerRepo.save(customer);
            return CustomerMapper.toDTO(updatedCustomer);
        }
        else
        {
            throw new CustomerNotFoundException("Customer not found");
        }
    }
 
    @Override
    public void deleteCustomerById(Integer customerId) throws CustomerNotFoundException {
        Optional<Customer> optionalCustomer = customerRepo.findById(customerId);
        if (optionalCustomer.isPresent())
        {
            customerRepo.deleteById(customerId);
        }
        else
        {
            throw new CustomerNotFoundException("Customer not found");
        }
    }
}