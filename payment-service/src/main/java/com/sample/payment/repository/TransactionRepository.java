package com.sample.payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sample.payment.entity.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

}
