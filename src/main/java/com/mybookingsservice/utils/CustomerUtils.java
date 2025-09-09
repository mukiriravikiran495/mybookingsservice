package com.mybookingsservice.utils;

import java.lang.invoke.MethodHandles;
import java.util.Collections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import com.mybookingsservice.domain.CustomerDetailsDTO;
import com.mybookingsservice.domain.CustomerResponse;
import com.mybookingsservice.domain.TokenID;
import com.mybookingsservice.entity.CustomerDetails;
import com.mybookingsservice.entity.CustomerTokens;

@Component
public class CustomerUtils {
	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
	
    private  RestTemplate restTemplate;
	
	@Value("${customer.service.url}")
    private String customerServiceUrl;
	
	@Value("${customerlogin.service.url}")
    private String customerLoginServiceUrl;

	
	public CustomerUtils() {
		
	}
	@Autowired
	public CustomerUtils(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}
	
	
	
	public  CustomerResponse findCustomer(Long custId, String token) {
		logger.info("Start : get customer details by custId : "+custId);
		
		String url = customerServiceUrl+"/get/"+custId;
		
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer "+token);
		System.out.println("token : "+token);
		System.out.println(" URL : "+url);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Void> entity = new HttpEntity<>(headers);

        try {
            ResponseEntity<CustomerResponse> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                CustomerResponse.class
            );
            logger.info("Response: " + response.getBody());
            return response.getBody();
        } catch (HttpClientErrorException | HttpServerErrorException ex) {
            logger.error("HTTP Error: " + ex.getStatusCode() + " - " + ex.getResponseBodyAsString());
            System.out.println(ex.getMessage());
            throw ex;
        } catch (Exception e) {
            logger.error("Request failed: ", e);
            throw e;
        }
	}
	private TokenID getToken() {
		logger.info("Start get token customer-service : ");
//		String url = customerServiceUrl+"/auth/token";
//		HttpHeaders headers = new HttpHeaders();
//		System.out.println(" URL : "+url);
//        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        HttpEntity<Void> entity = new HttpEntity<>(headers);
//
//        ResponseEntity<TokenID> response = restTemplate.exchange(
//                url,
//                HttpMethod.GET,
//                entity,
//                TokenID.class
//        );
        
		logger.info("End : get token customer-service : ");
		return null;
	}
	public CustomerDetails createCustomer(CustomerDetails details) {
		logger.info("Start : create customer utils : "+details);
		
		return null;
	}
	public CustomerResponse updateCustomer(CustomerDetailsDTO detailsDTO) {
		logger.info("Start : create customer utils : "+detailsDTO);
		
		String url = customerServiceUrl+"/updateCustomer";
		
		
		
		HttpHeaders headers = new HttpHeaders();
//		headers.set("Authorization", "Bearer "+detailsDTO.getAccessToken());
//		System.out.println("token : "+detailsDTO.getAccessToken());
		System.out.println(" URL : "+url);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<CustomerDetailsDTO> entity = new HttpEntity<>(detailsDTO, headers);

        try {
            ResponseEntity<CustomerResponse> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                entity,
                CustomerResponse.class
            );
            logger.info("Response: " + response.getBody());
            logger.info("End : create vendor utils : ");
            return response.getBody();
        } catch (HttpClientErrorException | HttpServerErrorException ex) {
            logger.error("HTTP Error: " + ex.getStatusCode() + " - " + ex.getResponseBodyAsString());
            System.out.println(ex.getMessage());
            throw ex;
        } catch (Exception e) {
            logger.error("Request failed: ", e);
            throw e;
        }
	}
	public CustomerResponse updateCustomerName(CustomerDetailsDTO detailsDTO) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public CustomerTokens validateAccessToken(String token) {
		String url = customerLoginServiceUrl+"/auth/token";
		
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer "+token);
		System.out.println("token : "+token);
		System.out.println(" URL : "+url);
        

        HttpEntity<Void> entity = new HttpEntity<>(headers);

        try {
        	ResponseEntity<CustomerTokens> response = restTemplate.exchange(
        		    url,
        		    HttpMethod.GET,
        		    entity,
        		    CustomerTokens.class
        	);
            logger.info("Response: " + response.getBody());
            logger.info("End : create customer utils : ");
            return response.getBody();
        } catch (HttpClientErrorException | HttpServerErrorException ex) {
            logger.error("HTTP Error: " + ex.getStatusCode() + " - " + ex.getResponseBodyAsString());
            System.out.println(ex.getMessage());
            throw ex;
        } catch (Exception e) {
            logger.error("Request failed: ", e);
            throw e;
        }
		
	}

}
