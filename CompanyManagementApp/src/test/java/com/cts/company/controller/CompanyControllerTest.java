package com.cts.company.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

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
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.cts.stockmarket.controller.CompanyController;
import com.cts.stockmarket.model.Company;
import com.cts.stockmarket.service.CompanyServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = CompanyControllerTest.class)
public class CompanyControllerTest {
	
	@Mock
	private CompanyServiceImpl companyService;
	
	@InjectMocks
	private CompanyController companyController;
	
	@Autowired
	private MockMvc mockMvc;
	
	private List<Company> companyList= new ArrayList<>();
	
	private Company company= new Company();
	
	@BeforeEach
	public void init() {
		MockitoAnnotations.initMocks(this);
		mockMvc= MockMvcBuilders.standaloneSetup(companyController).build();
		
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
	public void getAllCompanySuccess() throws Exception{
		companyList.add(company);
		when(companyService.getAllCompanyDetails()).thenReturn(companyList);
		
		List<Company> checkList= companyService.getAllCompanyDetails();
		assertEquals(companyList, checkList);
		
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1.0/market/company/getall").contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());
		
	}
	
	@Test
	public void getAllCompanyFailure() throws Exception{
		companyList.clear();
		when(companyService.getAllCompanyDetails()).thenReturn(companyList);
		
		List<Company> checkList= companyService.getAllCompanyDetails();
		assertEquals(companyList, checkList);
		
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1.0/market/company/getall").contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());
		
	}
	
	@Test
	public void registerCompanySuccess() throws Exception{
		
		when(companyService.addCompany(any())).thenReturn(company);
		Company savedCompany= companyService.addCompany(company);
		
		assertEquals(company, savedCompany);
		
		mockMvc.perform(MockMvcRequestBuilders.post("/api/v1.0/market/company/register").contentType(MediaType.APPLICATION_JSON)
			.content(new ObjectMapper().writeValueAsString(company)))
			.andExpect(MockMvcResultMatchers.status().isCreated());
	}
	
	@Test
	public void registerCompanyFailure() throws Exception{
		
		when(companyService.addCompany(any())).thenReturn(null);
		
		Company savedCompany= companyService.addCompany(company);
		assertNull(savedCompany);
		
		mockMvc.perform(MockMvcRequestBuilders.post("/api/v1.0/market/company/register").contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(company)))
				.andExpect(MockMvcResultMatchers.status().isConflict());
	}
}
