package com.robobank.repository;

import com.robobank.domain.CustomerStatement;

public class Utility {
	

	public static CustomerStatement parseStatements(String statementDetail) {
			
			String row[]=statementDetail.trim().split(",");
			System.out.print("***************"+row[0]);
			
			CustomerStatement customerStatement = new CustomerStatement(Integer.parseInt(row[0].trim()), row[1].trim() , row[2].trim() , Double.parseDouble(row[3].trim()) , Double.parseDouble(row[4].trim()) , Double.parseDouble(row[5].trim()) );	
			
			return customerStatement;
		}	

}
