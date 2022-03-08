package com.cts.stockmarket.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.stockmarket.model.Company;
import com.cts.stockmarket.model.Stock;
import com.cts.stockmarket.repository.StockRepository;

@Service
public class StockServiceImpl implements IStockService{
	
	@Autowired
	private StockRepository stockRepository;

	@Override
	public boolean deleteStock(int companyCode) {
		stockRepository.deleteStockRecords(companyCode);
		return true;
	}

	@Override
	public boolean addStock(Company company) {
		Stock stock= new Stock();
		stock.setStockPrice(company.getStockPrice());
		stock.setCs_fk(company.getCompanyCode());
		stockRepository.saveAndFlush(stock);
		return true;
	}
	
	@Override
	public List<Stock> getAllStockByCompanyCode(int companyCode) {
		List<Stock> stockList = stockRepository.getStockRecords(companyCode);
		return stockList;
	}

}