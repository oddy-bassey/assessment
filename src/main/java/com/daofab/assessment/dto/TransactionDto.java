package com.daofab.assessment.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * Class DTO for transferring Transaction data 
 */ 

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionDto {

	private Integer id;
	private String sender;
	private String receiver;
	private BigDecimal totalAmount;
	private BigDecimal totalPaidAmount;
}
