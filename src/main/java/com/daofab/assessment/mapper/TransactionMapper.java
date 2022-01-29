package com.daofab.assessment.mapper;

import java.math.BigDecimal;
import java.util.List;

import com.daofab.assessment.dto.TransactionDto;
import com.daofab.assessment.model.Installment;
import com.daofab.assessment.model.Transaction;

/*
 * A helper class to map between Transaction DTO and Transaction objects 
 */

public abstract class TransactionMapper {

	public static Transaction dtoToModel(TransactionDto data) {
		
		return Transaction.builder()
				.id(data.getId())
				.sender(data.getSender())
				.receiver(data.getReceiver())
				.totalAmount(data.getTotalAmount())
				.build();
	}
	
	public static TransactionDto modelToDto(Transaction data, List<Installment> installments) {
		
		BigDecimal totalAmountPaid = new BigDecimal(0.0);
		
		installments.stream().peek(installment -> totalAmountPaid.add(installment.getPaidAmount()));
		
		return TransactionDto.builder()
				.id(data.getId())
				.sender(data.getSender())
				.receiver(data.getReceiver())
				.totalAmount(data.getTotalAmount())
				.totalAmount(totalAmountPaid)
				.build();
	}
}
