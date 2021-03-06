package com.cts.stockmarket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import com.cts.stockmarket.filter.JWTFilter;

@SpringBootApplication
public class CompanyManagementAppApplication {
	
//	@Bean 
//	public FilterRegistrationBean<JWTFilter> jwtFilter() { 
//		FilterRegistrationBean<JWTFilter> fb =new FilterRegistrationBean<>(); 
//		fb.setFilter(new JWTFilter(/*jwt*/));
//		fb.addUrlPatterns("/api/v1.0/market/*"); 
//		return fb; 
//	}
	 
	public static void main(String[] args) {
		SpringApplication.run(CompanyManagementAppApplication.class, args);
	}
}