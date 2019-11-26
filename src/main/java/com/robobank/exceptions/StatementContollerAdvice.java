package com.robobank.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class StatementContollerAdvice extends ResponseEntityExceptionHandler  {

	@ExceptionHandler(InvalidDataException.class)
	ResponseEntity<Object> handleInvalidData(InvalidDataException exception,WebRequest request)
	{
		HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.APPLICATION_JSON);
		return handleExceptionInternal(exception, "Invalid Data", header, HttpStatus.BAD_REQUEST, request);
	}
	
	@ExceptionHandler(InvalidFileFormatException.class)
	ResponseEntity<Object> handleInvalidFileFormat(InvalidFileFormatException exception,WebRequest request)
	{
		HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.APPLICATION_JSON);
		return handleExceptionInternal(exception, "Invalid File Format", header, HttpStatus.BAD_REQUEST, request);
	}
	
	@ExceptionHandler(CustomerStatementException.class)
	ResponseEntity<Object> handleStatementData(CustomerStatementException exception,WebRequest request)
	{
		HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.APPLICATION_JSON);
		return handleExceptionInternal(exception, "Invalid Statements", header, HttpStatus.BAD_REQUEST, request);
	}
	}
