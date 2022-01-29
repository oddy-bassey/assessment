package com.daofab.assessment.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.daofab.assessment.model.Transaction;

//Class defining Transaction object JPA repository for DB transactions

public interface TransactionRepository  extends JpaRepository<Transaction, Integer>{

	Optional<Transaction> findById(Integer id);
}
