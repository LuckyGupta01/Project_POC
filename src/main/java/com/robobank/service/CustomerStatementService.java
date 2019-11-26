package com.robobank.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.robobank.domain.CustomerStatement;
import com.robobank.domain.FileDetails;

public interface CustomerStatementService {	
	
	List<CustomerStatement> readFile(String fileName);
	List<String> validateData(List<CustomerStatement> records);
}
