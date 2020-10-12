package com.techelevator.tenmo.services;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import com.techelevator.tenmo.models.Transfer;

public class TransferService {

	private final String BASE_SERVICE_URL;
	private RestTemplate restTemplate = new RestTemplate();
	
	public TransferService(String baseUrl) {
		this.BASE_SERVICE_URL = baseUrl + "transfer";
	}

	private HttpHeaders authHeaders(String authToken) {
		HttpHeaders headers = new HttpHeaders();
		headers.setBearerAuth(authToken);
		return headers;
	}
	
	 public Transfer doTransfer(String token,String CSV) {	  
		  HttpHeaders headers = authHeaders(token);
		  headers.setContentType(MediaType.APPLICATION_JSON);
		  Transfer transfer = makeTransfer(CSV);
		  HttpEntity<Transfer> headerStuff = new HttpEntity<Transfer>(transfer,headers);
		  transfer = restTemplate.postForObject(BASE_SERVICE_URL,headerStuff,Transfer.class);		  
		  return transfer;
	  }

	 public Transfer[] getTransfersByUserId(String authToken) {
			HttpEntity<?> headerStuff = new HttpEntity<>(authHeaders(authToken));
			ResponseEntity<Transfer[]> response = restTemplate.exchange(BASE_SERVICE_URL + "/mytransfers", HttpMethod.GET, headerStuff,Transfer[].class);
			return response.getBody();
	}
	
	 public Transfer getTransferByTransferId(String authToken, int transferId) {
		 	HttpEntity<?> headerStuff = new HttpEntity<>(authHeaders(authToken));
			ResponseEntity<Transfer> response = restTemplate.exchange(BASE_SERVICE_URL + "/" + transferId, HttpMethod.GET, headerStuff,Transfer.class);
			return response.getBody();
	 }
	 
	 public String transferTypeByTypeId(String authToken, int typeId) {	  
		  HttpHeaders headers = authHeaders(authToken);
		  headers.setContentType(MediaType.APPLICATION_JSON);
		  HttpEntity<Integer> headerStuff = new HttpEntity<Integer>(typeId,headers);
		  ResponseEntity<String> response = restTemplate.exchange(BASE_SERVICE_URL + "/gettype", HttpMethod.POST, headerStuff,String.class);		  
		  return response.getBody();
	  }
	 
	 public String transferStatusByStatusId(String authToken, int statusId) {	  
		  HttpHeaders headers = authHeaders(authToken);
		  headers.setContentType(MediaType.APPLICATION_JSON);
		  HttpEntity<Integer> headerStuff = new HttpEntity<Integer>(statusId,headers);
		  ResponseEntity<String> response = restTemplate.exchange(BASE_SERVICE_URL + "/getstatus", HttpMethod.POST, headerStuff,String.class);		  
		  return response.getBody();
	  }
	 
	 private Transfer makeTransfer(String CSV) {
		    String[] parsed = CSV.split(",");   
		    return new Transfer(parsed[0],parsed[1],parsed[2]);
	 }
}
