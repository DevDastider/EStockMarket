package com.cts.company.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.cts.stockmarket.model.Company;

public class CompanyTest {
	
	@Test
	public void testName() {
		Company company= Mockito.mock(Company.class);
		String compName= "Company 1";
		
		when(company.getCompanyName()).thenReturn(null);
		System.out.println("Mocked object result before assigning name: "+ company.getCompanyName());
		assertNull(company.getCompanyName());
		
		
		when(company.getCompanyName()).thenReturn(compName);
		System.out.println("Mocked object result after assigning name: "+ company.getCompanyName());
		assertEquals(compName, company.getCompanyName());
	}

	@Test
	public void testCode() {
		Company company= Mockito.mock(Company.class);
		int compCode= 100;		//mock data
		
		when(company.getCompanyCode()).thenReturn(0);
		System.out.println("Mocked object result before assigning code: "+ company.getCompanyCode());
		assertEquals(0, company.getCompanyCode());
		
		when(company.getCompanyCode()).thenReturn(compCode);
		System.out.println("Mocked object result after assigning code: "+ company.getCompanyCode());
		assertEquals(compCode, company.getCompanyCode());
	}
}
