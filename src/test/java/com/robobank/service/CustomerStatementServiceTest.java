package com.robobank.service;

import static org.hamcrest.CoreMatchers.any;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.matchers.Any;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import com.robobank.domain.CustomerStatement;
import com.robobank.exceptions.InvalidFileFormatException;
import com.robobank.utility.Utility;

import junit.framework.Assert;

@RunWith(MockitoJUnitRunner.class)
public class CustomerStatementServiceTest {
	String filename ;
	@Mock
	Utility utility;
/*@Autowired
CustomerStatementServiceImpl cust;*/

@Mock
CustomerStatementServiceImpl cust = new CustomerStatementServiceImpl();


@Before
public void setup()
{
	 filename ="recored.csv";
	 System.out.println("Hi"+filename);
	//cust = Mockito.mock(CustomerStatementServiceImpl.class);
	 
	 
}
	

@Test
public void readFileFromCSVTest()
{
	
	
	
	when(utility.readFileCSV(filename)).thenReturn((List<CustomerStatement>) any(CustomerStatement.class));  
	
	List<CustomerStatement> list = cust.readFile(filename);
	assertNotNull(list);
	
	
}

/*

@Test(expected = InvalidFileFormatException.class)
public void readFileFromCSVTest()
{
	
	
}



@Test
public void readFileFromCSVTest()
{
	
	
}



@Test
public void readFileFromCSVTest()
{
	
	
}*/

private List<CustomerStatement> getList()

{
	
	return Arrays.asList(new CustomerStatement(111,null,null,10.2,-2,10.8),new CustomerStatement(112,null,null,11.2,-2,10.8));
	
}




	
}
