package com.robobank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.robobank.domain.ReportData;

@SpringBootApplication
public class ProjectRoboBankApplication {

	@Bean
	public ReportData getFileInfo()
	{
		return new ReportData();
	}
	
	public static void main(String[] args) {
		SpringApplication.run(ProjectRoboBankApplication.class, args);
	}

}
