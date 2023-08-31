package com.apigateway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api")
public class TestController {

	@Autowired
	private RestTemplate restTemplate;
	
	@GetMapping("/get/Test3")
	public String getData()
	{
		String response = "Test  Data From Service 1";
		String secondService = "http://3.110.90.34:8082/api/get/Test2"; 
    
	     response =  restTemplate.getForObject(secondService,String.class);
	
		return response;
		
	}
}
