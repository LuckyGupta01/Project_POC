package com.robobank.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.robobank.domain.CustomerStatement;
import com.robobank.domain.FileDetails;
import com.robobank.service.CustomerStatementService;
import com.robobank.service.CustomerStatementServiceImpl;

@RestController
@RequestMapping("/api/v1/robobank")
public class CustomerStatementController {
	
	
	private static final Logger logger = LoggerFactory.getLogger(CustomerStatementController.class);

	
	@Autowired
	CustomerStatementService customerStatementService;	
	
	@GetMapping("/inputfile/{fileName}")
	public ResponseEntity<List<String>> fileDetails(@PathVariable ("fileName") String fileName) {	
		logger.info("In fileDetails() CustomerStatementController ");
		List<CustomerStatement> records = customerStatementService.readFile(fileName);
		List<String> list = customerStatementService.validateData(records);
		
		return new ResponseEntity<>(list, HttpStatus.OK);
	  }		
	
	
	/*
	 * @RequestMapping(value="/upload" , method = RequestMethod.POST) public
	 * ResponseEntity<Object> uploadFile(@RequestParam("file") MultipartFile file )
	 * throws IOException {
	 * 
	 * File convertFile = new
	 * File("\\home\\buntu\\STS_Workspace\\Project_RoboBank\\src\\data"+file.
	 * getOriginalFilename()); convertFile.createNewFile(); FileOutputStream fout =
	 * new FileOutputStream(convertFile); fout.write(file.getBytes()); fout.close();
	 * return new ResponseEntity<>("File Uploaded Successfully",HttpStatus.OK); }
	 */
	
}
