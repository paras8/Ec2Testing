package com.apigateway.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TestController2 {

	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/get/Test2")
	public String getData()
	{
		return "Test  Data From Service 2 ADMIN";
	}
	@PreAuthorize("hasRole('USER')")
	@GetMapping("/getUser/Test2")
	public String getDat()
	{
		return "Test  Data From Service 2 USER";
	}
}
