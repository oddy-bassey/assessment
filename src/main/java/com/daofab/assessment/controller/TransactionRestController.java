package com.daofab.assessment.controller;
 
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException; 

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.daofab.assessment.dto.TransactionDto;
import com.daofab.assessment.enums.SortParam;
import com.daofab.assessment.model.Installment;
import com.daofab.assessment.model.Transaction;
import com.daofab.assessment.service.InstallmentService;
import com.daofab.assessment.service.TransactionService; 

import lombok.RequiredArgsConstructor; 
 
@RequiredArgsConstructor
@RequestMapping("/api/v1/transactions")
@RestController
public class TransactionRestController {
	 
	// Injecting transaction and installment service dependencies
	private final TransactionService transactionService;
	private final InstallmentService installmentService;

	// get end-point to fetch transactions (Parent.json data) using pagination
	@GetMapping("/{pageNo}/{pageSize}")
	public ResponseEntity<?> getTransactions(@PathVariable int pageNo, @PathVariable int pageSize,
			@RequestParam(name = "sortBy", required = false) Optional<SortParam> sortBy) throws InterruptedException, ExecutionException{
		
		// get the paged list of transactions
		List<Transaction> transactions = transactionService.getTransactionsPaginatedAndSorted(pageNo, pageSize, sortBy).getContent();
		
		// convert transactions data model to DTO (data transfer objects)
		List<TransactionDto> transactionsDto = new ArrayList<>();
		
		for(Transaction data : transactions) {
			
			// retrieving installment DTO (data transfer objects) from async method to improve execution time 
			transactionsDto.add(executeRequest(data).get()); 
		}
		
		return new ResponseEntity<>(transactionsDto, HttpStatus.OK);
	}
	
	// Async method to execute asynchronous requests
	@Async
    CompletableFuture<TransactionDto> executeRequest(Transaction transaction){
		BigDecimal totalAmtPaid = new BigDecimal(0);
		
		//  retrieving all installment payment for a transaction
		List<Installment> installments = installmentService.getInstallmentsByTransaction(transaction);
 
		// calculating the total amount paid
		for(Installment installment : installments) { 
			totalAmtPaid = totalAmtPaid.add(installment.getPaidAmount()); 
		}
		
		// creating a corresponding DTO (data transfer objects) for a transaction
		TransactionDto transactionDto = TransactionDto.builder()
				.id(transaction.getId())
				.sender(transaction.getSender())
				.receiver(transaction.getReceiver())
				.totalAmount(transaction.getTotalAmount())
				.totalPaidAmount(totalAmtPaid)
				.build();
		return CompletableFuture.completedFuture(transactionDto);
	} 
}
