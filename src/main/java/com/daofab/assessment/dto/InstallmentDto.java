package com.daofab.assessment.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * Class DTO for transferring Installment data 
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InstallmentDto {

	private Integer id;
	private Integer parentId; 
	private BigDecimal paidAmount;
	private String sender;
	private String receiver;
	private BigDecimal totalAmount;
}
