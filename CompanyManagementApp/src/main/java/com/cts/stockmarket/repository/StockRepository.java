package com.cts.stockmarket.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cts.stockmarket.model.Stock;

@Repository
@Transactional
public interface StockRepository extends JpaRepository<Stock, Integer>{

	@Modifying
	@Query(value = "delete from Stock where cs_fk= :companyCode")
	public void deleteStockRecords(int companyCode);
	
	@Query(value="select s from Stock s where s.cs_fk= :companyCode ")
	public List<Stock> getStockRecords(int companyCode);
}