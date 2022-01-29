package com.daofab.assessment.mapper;
 
import org.springframework.stereotype.Component;

import com.daofab.assessment.dto.InstallmentDto;
import com.daofab.assessment.model.Installment;
import com.daofab.assessment.model.Transaction; 

/*
 * A helper class to map between Installment DTO and Model objects 
 */

@Component
public abstract class InstallmentMapper { 

	public static Installment dtoToModel(InstallmentDto data, Transaction transaction) { 
				
		return Installment.builder()
				.id(data.getId())
				.paidAmount(data.getPaidAmount())
				.transaction(transaction)
				.build();
	}
	
	public static InstallmentDto modelToDto(Installment data) {
		
		return InstallmentDto.builder()
				.id(data.getId())
				.paidAmount(data.getPaidAmount())
				.parentId(data.getTransaction().getId())
				.sender(data.getTransaction().getSender())
				.receiver(data.getTransaction().getReceiver())
				.totalAmount(data.getTransaction().getTotalAmount())
				.build();
	}
}
