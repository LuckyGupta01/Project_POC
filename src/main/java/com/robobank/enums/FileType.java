package com.robobank.enums;

public enum FileType {
	CSV("csv"),
	XML("xml");
	private final String label;	
	
	FileType(String label)
	{
		this.label=label;
	}
	
	public String getLabel() {
		return label;
	}
}
