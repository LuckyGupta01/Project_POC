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
import com.robobank.utility.Utility;

@Service
public class CustomerStatementServiceImpl implements CustomerStatementService {

	
	@Autowired
	Utility utility;
	
	@Override
	public List<CustomerStatement>  readFile(String fileName) {
		
		String x[] =fileName.split("\\.");		
		String filetype= x[1].toString();
		List<CustomerStatement> records = new ArrayList<>();
		
		if(filetype.equalsIgnoreCase("csv"))
		{
			records =utility.readFileCSV(fileName);			
		}
		else if (filetype.equalsIgnoreCase("xml"))
		{
			records = utility.readFileXML(fileName);
			
		}
		else
		{
			throw new InvalidFileFormatException("Invalid File Format");
		}
		
		return records;
	}

	@Override
	public List<String> validateData(List<CustomerStatement> records) {
		
		return utility.validateData(records);
		
	}
	
	
}

