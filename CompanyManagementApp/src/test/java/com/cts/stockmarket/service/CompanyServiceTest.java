package com.cts.stockmarket.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.cts.stockmarket.model.Company;
import com.cts.stockmarket.repository.CompanyRepository;
import com.cts.stockmarket.service.CompanyServiceImpl;

@AutoConfigureMockMvc
@SpringBootTest
public class CompanyServiceTest {
	
	@Mock
	private CompanyRepository compRepository;
	
	@InjectMocks
	private CompanyServiceImpl compService;
	
	@Autowired
	private MockMvc mockMvc;
	
	private List<Company> companyList= new ArrayList<>();
	
	private Company company= new Company();
	
	@BeforeEach
	public void init() {
		MockitoAnnotations.initMocks(this);
		mockMvc= MockMvcBuilders.standaloneSetup(compService).build();
		
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
	public void getAllCompany() throws Exception{
		companyList.add(company);
		when(compRepository.findAll()).thenReturn(companyList);
		
		List<Company> checkList= compService.getAllCompanyDetails();
		assertEquals(companyList, checkList);	
	}
	
	@Test
	public void getAllCompanyFailure() throws Exception{
		when(compRepository.findAll()).thenReturn(null);
		List<Company> checkList= compService.getAllCompanyDetails();
		
		assertNull(checkList);
	}
	
	@Test
	public void registerCompanySuccess() throws Exception{
		
		when(compRepository.saveAndFlush(any())).thenReturn(company);
		
		Company savedCompany= compService.addCompany(company);
		assertEquals(company, savedCompany);
	}
	
	@Test
	public void registerCompanyFailure() throws Exception{
		
		when(compRepository.saveAndFlush(any())).thenReturn(null);
		
		Company savedCompany= compService.addCompany(company);
		assertNull(savedCompany);
	}
}
