package com.robobank.controller;



import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.robobank.domain.CustomerStatement;
import com.robobank.domain.ReportData;
import com.robobank.service.CustomerStatementService;

@RestController
@RequestMapping("/api/v1/robobank")
public class CustomerStatementController {
	
	
	private static final Logger logger = LoggerFactory.getLogger(CustomerStatementController.class);

	@Autowired
	ReportData reportData;
	
	@Autowired
	CustomerStatementService customerStatementService;	
	
	@GetMapping("/inputfile/{fileName}")
	public ResponseEntity<ReportData> fileRecords(@PathVariable ("fileName") String fileName) {	
		logger.info("In reportData() CustomerStatementController ");
		List<CustomerStatement> records = customerStatementService.readFile(fileName);
		List<String> list = customerStatementService.validateData(records);
		
		reportData.setRecords(list);
		return new ResponseEntity<ReportData>(reportData,HttpStatus.OK);
	  }	
}
