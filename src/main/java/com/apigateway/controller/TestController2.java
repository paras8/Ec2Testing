package com.apigateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TestController2 {

	
	@GetMapping("/get/Test2")
	public String getData()
	{
		return "Test  Data From Service 2";
	}
}
