package com.cts.stockmarket.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;

@Entity
public class Company {
	
	@Id
	@Column(name = "companyCode")
	private Integer companyCode;
	private String companyName;
	private String companyCeo;
	private double companyTurnover;
	private String companyWebsite;
	private String stockExchange;
	private double stockPrice;
	private String timeStamp;
	
	@OneToMany(targetEntity = Stock.class)//, cascade = CascadeType.REMOVE)
//	@JoinColumn(name="cs_fk", referencedColumnName = "companyCode")
	private List<Stock> stockList;
	
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
			SimpleDateFormat timeformat= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			timeformat.setTimeZone(TimeZone.getTimeZone("Asia/Kolkata"));
			this.timeStamp = timeformat.format(new Date(System.currentTimeMillis()));
		}
		System.out.println("timestamp=" + this.timeStamp);
	}
	public List<Stock> getStockList() {
		return stockList;
	}
	public void setStockList(List<Stock> stockList) {
		this.stockList = stockList;
	}	
	
	public Company() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Company(int companyCode, String companyName, String companyCeo, double companyTurnover,
			String companyWebsite, String stockExchange, double stockPrice, String timeStamp, List<Stock> stockList) {
		super();
		this.companyCode = companyCode;
		this.companyName = companyName;
		this.companyCeo = companyCeo;
		this.companyTurnover = companyTurnover;
		this.companyWebsite = companyWebsite;
		this.stockExchange = stockExchange;
		this.stockPrice = stockPrice;
		this.timeStamp = timeStamp;
		this.stockList = stockList;
	}

}