package com.robobank.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.robobank.domain.CustomerStatement;
import com.robobank.enums.FileType;
import com.robobank.exceptions.InvalidFileFormatException;
import com.robobank.repository.StatementRepository;

@Service
public class CustomerStatementServiceImpl implements CustomerStatementService {

	private static final Logger log = LoggerFactory.getLogger(CustomerStatementServiceImpl.class);

	@Autowired
	StatementRepository statementRepository;

	@Override
	public List<CustomerStatement> readFile(String fileName) {
		log.info("In readFile() CustomerStatementServiceImpl ");
		String x[] = fileName.split("\\.");
		String filetype = x[1].toString();
		List<CustomerStatement> records = new ArrayList<>();

		if (filetype.equalsIgnoreCase(FileType.CSV.getLabel())) {
			records = statementRepository.readFileCSV(fileName);
		} else if (filetype.equalsIgnoreCase(FileType.XML.getLabel())) {
			records = statementRepository.readFileXML(fileName);

		} else {
			throw new InvalidFileFormatException("Invalid File Format");
		}

		return records;
	}

	@Override
	public List<String> validateData(List<CustomerStatement> records) {

		return statementRepository.validateData(records);

	}

}
