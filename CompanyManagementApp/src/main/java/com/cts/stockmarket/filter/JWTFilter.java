package com.cts.stockmarket.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.web.filter.GenericFilterBean;

import com.cts.stockmarket.controller.ConsumerController;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class JWTFilter extends GenericFilterBean {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest httpRequest= (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		
		
		/*
		 * if(authHeader==null || !authHeader.startsWith("Bearer")) { throw new
		 * ServletException("Missing or invalid Authentication Header"); }
		 */
		
		String jwtToken= "";
		try{
			jwtToken=new ConsumerController().getUserToken(); 
			System.out.println(jwtToken);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		Claims claims= Jwts.parser().setSigningKey("secret key").parseClaimsJws(jwtToken).getBody();
		
		httpRequest.setAttribute("username", claims);
		
		chain.doFilter(request, response);
	}

}
