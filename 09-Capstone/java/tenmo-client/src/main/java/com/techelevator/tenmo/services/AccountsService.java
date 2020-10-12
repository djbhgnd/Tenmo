package com.techelevator.tenmo.services;

import java.math.BigDecimal;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class AccountsService {

	private final String BASE_SERVICE_URL;
	private RestTemplate restTemplate = new RestTemplate();
	
	public AccountsService(String baseUrl) {
		this.BASE_SERVICE_URL = baseUrl + "accounts/";
	}
	
	public BigDecimal getBalance(String authToken) {
		HttpEntity<?> headerStuff = new HttpEntity<>(authHeaders(authToken));
		ResponseEntity<BigDecimal> response = restTemplate.exchange(BASE_SERVICE_URL + "balance", HttpMethod.GET, headerStuff,BigDecimal.class);
		return response.getBody();
	}

	private HttpHeaders authHeaders(String authToken) {
		HttpHeaders headers = new HttpHeaders();
		headers.setBearerAuth(authToken);
		return headers;
	}
}
