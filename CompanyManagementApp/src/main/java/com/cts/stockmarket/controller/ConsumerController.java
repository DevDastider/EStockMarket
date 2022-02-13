package com.cts.stockmarket.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@RestController
public class ConsumerController {
	
	@Autowired
	private DiscoveryClient discoveryClient;
	
	public String getUserToken() throws RestClientException, Exception{
		List<ServiceInstance> instances= discoveryClient.getInstances("user-producer");
		System.out.println(instances.toString());
		ServiceInstance serviceInstance= instances.get(0);
		
		String baseUrl= serviceInstance.getUri().toString();
		
		ResponseEntity<String> response =null;
		
		try {
			RestTemplate restTemplate= new RestTemplate();
			baseUrl += "/auth/v1.0/getToken";
			response= restTemplate.exchange(baseUrl, HttpMethod.GET, getHeaders(), String.class);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println(response.getBody());
		return response.getBody();
	}
	
	private static HttpEntity<?> getHeaders() throws Exception
	{
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		return new HttpEntity<>(headers);
	}
	
}
