package com.robobank.domain;

public class CustomerStatement {
	
	private int reference;
	private String account_Number;
	private String description;
	private double start_Balance;
	private double mutation;
	private double end_Balance;
	
	public CustomerStatement(){		
	}	
	public CustomerStatement(int reference, String account_Number, String description, double start_Balance,
			double mutation, double end_Balance) {		
		this.reference = reference;
		this.account_Number = account_Number;
		this.description = description;
		this.start_Balance = start_Balance;
		this.mutation = mutation;
		this.end_Balance = end_Balance;
	}
	
	public int getReference() {
		return reference;
	}
	public void setReference(int reference) {
		this.reference = reference;
	}
	public String getAccount_Number() {
		return account_Number;
	}
	public void setAccount_Number(String account_Number) {
		this.account_Number = account_Number;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getStart_Balance() {
		return start_Balance;
	}
	public void setStart_Balance(double start_Balance) {
		this.start_Balance = start_Balance;
	}
	public double getMutation() {
		return mutation;
	}
	public void setMutation(double mutation) {
		this.mutation = mutation;
	}
	public double getEnd_Balance() {
		return end_Balance;
	}
	public void setEnd_Balance(double end_Balance) {
		this.end_Balance = end_Balance;
	}
	@Override
	public String toString() {
		return "CustomerStatement [reference=" + reference + ", account_Number=" + account_Number + ", description="
				+ description + ", start_Balance=" + start_Balance + ", mutation=" + mutation + ", end_Balance="
				+ end_Balance + "]";
	}	

}
