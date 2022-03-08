package com.cts.stockmarket.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import com.cts.stockmarket.model.Company;
import com.cts.stockmarket.repository.CompanyRepository;

@DataJpaTest
@AutoConfigureMockMvc
public class CompanyRepositoryTest {

	@Autowired
	CompanyRepository companyRepository;
	
	private Company company= new Company();
	
	@BeforeEach
	public void init() {
		company.setCompanyCode(101);
		company.setCompanyName("Company 1");
		company.setCompanyCeo("Mr. A");
		company.setCompanyTurnover(1893673912);
		company.setCompanyWebsite("www.company.com");
		company.setStockExchange("NSE");
		company.setStockPrice(500);
		company.setTimeStamp();
	}
	
//	@Test
//	public void saveCompanySuccess() throws Exception{
//		
//		Company comp= companyRepository.save(company);
//		
//		assertEquals(company.getCompanyName(), comp.getCompanyName());
//		assertEquals(company.getCompanyCeo(), comp.getCompanyCeo());
//		assertEquals(company.getCompanyTurnover(), comp.getCompanyTurnover());
//		assertEquals(company.getCompanyWebsite(), comp.getCompanyWebsite());
//		assertEquals(company.getStockExchange(), comp.getStockExchange());
//		assertEquals(company.getStockPrice(), comp.getStockPrice());
//	}
}
