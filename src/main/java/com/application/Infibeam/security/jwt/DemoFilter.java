package com.application.Infibeam.security.jwt;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Component;

@Component
public class DemoFilter implements Filter{


	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest req=(HttpServletRequest)request;
		 System.out.println("path is"+req.getRequestURL());
		 chain.doFilter(request, response);
		
	}
	
	

}
