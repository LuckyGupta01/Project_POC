package com.robobank.repository;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.robobank.domain.CustomerStatement;
import com.robobank.utils.StatmentUtils;

@Component
public class StatementRepository {

	@Value("${dir.path}")
	String path;

	@Autowired
	StatmentUtils statementUtil;

	private static final Logger loggger = LoggerFactory.getLogger(StatementRepository.class);

	public List<CustomerStatement> readFileCSV(String fileName) {
		loggger.info("In readFileCSV() StatementRepository ");
		List<CustomerStatement> statements = new ArrayList<CustomerStatement>();
		Path statementsfilePath = Paths.get(path + fileName);
		statements = statementUtil.initializeDataFromFile(statementsfilePath);
		return statements;
	}

	public List<CustomerStatement> readFileXML(String fileName) {
		loggger.info("In readFileXML() StatementRepository ");
		List<CustomerStatement> statements = new ArrayList<CustomerStatement>();
		Path statementsfilePath = Paths.get(path + fileName);
		try {
			statements = statementUtil.initializeDataFile(statementsfilePath);
		} catch (Exception e) {

			loggger.error(e.getMessage());
		}
		return statements;
	}

	public List<String> validateData(List<CustomerStatement> statements) {
		List<String> failedRecords = new ArrayList<>();
		HashMap<Integer, CustomerStatement> hm = new HashMap<Integer, CustomerStatement>();
		DecimalFormat decimalFormatter = new DecimalFormat("0.00");
		for (CustomerStatement st : statements) {
			String details = "";
			double value;
			if (hm.containsKey(st.getReference())) {
				details = st.getReference() + "  " + st.getDescription();
				failedRecords.add(details);
			} else {
				value = Double.parseDouble(decimalFormatter.format((st.getStart_Balance() + st.getMutation())));
				if (st.getEnd_Balance() != value) {
					details = st.getReference() + "  " + st.getDescription();
					failedRecords.add(details);
				} else {
					hm.put(st.getReference(), st);
				}
			}

		}
		return failedRecords;

	}

}
