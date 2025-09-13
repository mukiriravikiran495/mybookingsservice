package com.mybookingsservice.utils;

import java.lang.invoke.MethodHandles;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import com.mybookingsservice.domain.ConfirmBookingResponse;
import com.mybookingsservice.domain.VehicleSearchRequest;
import com.mybookingsservice.domain.VendorDetailsDTO;
import com.mybookingsservice.domain.VendorsListResponse;
import com.mybookingsservice.entity.VendorTokens;

@Component
public class VendorUtils {
private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
	
    private  RestTemplate restTemplate;
	
	@Value("${vendor.service.url}")
    private String vendorServiceUrl;
	
	@Value("${vendorlogin.service.url}")
    private String vendorLoginServiceUrl;

	
	public VendorUtils() {
		
	}
	@Autowired
	public VendorUtils(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}
	
	
	
	public ConfirmBookingResponse findNearestTrucks(VehicleSearchRequest vehicleSearchRequest, String token,
			String appId) {
		logger.info("Start : call vendorservice "+vehicleSearchRequest);
		String url = vendorServiceUrl+"/get/nearest/trucks";
		
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer "+token);
		headers.set("APPID", appId);
		System.out.println("token : "+token);
		System.out.println(" URL : "+url);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<VehicleSearchRequest> entity = new HttpEntity<>(vehicleSearchRequest, headers);

        try {
            ResponseEntity<ConfirmBookingResponse> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                entity,
                ConfirmBookingResponse.class
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
	
	public VendorTokens validateAccessToken(String token) {
		String url = vendorLoginServiceUrl+"/auth/token";
		
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer "+token);
		System.out.println("token : "+token);
		System.out.println(" URL : "+url);
        

        HttpEntity<Void> entity = new HttpEntity<>(headers);

        try {
        	ResponseEntity<VendorTokens> response = restTemplate.exchange(
        		    url,
        		    HttpMethod.GET,
        		    entity,
        		    VendorTokens.class
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
	public VendorsListResponse findAvailablePackersAndMovers(String getcCity, String token, long isOnline) {
		logger.info("Start : call vendorservice "+getcCity);
		String url = vendorServiceUrl+"/get/packersandmovers/"+getcCity+"/"+isOnline;
		
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer "+token);
		
		System.out.println("token : "+token);
		System.out.println(" URL : "+url);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<VehicleSearchRequest> entity = new HttpEntity<>( headers);

        try {
            ResponseEntity<VendorsListResponse> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                VendorsListResponse.class
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
	
}
