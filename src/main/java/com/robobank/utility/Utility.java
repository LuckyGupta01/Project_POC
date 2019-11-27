package com.robobank.utility;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.robobank.domain.CustomerStatement;
import com.robobank.repository.StatementRepository;

@Component
public class Utility {


	@Value("${dir.path}")
	String path;
	
	@Autowired
	StatementRepository statementRepo;
	
	public List<CustomerStatement>  readFileCSV(String fileName)
	{
		List<CustomerStatement> statements = new ArrayList<CustomerStatement>();
		Path statementsfilePath = Paths.get(path+fileName);
		statements = statementRepo.initializeDataFromFile(statementsfilePath);
		return statements;
	}
	
	
	public List<CustomerStatement>   readFileXML(String fileName)
	{	
		List<CustomerStatement> statements = new ArrayList<CustomerStatement>();
		Path statementsfilePath = Paths.get(path+fileName);		
		try {
			statements = statementRepo.initializeDataFile(statementsfilePath);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return statements;
	}
	public List<String> validateData(List<CustomerStatement> statements) {
		List<String> failedRecords = new ArrayList<>() ; 
		HashMap<Integer, CustomerStatement> hm = new HashMap<Integer, CustomerStatement>();			
		DecimalFormat decimalFormatter = new DecimalFormat("0.00");		
		for(CustomerStatement st : statements)
		{
			String details = "";
			double value;
			if(hm.containsKey(st.getReference())){	
				 details = st.getReference() +"  "+st.getDescription();
				 failedRecords.add(details);
			}
			else {
					value =Double.parseDouble(decimalFormatter.format((st.getStart_Balance() + st.getMutation())));	
					if(st.getEnd_Balance() != value){
						 details = st.getReference() +"  "+st.getDescription();
						 failedRecords.add(details);
					}				
					else {
					hm.put(st.getReference(), st); 
				}
			}		
			
		}		
		return failedRecords;
			
	}
	
}
