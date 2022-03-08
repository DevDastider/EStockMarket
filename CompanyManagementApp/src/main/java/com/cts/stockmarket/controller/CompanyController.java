package com.cts.stockmarket.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.cts.stockmarket.exceptions.CompanyIDAlreadyExistsException;
import com.cts.stockmarket.model.Company;
import com.cts.stockmarket.model.Stock;
import com.cts.stockmarket.response.ResponseHandler;
import com.cts.stockmarket.service.DataPublisher;
import com.cts.stockmarket.service.ICompanyService;
import com.cts.stockmarket.service.IStockService;

@RestController
@RequestMapping("/api/v1.0/market")
@CrossOrigin("*")
public class CompanyController {
	
	@Autowired
	private ICompanyService companyService;
	
	@Autowired
	private IStockService stockService;
	
//	@Autowired
//	private DiscoveryClient discoveryClient;
	
//	@Autowired
//	private DataPublisher dataPublisher;

	@GetMapping("/company/getall")
	public ResponseEntity<?> getAllCompanysDetails() throws RestClientException, Exception {
		//System.out.println("JwtToken: " + getUserToken());
		
		List<Company> companyList = companyService.getAllCompanyDetails();
		
		if (companyList != null) {
			//CacheControl cacheControl=CacheControl.maxAge(5,TimeUnit.MINUTES);
			
			//return ResponseEntity.ok()
					//.cacheControl(cacheControl)
					//.body(ResponseHandler.generateResponse("Successfully retrieved the data", HttpStatus.OK, companyList));
			
			for(Company c: companyList) {
				List<Stock> stockRecords= stockService.getAllStockByCompanyCode(c.getCompanyCode());
				c.setStockList(stockRecords);
			}
			return (ResponseHandler.generateResponse("Successfully retrieved the data", HttpStatus.OK, companyList));
		}

		return ResponseHandler.generateResponse("List Empty", HttpStatus.NO_CONTENT, "Company List is empty!");
	}

	@GetMapping("/company/info/{sId}")
	public ResponseEntity<?> getCompanyDetailsById(@PathVariable("sId") int companyCode) {
		Company company = companyService.getCompanyDetailsById(companyCode);

		if (company != null) {
			List<Stock> stockRecords= stockService.getAllStockByCompanyCode(company.getCompanyCode());
			company.setStockList(stockRecords);
			
			return ResponseHandler.generateResponse("Succesfully retrieved the data", HttpStatus.OK, company);
		}

		return ResponseHandler.generateResponse("List Empty", HttpStatus.NO_CONTENT, "Company List is empty!");
	}

	@PostMapping(value = "/company/register", consumes = "application/json; charset= utf-8")
	public ResponseEntity<?> addCompany(@RequestBody Company company) throws CompanyIDAlreadyExistsException {

		// Checking whether company turnover is greater than 10Cr.
		if (company.getCompanyTurnover() > 100000000) {
			if (companyService.addCompany(company) != null) {
				return ResponseHandler.generateResponse("Succesfully data added", HttpStatus.CREATED, company);
			} else
				return ResponseHandler.generateResponse("Sorry data is not inserted!", HttpStatus.CONFLICT, "Company code "+ company.getCompanyCode() + " exists");
		} else
			return ResponseHandler.generateResponse("Sorry company turnover is not sufficient!", HttpStatus.NOT_ACCEPTABLE, "Minimum turnover of Rs.100000000 required");
	}

	
	 @PostMapping(value="/stock/add/{sId}") 
	 public ResponseEntity<?> addCompanyPrice(@PathVariable("sId") int companyCode, @RequestBody Company company){
		 
		 Company existingCompany= companyService.getCompanyDetailsById(companyCode);
		  
		  if (existingCompany!=null) {
			  existingCompany.setStockPrice(company.getStockPrice());
			  
			  if(companyService.updateStockPrice(existingCompany) && stockService.addStock(existingCompany))
				  return ResponseHandler.generateResponse("Price added", HttpStatus.OK, existingCompany);
			  
		  }
		  
		  return ResponseHandler.generateResponse("Error", HttpStatus.INTERNAL_SERVER_ERROR, "Internal Error");
	 }
	 

	@DeleteMapping("/company/delete/{sId}")
	public ResponseEntity<?> deleteCompany(@PathVariable("sId") int companyCode){
		if (companyService.deleteCompany(companyCode) && stockService.deleteStock(companyCode))
			return ResponseHandler.generateResponse("Deleted", HttpStatus.NO_CONTENT, "Company record deleted");

		else
			return ResponseHandler.generateResponse("Cannot be deleted", HttpStatus.INTERNAL_SERVER_ERROR, "Cannot delete due to internal error");
	}

	@PutMapping(value="/stock/put/{sId}") 
	public ResponseEntity<?> updateStockPrice(@PathVariable("sId") int companyCode, @RequestBody Company company){
		
		Company existingCompany= companyService.getCompanyDetailsById(companyCode);
		
		if (existingCompany!=null) {
			//double prevPrice= existingCompany.getStockPrice();
			existingCompany.setStockPrice(company.getStockPrice());
			  
			if(companyService.updateStockPrice(existingCompany) && stockService.addStock(existingCompany)) {
				//Adding update in Kafka
//				String updateMsg= existingCompany.getCompanyName()+ "-> Previous price: "+ prevPrice + "New Price: "+ company.getStockPrice(); 
//				dataPublisher.setTemplate(updateMsg);
			  
				return ResponseHandler.generateResponse("Price updated", HttpStatus.CREATED, existingCompany);
			  }
		  }
		return ResponseHandler.generateResponse("Price updation not possible", HttpStatus.INTERNAL_SERVER_ERROR, "Updation not possible");
	  }
	  
	//Function to fetch user token from user micro-service
//	public String getUserToken() throws RestClientException, Exception{
//			List<ServiceInstance> instances= discoveryClient.getInstances("user-producer");
//			//System.out.println(instances.toString());
//			ServiceInstance serviceInstance= instances.get(0);
//			
//			String baseUrl= serviceInstance.getUri().toString();
//			
//			ResponseEntity<String> response =null;
//				
//			try {
//				RestTemplate restTemplate= new RestTemplate();
//				baseUrl += "/auth/v1.0/getToken";
//				response= restTemplate.exchange(baseUrl, HttpMethod.GET, getHeaders(), String.class);
//			}
//			catch(Exception e) {
//				e.printStackTrace();
//			}
//				//System.out.println(response.getBody());
//			return response.getBody();
//		}
//			
//		private static HttpEntity<?> getHeaders() throws Exception{
//			HttpHeaders headers = new HttpHeaders();
//			headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
//			return new HttpEntity<>(headers);
//		}
}