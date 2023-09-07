package com.apigateway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api")
public class TestController {

	@Autowired
	private RestTemplate restTemplate;

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/get/Test3")
	public String getData(@RequestHeader("Authorization") String header) {
		String response = "Test  Data From Service 1";

		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", header);

		HttpEntity<String> entity = new HttpEntity<>(headers);

		String secondService = "http://43.204.108.186:8082/api/get/Test2";

		ResponseEntity<String> responseEntity = restTemplate.exchange(secondService, HttpMethod.GET, entity,
				String.class);

		response = responseEntity.getBody();

		return response;

	}
}
