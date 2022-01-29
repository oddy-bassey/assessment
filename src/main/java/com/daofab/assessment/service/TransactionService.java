package com.daofab.assessment.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.daofab.assessment.dto.TransactionDto;
import com.daofab.assessment.enums.SortDirection;
import com.daofab.assessment.enums.SortParam;
import com.daofab.assessment.model.Transaction;

//Defining an abstract type which defines the behavior an installment service class must implement
public interface TransactionService {

	void saveAll(List<Transaction> data);
	
	Optional<Transaction> findById(Integer id);
	
	Page<Transaction> getTransactionsPaginatedAndSorted(
			int pageNumber, int pageSize, Optional<SortParam> sortParam); 
}
