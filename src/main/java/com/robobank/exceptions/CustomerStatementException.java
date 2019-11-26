package com.robobank.exceptions;


public class CustomerStatementException extends RuntimeException {
	
	public CustomerStatementException()
	{
		
	}
	CustomerStatementException(String message)
	{
		super(message);
	}
	
}
