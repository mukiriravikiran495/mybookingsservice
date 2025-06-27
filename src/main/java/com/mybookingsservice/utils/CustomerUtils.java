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
import com.mybookingsservice.configuration.HibernateConfiguration;
import com.mybookingsservice.domain.CustomerDetailsDTO;
import com.mybookingsservice.domain.CustomerResponse;
import com.mybookingsservice.domain.TokenID;
import com.mybookingsservice.entity.CustomerDetails;

@Component
public class CustomerUtils {
	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
	
    private  RestTemplate restTemplate;
	
	@Value("${customer.service.url}")
    private String customerServiceUrl;

	
	public CustomerUtils() {
		
	}
	@Autowired
	public CustomerUtils(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}
	
	
	
	public  CustomerResponse getCustomer(Long custId) {
		logger.info("Start : get customer details by custId : "+custId);
		HibernateConfiguration conf = new HibernateConfiguration();
		String url = customerServiceUrl+"/get/"+custId;
		
		TokenID token = getToken();
		
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer "+token.getToken());
		System.out.println("token : "+token.getToken());
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
		String url = customerServiceUrl+"/auth/token";
		HttpHeaders headers = new HttpHeaders();
		System.out.println(" URL : "+url);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Void> entity = new HttpEntity<>(headers);

        ResponseEntity<TokenID> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                TokenID.class
        );
        
		logger.info("End : get token customer-service : ");
		return response.getBody();
	}
	public CustomerDetails createCustomer(CustomerDetails details) {
		logger.info("Start : create customer utils : "+details);
		
		String url = customerServiceUrl+"/create";
		
		TokenID token = getToken();
		
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer "+token.getToken());
		System.out.println("token : "+token.getToken());
		System.out.println(" URL : "+url);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<CustomerDetails> entity = new HttpEntity<>(details, headers);

        try {
            ResponseEntity<CustomerDetails> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                entity,
                CustomerDetails.class
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
	public CustomerResponse updateCustomer(CustomerDetailsDTO detailsDTO) {
		logger.info("Start : create customer utils : "+detailsDTO);
		
		String url = customerServiceUrl+"/updateCustomer";
		
		TokenID token = getToken();
		
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer "+token.getToken());
		System.out.println("token : "+token.getToken());
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

}
