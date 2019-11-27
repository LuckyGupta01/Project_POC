package com.robobank.domain;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class FileDetails {
	
	
	private List<String> records;
	
	public List<String> getRecords() {
		return records;
	}

	public void setRecords(List<String> records) {
		this.records = records;
	}

	@Override
	public String toString() {
		return "FileDetails [records=" + records + "]";
	}

	
}
