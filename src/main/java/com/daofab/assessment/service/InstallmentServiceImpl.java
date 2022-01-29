package com.daofab.assessment.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.daofab.assessment.dto.InstallmentDto;
import com.daofab.assessment.mapper.InstallmentMapper;
import com.daofab.assessment.model.Installment;
import com.daofab.assessment.model.Transaction;
import com.daofab.assessment.repository.InstallmentRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Service
public class InstallmentServiceImpl  implements InstallmentService{

	private final InstallmentRepository installmentRepository;
	private final TransactionService transactionService;
	
	@Override
	public void saveAll(List<InstallmentDto> data) {
		
		// converting installment dto's to data models
		List<Installment> installments =  new ArrayList<>();
		
		for(InstallmentDto installmentDto : data) {
			 
			Transaction transaction = transactionService.findById(installmentDto.getParentId()).get();
			Installment installment = InstallmentMapper.dtoToModel(installmentDto, transaction);
			installments.add(installment);
		}
		
		// save to database
		installmentRepository.saveAll(installments);
	}

	@Override
	public List<Installment> getInstallmentsByTransaction(Transaction transaction) { 
		// find related installments by transaction (referencing transaction id)
		
		return  installmentRepository.findByTransactionOrderById(transaction); 
	}

}
