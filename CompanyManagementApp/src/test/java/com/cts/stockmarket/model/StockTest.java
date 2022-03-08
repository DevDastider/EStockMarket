package com.cts.stockmarket.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class StockTest {
	
	@Test
	public void testStockPrice() {
		Stock stock= Mockito.mock(Stock.class);
		double stockPrice= 250.75;
		
		when(stock.getStockPrice()).thenReturn(0.0);
		//System.out.println("Mocked object result before assigning stock price: "+ stock.getStockPrice());
		assertEquals(0.0, stock.getStockPrice());
		
		when(stock.getStockPrice()).thenReturn(stockPrice);
		//System.out.println("Mocked object result after assigning stock price: "+ stock.getStockPrice());
		assertEquals(stockPrice, stock.getStockPrice());
	}
	
	@Test
	public void testCs_fk() {
		Stock stock= Mockito.mock(Stock.class);
		int cs_fk= 200;
		
		when(stock.getCs_fk()).thenReturn(0);
		//System.out.println("Mocked object result before assigning stock price: "+ stock.getStockPrice());
		assertEquals(0, stock.getStockPrice());
		
		when(stock.getCs_fk()).thenReturn(cs_fk);
		//System.out.println("Mocked object result after assigning stock price: "+ stock.getStockPrice());
		assertEquals(cs_fk, stock.getCs_fk());
	}

}