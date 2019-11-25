package com.robobank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.robobank.domain.FileDetails;

@SpringBootApplication
public class ProjectRoboBankApplication {

	@Bean
	public FileDetails getFileInfo()
	{
		return new FileDetails();
	}
	
	public static void main(String[] args) {
		SpringApplication.run(ProjectRoboBankApplication.class, args);
	}

}
