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
import com.daofab.assessment.error.RequestError;
import com.daofab.assessment.mapper.InstallmentMapper;
import com.daofab.assessment.model.Transaction;
import com.daofab.assessment.service.InstallmentService;
import com.daofab.assessment.service.TransactionService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "Installment (Child)")
@RequiredArgsConstructor
@RequestMapping("/api/v1/installments")
@RestController
public class InstallmentRestController {

	// Injecting transaction and installment service dependencies
	private final InstallmentService installmentService;
	private final TransactionService transactionService;
	
	// get end-point to retrieve installment data based on the transaction id
	@Operation(summary = "Get Installment (Child data)", responses = {
            @ApiResponse(description = "Get installment success", responseCode = "200",
                    content = @Content(mediaType = "application/json",schema = @Schema(implementation = Transaction.class))),
            @ApiResponse(description = "No such transaction with id exist",responseCode = "500",content = @Content)
    })
	@GetMapping("/{transactionId}")
	public ResponseEntity<?> getInstallments(@PathVariable int transactionId){
		
		Optional<Transaction> transaction = transactionService.findById(transactionId);
		
		// return an error message if no transaction is found with the transaction id
		if(transaction.isEmpty()) {
			return new ResponseEntity<>(RequestError.builder().error("No such transaction with id("+transactionId+") exist").build(), HttpStatus.NOT_FOUND);
		}
		
		// converting the installment data model to DTO (data transfer object)
				List<InstallmentDto> installmentsDto= installmentService.getInstallmentsByTransaction(transaction.get()).stream()
						.map(InstallmentMapper::modelToDto)
						.collect(Collectors.toList());
				
		return new ResponseEntity<>(installmentsDto, HttpStatus.OK);
	}
}
