package com.cts.stockmarket.model;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;

@Entity
public class Company {
	
	@Id
	private int companyCode;
	private String companyName;
	private String companyCeo;
	private double companyTurnover;
	private String companyWebsite;
	private String stockExchange;
	private double stockPrice;
	private String timeStamp;
	
	public int getCompanyCode() {
		return companyCode;
	}
	public void setCompanyCode(int companyCode) {
		this.companyCode = companyCode;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getCompanyCeo() {
		return companyCeo;
	}
	public void setCompanyCeo(String companyCeo) {
		this.companyCeo = companyCeo;
	}
	public double getCompanyTurnover() {
		return companyTurnover;
	}
	public void setCompanyTurnover(double companyTurnover) {
		this.companyTurnover = companyTurnover;
	}
	public String getCompanyWebsite() {
		return companyWebsite;
	}
	public void setCompanyWebsite(String companyWebsite) {
		this.companyWebsite = companyWebsite;
	}
	public String getStockExchange() {
		return stockExchange;
	}
	public void setStockExchange(String stockExchange) {
		this.stockExchange = stockExchange;
	}
	public double getStockPrice() {
		return stockPrice;
	}
	public void setStockPrice(double stockPrice) {
		this.stockPrice = stockPrice;
	}
	public String getTimeStamp() {
		return timeStamp;
	}
	@PrePersist
	public void setTimeStamp() {
		System.out.println("Triggered");
		if(this.timeStamp==null) {
			SimpleDateFormat timeformat= new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
			this.timeStamp = timeformat.format(new Date(System.currentTimeMillis()));
		}
		System.out.println("timestamp=" + this.timeStamp);
	}
	
	public Company(int companyCode, String companyName, String companyCeo, double companyTurnover,
			String companyWebsite, String stockExchange, double stockPrice) {
		super();
		this.companyCode = companyCode;
		this.companyName = companyName;
		this.companyCeo = companyCeo;
		this.companyTurnover = companyTurnover;
		this.companyWebsite = companyWebsite;
		this.stockExchange = stockExchange;
		this.stockPrice = stockPrice;
	}
	public Company() {
		super();
		// TODO Auto-generated constructor stub
	}

}
