package com.daofab.assessment.repository;

import java.util.List;
 
import com.daofab.assessment.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import com.daofab.assessment.model.Installment; 

// Class defining Installment object JPA repository for DB transactions

public interface InstallmentRepository extends JpaRepository<Installment, Integer>{

	List<Installment> findByTransactionOrderById(Transaction transaction);
}
