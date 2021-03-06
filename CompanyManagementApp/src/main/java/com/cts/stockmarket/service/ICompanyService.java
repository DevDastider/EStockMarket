package com.cts.stockmarket.service;

import java.util.List;

import com.cts.stockmarket.exceptions.CompanyIDAlreadyExistsException;
import com.cts.stockmarket.model.Company;

public interface ICompanyService {
	
	public Company addCompany(Company company) throws CompanyIDAlreadyExistsException;		//Registering new company
	
	public Company getCompanyDetailsById(int companyCode);									//Fetch a particular company details
	
	public List<Company> getAllCompanyDetails();											//Fetches all company details
	
	public boolean deleteCompany(int companyCode);											//Deletes a company
	
	public boolean updateStockPrice(Company company);										//Updates company price of particular company
}
