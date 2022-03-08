package com.cts.stockmarket.service;

import java.util.List;

import com.cts.stockmarket.model.Company;
import com.cts.stockmarket.model.Stock;

public interface IStockService {
	
	public boolean deleteStock(int companyCode);	
	
	public boolean addStock(Company company);

	List<Stock> getAllStockByCompanyCode(int companyCode);

}