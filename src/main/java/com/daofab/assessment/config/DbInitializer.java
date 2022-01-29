package com.daofab.assessment.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import com.daofab.assessment.dto.ChildData;
import com.daofab.assessment.dto.ParentData;
import com.daofab.assessment.mapper.TransactionMapper;
import com.daofab.assessment.model.Transaction;
import com.daofab.assessment.service.InstallmentService;
import com.daofab.assessment.service.TransactionService;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Component
public class DbInitializer {

	private InputStream fileInputStream;
    private ResourceLoader resourceLoader;
    private Resource resource;
    private final ObjectMapper objectMapper;
    
    private final TransactionService transactionService;
    private final InstallmentService installmentService;
    
    // A helper method that runs immediately after dependency injection to initialize the Database (H2 db) with data
	@PostConstruct
	public void uploadData() {
		
		// Uploading the Parent.jdon data (Transactions) and Child.json data (Installments) into database
		uploadTransactionData();
		uploadInstallmentData();
	}
	
	public void uploadTransactionData() {
		resourceLoader = new DefaultResourceLoader();
		
		// acquiring the resource location
        resource = resourceLoader.getResource("classpath:data/Parent.json");
        try {
        	// loading Parent (Transaction) data from file Parent.json
            fileInputStream = resource.getInputStream();
            ParentData parentData = objectMapper.readValue(fileInputStream, ParentData.class);
            log.info("Parent ** "+objectMapper.writeValueAsString(parentData));
  
         // converting transaction dto's to data model
    		List<Transaction> transactions = parentData.getData().stream()
    				.map(TransactionMapper::dtoToModel)
    				.collect(Collectors.toList());
    		
            transactionService.saveAll(transactions);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
	}
	
	public void uploadInstallmentData() {
		resourceLoader = new DefaultResourceLoader();
		
		// acquiring the resource location
        resource = resourceLoader.getResource("classpath:data/Child.json");
        
        try {
        	// loading Child (Installment) data from file Child.json
            fileInputStream = resource.getInputStream();
            ChildData childData = 	objectMapper.readValue(fileInputStream, ChildData.class);

            log.info("Child ** "+objectMapper.writeValueAsString(childData));
  
            installmentService.saveAll(childData.getData());
        } catch (IOException e) {
            log.error(e.getMessage());
        }
	}
}
