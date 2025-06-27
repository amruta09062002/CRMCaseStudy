package com.crm.customer.mapper;

import com.crm.customer.dto.CustomerDTO;
import com.crm.customer.entities.Customer;

public class CustomerMapper {
	
	public static CustomerDTO toDTO(Customer customer) {
        return new CustomerDTO(
            customer.getCustomerId(),
            customer.getCustomerName(),
            customer.getCustomerEmail(),
            customer.getPhoneNo(),
            customer.getInteractions()
        );
    }
 
    public static Customer toEntity(CustomerDTO customerDTO) {
        return new Customer(
            customerDTO.getCustomerId(),
            customerDTO.getCustomerName(),
            customerDTO.getCustomerEmail(),
            customerDTO.getPhoneNo(),
            customerDTO.getInteractions()
        );
    }
}
