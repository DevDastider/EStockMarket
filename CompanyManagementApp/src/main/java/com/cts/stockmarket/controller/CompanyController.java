package com.cts.stockmarket.controller;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cts.stockmarket.exceptions.CompanyIDAlreadyExistsException;
import com.cts.stockmarket.model.Company;
import com.cts.stockmarket.response.ResponseHandler;
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
			CacheControl cacheControl=CacheControl.maxAge(5,TimeUnit.MINUTES);
			
			return ResponseEntity.ok()
					.cacheControl(cacheControl)
					.body(ResponseHandler.generateResponse("Succesfully retrieved the data", HttpStatus.OK, companyList));
		}

		return ResponseHandler.generateResponse("List Empty", HttpStatus.NO_CONTENT, "Company List is empty!");
	}

	@GetMapping("/company/info/{sId}")
	public ResponseEntity<?> getCompanyDetailsById(@PathVariable("sId") int companyCode) {
		Company company = companyService.getCompanyDetailsById(companyCode);

		if (company != null) {
			return ResponseHandler.generateResponse("Succesfully retrieved the data", HttpStatus.OK, company);
		}

		return ResponseHandler.generateResponse("List Empty", HttpStatus.NO_CONTENT, "Company List is empty!");
	}

	@PostMapping(value = "/company/register", consumes = "application/json; charset= utf-8")
	public ResponseEntity<?> addCompany(@RequestBody Company company) throws CompanyIDAlreadyExistsException {

		// Checking whether company turnover is greater than 10Cr.
		if (company.getCompanyTurnover() > 100000000) {
			if (companyService.addCompany(company) != null) {
				return ResponseHandler.generateResponse("Succesfully data added", HttpStatus.CREATED, company);	//new ResponseEntity<Company>(company, HttpStatus.CREATED);
			} else
				return ResponseHandler.generateResponse("Sorry data is not inserted!", HttpStatus.CONFLICT, "Company code exists");	//new ResponseEntity<String>("Sorry data is not inserted!", HttpStatus.CONFLICT);
		} else
			return ResponseHandler.generateResponse("Sorry company turnover is not sufficient!", HttpStatus.NOT_ACCEPTABLE, "Minimum turnover of Rs.100000000 required");//new ResponseEntity<String>("Sorry company turnover is not sufficient!", HttpStatus.NOT_ACCEPTABLE);
	}

	
	 @PostMapping(value="/stock/add/{sId}") 
	 public ResponseEntity<?> addCompanyPrice(@PathVariable("sId") int companyCode, @RequestBody Company company){
		 
		 Company existingCompany= companyService.getCompanyDetailsById(companyCode);
		  
		  if (existingCompany!=null) {
			  existingCompany.setStockPrice(company.getStockPrice());
			  
			  if(companyService.updateStockPrice(existingCompany))
				  return ResponseHandler.generateResponse("Price added", HttpStatus.OK, company);
			  
		  }
		  
		  return ResponseHandler.generateResponse("Error", HttpStatus.INTERNAL_SERVER_ERROR, "Internal Error");
	 }
	 

	@DeleteMapping("/company/delete/{sId}")
	public ResponseEntity<?> deleteCompany(@PathVariable("sId") int companyCode) {
		if (companyService.deleteCompany(companyCode))
			return ResponseHandler.generateResponse("Deleted", HttpStatus.NO_CONTENT, "Company record deleted");

		else
			return ResponseHandler.generateResponse("Cannot be deleted", HttpStatus.INTERNAL_SERVER_ERROR, "Cannot delete due to internal error");
	}

	  @PutMapping(value="/stock/put/{sId}") 
	  public ResponseEntity<?> updateStockPrice(@PathVariable("sId") int companyCode, @RequestParam("price") double stockPrice){
		  
		  Company existingCompany= companyService.getCompanyDetailsById(companyCode);
		  
		  if (existingCompany!=null) {
			  existingCompany.setStockPrice(stockPrice);
			  
			  if(companyService.updateStockPrice(existingCompany))
				  return ResponseHandler.generateResponse("Price updated", HttpStatus.CREATED, existingCompany);//new ResponseEntity<Company>(existingCompany,HttpStatus.CREATED);	  
		  }
		  
		  return ResponseHandler.generateResponse("Price updation not possible", HttpStatus.INTERNAL_SERVER_ERROR, "Updation not possible");
	  }


}
