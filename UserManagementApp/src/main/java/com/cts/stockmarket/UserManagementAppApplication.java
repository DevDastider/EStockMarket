package com.cts.stockmarket;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import com.cts.stockmarket.filter.JWTFilter;

@SpringBootApplication
public class UserManagementAppApplication {
	
	@Bean
	public FilterRegistrationBean jwtFilter()
	{
		FilterRegistrationBean fb = new FilterRegistrationBean();
		fb.setFilter(new JWTFilter());
		fb.addUrlPatterns("/api/v1.0/*");
		return fb;
	}

	public static void main(String[] args) {
		SpringApplication.run(UserManagementAppApplication.class, args);
	}

}