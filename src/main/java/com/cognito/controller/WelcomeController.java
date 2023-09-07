package com.cognito.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class WelcomeController {

	@Autowired
	private RestTemplate restTemplate;
	
	@GetMapping("/user/greetMe")
	public String userGreet() {
		return "I am the User";
	}
	
	@GetMapping("/callback")
    public ResponseEntity<String> handleCallback(@RequestParam("code") String code) {
        // Handle the code received from Cognito here
        System.out.println("Received code: " + code);

        // Prepare the request to exchange the code for tokens
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("grant_type", "authorization_code");
        map.add("code", code);
        map.add("client_id", "4sk3sll5el6glat18fb22ek1ts");
        map.add("client_secret", "n8pl0c24ikumbho8rm7lpb1tmromn76tv9mjgkir8618c5c84b7");
        map.add("redirect_uri", "https://65.1.134.142:8443/callback"); // Ensure this matches the configured callback URL

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

        // Define the Cognito token endpoint URL
        String tokenUrl = "https://spring-boot-secure.auth.ap-south-1.amazoncognito.com/oauth2/token"; // Replace with your Cognito endpoint

        // Send the request to Cognito to exchange the code for tokens
        ResponseEntity<String> response = restTemplate.exchange(tokenUrl, HttpMethod.POST, request, String.class);

        // Handle the response, which contains the tokens
        if (response.getStatusCode() == HttpStatus.OK) {
            String tokens = response.getBody();
            System.out.println("Received tokens: " + tokens);
            return new ResponseEntity<>(tokens, HttpStatus.OK);
        } else {
            // Handle the error case
            return new ResponseEntity<>(response.getBody(), response.getStatusCode());
        }
    }
	
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/admin/greetMe")
	public String adminGreet() {
		return "I am the Admin";
	}
	
	@PreAuthorize("hasRole('USER')")
	@PostMapping("/user/add")
	public String addUser(@RequestBody User myname) {
		return myname.getName();
	}
	
	
}
