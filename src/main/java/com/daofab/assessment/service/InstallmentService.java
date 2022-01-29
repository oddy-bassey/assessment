package com.daofab.assessment.service;

import com.daofab.assessment.dto.InstallmentDto;
import com.daofab.assessment.model.Installment;
import com.daofab.assessment.model.Transaction;

import java.util.List;

// Defining an abstract type which defines the behavior an installment service class must implement
public interface InstallmentService {

	void saveAll(List<InstallmentDto> data);
	
	List<Installment> getInstallmentsByTransaction(Transaction transaction);
}
