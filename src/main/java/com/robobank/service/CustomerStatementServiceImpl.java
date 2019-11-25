package com.robobank.service;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.robobank.domain.CustomerStatement;
import com.robobank.domain.FileDetails;
import com.robobank.repository.StatementRepository;

@Service
public class CustomerStatementServiceImpl implements CustomerStatementService {

	
	@Autowired
	StatementRepository stRepo;
	
	List<CustomerStatement> custstatements = new ArrayList<CustomerStatement>();
	
	@Override
	public String readFile(String fileName) {
		
		String x[] =fileName.split("\\.");
		
		String filetype= x[1].toString();
		
		if(filetype.equalsIgnoreCase("csv"))
		{
			readFileCSV(fileName);
			display();
			return "Succccccc";
		}
		else
		{
			readFileXML(fileName);
			display();
			return "Succcttttcccc";
		}
	
		
	}
	
	private void readFileCSV(String fileName)
	{
		Path statementsfilePath = Paths.get("src/data/records.csv");
		custstatements = stRepo.initializeDataFromFile(statementsfilePath);
		
		
	}
	
	
	private void readFileXML(String fileName)
	{
		Path statementsfilePath = Paths.get("src/data/records.xml");
		try {
			custstatements = stRepo.initializeDataFile(statementsfilePath);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		
		
	}
	
	public void display() {
		for(CustomerStatement st : custstatements)
			System.out.println(st.getAccount_Number()+"---------------"+st.getReference());
	}
	
}

