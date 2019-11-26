package com.robobank.controller;


import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CustomerStatementControllerTest {



@MockBean
RestTemplate restTemplate;

String fileName;

@Before
public void settup()
{
	fileName = "records.csv";
}
	

@Test
public void customerStatementTest() {
	
	ResponseEntity responseUPdated = restTemplate.getForEntity("/api/v1/robobank/inputfile/records.csv",ResponseEntity.class); 
	assertThat(responseUPdated).isEqualTo(HttpStatus.OK);

	
}
}