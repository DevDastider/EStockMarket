package com.cts.stockmarket.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;

@Entity
public class Stock {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int transactionId;
	private double stockPrice;
	private String timeStamp;
	private int cs_fk;
	
	public int getCs_fk() {
		return cs_fk;
	}
	public void setCs_fk(int cs_fk) {
		this.cs_fk = cs_fk;
	}
	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}
	public int getTransactionId() {
		return transactionId;
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
		if(this.timeStamp==null) {
			SimpleDateFormat timeformat= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			timeformat.setTimeZone(TimeZone.getTimeZone("Asia/Kolkata"));
			this.timeStamp = timeformat.format(new Date(System.currentTimeMillis()));
		}
	}
	public Stock() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Stock(int transactionId, double stockPrice, String timeStamp, int cs_fk) {
		super();
		this.transactionId = transactionId;
		this.stockPrice = stockPrice;
		this.timeStamp = timeStamp;
		this.cs_fk= cs_fk;
	}
	
}