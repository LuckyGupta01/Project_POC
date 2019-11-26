package com.robobank.service;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.constraints.Size;

import org.apache.tomcat.util.http.fileupload.InvalidFileNameException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.robobank.domain.CustomerStatement;
import com.robobank.domain.FileDetails;
import com.robobank.exceptions.CustomerStatementException;
import com.robobank.exceptions.InvalidFileFormatException;
import com.robobank.repository.StatementRepository;

@Service
public class CustomerStatementServiceImpl implements CustomerStatementService {

	
	@Autowired
	StatementRepository statementRepo = new StatementRepository();	
	
	@Value("${dir.path}")
	String path;
	
	@Override
	public List<CustomerStatement>  readFile(String fileName) {
		
		String x[] =fileName.split("\\.");		
		String filetype= x[1].toString();
		List<CustomerStatement> records = new ArrayList<>();
		
		if(filetype.equalsIgnoreCase("csv"))
		{
			records =readFileCSV(fileName);			
		}
		else if (filetype.equalsIgnoreCase("xml"))
		{
			records = readFileXML(fileName);
			
		}
		else
		{
			throw new InvalidFileFormatException("Invalid File Format");
		}
		
		return records;
	}
	
	private List<CustomerStatement>  readFileCSV(String fileName)
	{
		List<CustomerStatement> statements = new ArrayList<CustomerStatement>();
		Path statementsfilePath = Paths.get(path+fileName);
		statements = statementRepo.initializeDataFromFile(statementsfilePath);
		return statements;
	}
	
	
	private List<CustomerStatement>   readFileXML(String fileName)
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

