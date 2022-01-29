package com.daofab.assessment.controller;
 
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.daofab.assessment.dto.InstallmentDto;
import com.daofab.assessment.mapper.InstallmentMapper;
import com.daofab.assessment.model.Transaction;
import com.daofab.assessment.service.InstallmentService;
import com.daofab.assessment.service.TransactionService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/api/v1/installments")
@RestController
public class InstallmentRestController {

	// Injecting transaction and installment service dependencies
	private final InstallmentService installmentService;
	private final TransactionService transactionService;
	
	// get end-point to retrieve installment data based on the transaction id
	@GetMapping("/{transactionId}")
	public ResponseEntity<?> getInstallments(@PathVariable int transactionId){
		
		Optional<Transaction> transaction = transactionService.findById(transactionId);
		if(transaction.isEmpty()) return null;
		
		// converting the installment data model to DTO (data transfer object)
				List<InstallmentDto> installmentsDto= installmentService.getInstallmentsByTransaction(transaction.get()).stream()
						.map(InstallmentMapper::modelToDto)
						.collect(Collectors.toList());
				
		return new ResponseEntity<>(installmentsDto, HttpStatus.OK);
	}
}
