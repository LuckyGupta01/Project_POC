package com.robobank.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

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

import com.robobank.domain.FileDetails;
import com.robobank.service.CustomerStatementService;

@RestController
@RequestMapping("/api/v1/robobank")
public class CustomerStatementController {
	
	
	@Autowired
	CustomerStatementService customerStatementService;
	
	@GetMapping("/hello")
	public String display() {		
		return "Heyyy";
	}
	
	@PostMapping("/inputfile/{fileName}")
	public String fileDetails(@PathVariable ("fileName") String fileName) {		
		//System.out.println("CustomerStatementController.fileDetails()"+fileName);
		return customerStatementService.readFile(fileName);
		
	}
	
	@RequestMapping(value="/upload" , method = RequestMethod.POST)
	public ResponseEntity<Object> uploadFile(@RequestParam("file") MultipartFile file ) throws IOException
	{
		
		File convertFile = new File("\\home\\buntu\\STS_Workspace\\Project_RoboBank\\src\\data"+file.getOriginalFilename());
		convertFile.createNewFile();
		FileOutputStream fout = new FileOutputStream(convertFile);
		fout.write(file.getBytes());
		fout.close();
		return new ResponseEntity<>("File Uploaded Successfully",HttpStatus.OK);
	}
}
