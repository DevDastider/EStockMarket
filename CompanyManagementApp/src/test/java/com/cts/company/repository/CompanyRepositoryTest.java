package com.cts.company.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.context.ContextConfiguration;

import com.cts.stockmarket.CompanyManagementAppApplication;
import com.cts.stockmarket.model.Company;
import com.cts.stockmarket.repository.CompanyRepository;

@DataJpaTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = CompanyRepositoryTest.class)
public class CompanyRepositoryTest {

	@Autowired
	private CompanyRepository companyRepository;
	
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
	
	@Test
	public void saveCompanySuccess() throws Exception{
		companyRepository.save(company);
		
		Company comp= companyRepository.findById(company.getCompanyCode()).get();
		
		assertEquals(company.getCompanyName(), comp.getCompanyName());
		assertEquals(company.getCompanyCeo(), comp.getCompanyCeo());
		assertEquals(company.getCompanyTurnover(), comp.getCompanyTurnover());
		assertEquals(company.getCompanyWebsite(), comp.getCompanyWebsite());
		assertEquals(company.getStockExchange(), comp.getStockExchange());
		assertEquals(company.getStockPrice(), comp.getStockPrice());
	}
}
