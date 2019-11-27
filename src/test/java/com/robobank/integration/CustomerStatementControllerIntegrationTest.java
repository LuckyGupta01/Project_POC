package com.robobank.integration;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.robobank.domain.ReportData;
import com.robobank.exceptions.InvalidFileFormatException;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CustomerStatementControllerIntegrationTest {

	@LocalServerPort
	int port;

	private String fileName;
	private String url;
	private TestRestTemplate restTemplate;
	HttpHeaders headers;

	@Before
	public void settup() {

		restTemplate = new TestRestTemplate();
	}

	@Test
	public void customerStatementTest() {

		fileName = "records.csv";
		url ="http://localhost:" +port+"/api/v1/robobank/inputfile/"+fileName;
		headers = new HttpHeaders();
		ResponseEntity<ReportData> responseUPdated = restTemplate.getForEntity(url, ReportData.class);
		assertThat(responseUPdated.getStatusCode()).isEqualTo(HttpStatus.OK);

	}

	@Test
	public void customerStatementInvalidFileFormat() {

		url ="http://localhost:" +port+"/api/v1/robobank/inputfile/"+fileName;
		headers = new HttpHeaders();
		try {
			ResponseEntity<ReportData> responseUPdated = restTemplate.getForEntity(url, ReportData.class);
		} catch (InvalidFileFormatException exception) {
			Assert.assertEquals(exception.getMessage(), "Invalid File Format");
		}
	}
}