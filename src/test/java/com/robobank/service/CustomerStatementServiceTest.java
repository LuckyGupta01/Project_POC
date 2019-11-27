package com.robobank.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.robobank.domain.CustomerStatement;
import com.robobank.exceptions.InvalidFileFormatException;
import com.robobank.repository.StatementRepository;
import com.robobank.utils.StatmentUtils;

@RunWith(MockitoJUnitRunner.class)
public class CustomerStatementServiceTest {

	@InjectMocks
	CustomerStatementServiceImpl customerStatementService;

	@Mock
	StatmentUtils statementUtils;
	@Mock
	StatementRepository statementRepository;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void readCSVFileTestWhenListIsNotNull() {
		String fileName = "records.csv";
		List<CustomerStatement> list = new ArrayList<CustomerStatement>();
		Mockito.when(statementRepository.readFileCSV(fileName)).thenReturn(list);
		list = customerStatementService.readFile(fileName);
		Assert.assertNotNull(list);

	}

	@Test
	public void readCSVFileTestWithListSize() {
		String fileName = "records.csv";
		List<CustomerStatement> list;
		Mockito.when(statementRepository.readFileCSV(fileName)).thenReturn(getList());
		list = customerStatementService.readFile(fileName);
		Assert.assertEquals(list.size(), 2);

	}

	@Test
	public void readXMLFileTestWhenListIsNotNull() {
		String fileName = "records.xml";
		List<CustomerStatement> list = new ArrayList<CustomerStatement>();
		Mockito.when(statementRepository.readFileXML(fileName)).thenReturn(list);
		list = customerStatementService.readFile(fileName);
		Assert.assertNotNull(list);

	}

	@Test
	public void readXMLFileTestWithListSize() {
		String fileName = "records.xml";
		List<CustomerStatement> list;
		Mockito.when(statementRepository.readFileXML(fileName)).thenReturn(getList());
		list = customerStatementService.readFile(fileName);
		Assert.assertEquals(list.size(), 2);

	}

	@Test
	public void readFileWhenInvalidFileFormatException() {
		String fileName = "records.txt";
		try {
			customerStatementService.readFile(fileName);
		} catch (InvalidFileFormatException exception) {
			Assert.assertEquals(exception.getMessage(), "Invalid File Format");
		}

	}

	@Test
	public void validDataTestForFiles() {
		List<String> list = new ArrayList<String>();
		Mockito.when(statementRepository.validateData(getList())).thenReturn(list);
		customerStatementService.validateData(getList());
		Assert.assertNotNull(customerStatementService.validateData(getList()));

	}

	private List<CustomerStatement> getList() {
		return Arrays.asList(new CustomerStatement(111, "abc","xyz", 10.2, -2, 10.8),
				new CustomerStatement(112, "abc", "mno", 11.2, -2, 10.8));
	}

}
