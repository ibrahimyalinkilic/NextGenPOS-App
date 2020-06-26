package com.company.domain;

public class Customer {
	private boolean isSenior;
	private String id;
	
	public Customer(String id,boolean isSenior) {
		this.isSenior = isSenior;
		this.id = id;
	}
	
	public boolean getisSenior() {
		return isSenior;
	}

	public void setSenior(boolean isSenior) {
		this.isSenior = isSenior;
	}
	
}
