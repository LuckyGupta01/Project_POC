package com.robobank;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.robobank.domain.CustomerStatement;
import com.robobank.repository.StatementRepository;
import com.robobank.repository.Utility;

public class testClass {
	
	private  List<CustomerStatement> statements;

	public static void main(String args[]) throws IOException
	{
		Path statementsfilePath = Paths.get("src/data/records.csv");
		
		
		/*testClass cl = new testClass();
		cl.initializeDataFromFile(statementsfilePath);
		*/
		//Path statementsfilePath = Paths.get("src/data/records.csv");
		BufferedReader br = new BufferedReader(new FileReader(statementsfilePath.toString()));
		
				
		String line =null;
		while((line=br.readLine())!=null)
			
		{
			String row[]=line.trim().split(",");
			for(String s : row)				
				System.out.println("***"+Integer.parseInt(row[0].trim())+"--"+ row[1].trim() +"--"+ row[2].trim() +"--"+ Double.parseDouble(row[3].trim()) +"--"+Double.parseDouble(row[4].trim()) +"--"+ Double.parseDouble(row[5].trim()));
			
		}
		
	
		}
	
	
public  void initializeDataFromFile(Path statementsfilePath)
	
	{
	try(Stream<String> statementsData = Files.lines(statementsfilePath))
	{	
		System.out.println("TRY----"+statementsData==null);
		statementsData.forEach(System.out::println);
		
		/*statements = statementsData.map(Utility::parseStatements)				
				.collect(Collectors.toCollection(ArrayList::new));	*/	
	}
	catch (IOException e) 
	{
	e.printStackTrace();
	}
	

	for(CustomerStatement s : statements)
	{
		System.out.println("____________"+s.getAccount_Number());
	}
	
	
	
	} // method closure

}
