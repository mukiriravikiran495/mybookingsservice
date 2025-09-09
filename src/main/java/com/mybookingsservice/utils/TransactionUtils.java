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

import com.mybookingsservice.domain.MyBookingsRequest;
import com.mybookingsservice.domain.TruckBookingTranLogRequest;
import com.mybookingsservice.domain.TranLogResponse;
import com.mybookingsservice.entity.CustomerTokens;

@Component
public class TransactionUtils {
	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	private RestTemplate restTemplate;

	@Value("${transaction.service.url}")
	private String transactionServiceUrl;

	public TransactionUtils() {

	}

	@Autowired
	public TransactionUtils(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	public TranLogResponse savetruckBookingTranLog( CustomerTokens custTokens,
			String vehicleBookingForm, String failed, String errorMessage) {
		logger.info("Start : create transaction utils : " + custTokens);

		String url = transactionServiceUrl + "/create/tranlog/truckbookingfailure";
		
		TruckBookingTranLogRequest tranLogRequest = new TruckBookingTranLogRequest();
		tranLogRequest.setTokenUuid(custTokens.getTokenUuid());
		tranLogRequest.setCustId(custTokens.getCustId());
		tranLogRequest.setMobile(custTokens.getMobile());
		tranLogRequest.setAccessToken(custTokens.getAccessToken());
		tranLogRequest.setCorrelationId(custTokens.getCorrelationId());
		tranLogRequest.setAction(vehicleBookingForm);
		tranLogRequest.setStatus(failed);
		tranLogRequest.setErrorMessage(errorMessage);
		
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer " + custTokens.getAccessToken());
		System.out.println("token : " + custTokens.getAccessToken());
		System.out.println(" URL : " + url);
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<TruckBookingTranLogRequest> entity = new HttpEntity<>(tranLogRequest, headers);

		try {
			ResponseEntity<TranLogResponse> tranResponse = restTemplate.exchange(url, HttpMethod.POST, entity,
					TranLogResponse.class);
			logger.info("Response: " + tranResponse.getBody());
			logger.info("End : create customer utils : ");
			return tranResponse.getBody();
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
