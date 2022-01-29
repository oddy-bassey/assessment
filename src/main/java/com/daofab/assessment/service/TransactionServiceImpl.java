package com.daofab.assessment.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.daofab.assessment.dto.TransactionDto;
import com.daofab.assessment.enums.SortDirection;
import com.daofab.assessment.enums.SortParam;
import com.daofab.assessment.mapper.TransactionMapper;
import com.daofab.assessment.model.Transaction;
import com.daofab.assessment.repository.TransactionRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class TransactionServiceImpl implements TransactionService {

	// auto-wiring the transaction repository
	private final TransactionRepository transactionRepository;
	
	@Override
	public void saveAll(List<Transaction> data) {
		// saving transactions to database
		transactionRepository.saveAll(data);
	}

	@Override
	public Optional<Transaction> findById(Integer id) { 
		// find a transaction by id
		return transactionRepository.findById(id);
	}
	
	@Override
	public Page<Transaction> getTransactionsPaginatedAndSorted(int pageNumber, int pageSize,
			Optional<SortParam> sortParam) {

		// check if the sort parameter is provided
		if(sortParam.isEmpty()) {
			// make request without any sort parameter
			return  transactionRepository.findAll(PageRequest.of(pageNumber, pageSize));
		}
		// make request with sort parameter
		return   transactionRepository.findAll(PageRequest.of(pageNumber, pageSize, Sort.by(sortParam.get().name())));
	}
}
