package com.apigateway.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api")
public class TestController2 {

	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/get/Test2")
	public String getData(HttpServletRequest request)
	{   String header = request.getHeader("Authorization");
	System.out.println(header);
		return header+"  Test  Data From Service 2 ADMIN";
	}
	@PreAuthorize("hasRole('USER')")
	@GetMapping("/getUser/Test2")
	public String getDat()
	{
		return "Test  Data From Service 2 USER";
	}
}
