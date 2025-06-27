package com.crm.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crm.customer.entities.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

}
