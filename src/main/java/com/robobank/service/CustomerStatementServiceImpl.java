package com.robobank.service;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.robobank.domain.CustomerStatement;
import com.robobank.domain.FileDetails;
import com.robobank.exceptions.StatementsExceptions;
import com.robobank.repository.StatementRepository;

@Service
public class CustomerStatementServiceImpl implements CustomerStatementService {

	
	@Autowired
	StatementRepository statementRepo = new StatementRepository();	
	
	
	@Override
	public List<CustomerStatement>  readFile(String fileName) {
		
		String x[] =fileName.split("\\.");		
		String filetype= x[1].toString();
		List<CustomerStatement> records = new ArrayList<>();
		
		if(filetype.equalsIgnoreCase("csv"))
		{
			records =readFileCSV(fileName);
			//validateData();
			//return failedRecords;
		}
		else if (filetype.equalsIgnoreCase("xml"))
		{
			records =readFileXML(fileName);
			
		}
		else
		{
			throw new StatementsExceptions("Invalid File Type");
		}
		return records;
	}
	
	private List<CustomerStatement>  readFileCSV(String fileName)
	{
		List<CustomerStatement> statements = new ArrayList<CustomerStatement>();
		Path statementsfilePath = Paths.get("src/data/records.csv");
		statements = statementRepo.initializeDataFromFile(statementsfilePath);
		return statements;
	}
	
	
	private List<CustomerStatement>   readFileXML(String fileName)
	{	
		List<CustomerStatement> statements = new ArrayList<CustomerStatement>();
		Path statementsfilePath = Paths.get("src/data/records.xml");		
		try {
			statements = statementRepo.initializeDataFile(statementsfilePath);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return statements;
	}
	@Override
	public List<String> validateData(List<CustomerStatement> statements) {
		List<String> failedRecords = new ArrayList<>() ; 
		HashMap<Integer, CustomerStatement> hm = new HashMap<Integer, CustomerStatement>();			
		DecimalFormat decimalFormatter = new DecimalFormat("0.00");		
		for(CustomerStatement st : statements)
		{
			String details = "";
			double value;
			if(hm.containsKey(st.getReference())){	
				//System.out.println(""+st.getReference());
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

