package com.cts.stockmarket.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.stockmarket.model.Company;
import com.cts.stockmarket.service.ICompanyService;


@RestController
@RequestMapping("/api/v1.0/market")
public class CompanyController {
	
	@Autowired
	private ICompanyService companyService;

	@GetMapping("/company/getall")
	public ResponseEntity<?> getAllCompanysDetails() {
		List<Company> companyList = companyService.getAllCompanyDetails();
		if (companyList != null) {
			return new ResponseEntity<List<Company>>(companyList, HttpStatus.OK);
		}

		return new ResponseEntity<String>("Company List is empty!", HttpStatus.NO_CONTENT);
	}

	@GetMapping("/company/info/{sId}")
	public ResponseEntity<?> getCompanyDetailsById(@PathVariable("sId") int companyCode) {
		Company company = companyService.getCompanyDetailsById(companyCode);

		if (company != null) {
			return new ResponseEntity<Company>(company, HttpStatus.OK);
		}

		return new ResponseEntity<String>("Company List is empty!", HttpStatus.NO_CONTENT);
	}

	@PostMapping(value = "/company/register", consumes = "application/json; charset= utf-8")
	public ResponseEntity<?> addCompany(@RequestBody Company company) {

		// Checking whether company turnover is greater than 10Cr.
		if (company.getCompanyTurnover() > 100000000) {
			if (companyService.addCompany(company) != null) {
				return new ResponseEntity<Company>(company, HttpStatus.CREATED);
			} else
				return new ResponseEntity<String>("Sorry data is not inserted!", HttpStatus.CONFLICT);
		} else
			return new ResponseEntity<String>("Sorry company turnover is not sufficient!", HttpStatus.NOT_ACCEPTABLE);
	}

	
	 @PostMapping(value="/stock/add/{sId}") 
	 public ResponseEntity<?> addCompanyPrice(@PathVariable("sId") int companyCode, @RequestBody Company company){
		 
		 Company existingCompany= companyService.getCompanyDetailsById(companyCode);
		  
		  if (existingCompany!=null) {
			  existingCompany.setStockPrice(company.getStockPrice());
			  
			  if(companyService.updateStockPrice(existingCompany))
				  return new ResponseEntity<Company>(existingCompany, HttpStatus.OK);
			  
		  }
		  
		  return new ResponseEntity<String>("Couldn't add company price", HttpStatus.INTERNAL_SERVER_ERROR);
	 }
	 

	@DeleteMapping("/company/delete/{sId}")
	public ResponseEntity<?> deleteCompany(@PathVariable("sId") int companyCode) {
		if (companyService.deleteCompany(companyCode))
			return new ResponseEntity<String>("Record Deleted!", HttpStatus.NO_CONTENT);

		else
			return new ResponseEntity<String>("Cannot delete due to internal error", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	  @PutMapping("/stock/put/{sId}") 
	  public ResponseEntity<?> updateStockPrice(@PathVariable("sId") int companyCode, @RequestBody Company company){
		  
		  Company existingCompany= companyService.getCompanyDetailsById(companyCode);
		  
		  if (existingCompany!=null) {
			  existingCompany.setStockPrice(company.getStockPrice());
			  
			  if(companyService.updateStockPrice(existingCompany))
				  return new ResponseEntity<Company>(existingCompany,HttpStatus.CREATED);
			  
		  }
		  
		  return new ResponseEntity<String>("Update unsuccessfull",HttpStatus.INTERNAL_SERVER_ERROR);
	  }


}
