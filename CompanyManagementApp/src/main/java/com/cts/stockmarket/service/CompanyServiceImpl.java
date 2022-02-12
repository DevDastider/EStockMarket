package com.cts.stockmarket.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.stockmarket.model.Company;
import com.cts.stockmarket.repository.CompanyRepository;

@Service
public class CompanyServiceImpl implements ICompanyService {
	
	@Autowired
	private CompanyRepository companyRepository;
	
	@Override
	public Company addCompany(Company company) {
		if(company != null) {
			return companyRepository.saveAndFlush(company);
		}
		return null;
	}

	@Override
	public Company getCompanyDetailsById(int companyCode) {
		Optional<Company> company= companyRepository.findById(companyCode);		//getById(companyCode);
		
		if(company.isPresent())
			return company.get();
		
		return null;
	}

	@Override
	public List<Company> getAllCompanyDetails() {
		List<Company> companyList= companyRepository.findAll();
		
		if(companyList!=null && companyList.size()>0)
			return companyList;
		else
			return null;
	}

	@Override
	public boolean deleteCompany(int companyCode) {
		if(companyRepository.getById(companyCode) != null) {
			companyRepository.deleteById(companyCode);
			return true;
		}
		else
			return false;
	}

	@Override
	public boolean updateStockPrice(Company company) {
		Company existingCompany= companyRepository.getById(company.getCompanyCode());
		
		if(existingCompany!=null) {
			existingCompany.setStockPrice(company.getStockPrice());
			companyRepository.saveAndFlush(existingCompany);
			return true;
		}
		else
			return false;
	}

}
