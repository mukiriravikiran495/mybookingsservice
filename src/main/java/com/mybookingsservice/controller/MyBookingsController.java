package com.mybookingsservice.controller;

import java.lang.invoke.MethodHandles;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mybookingsservice.domain.CustomerBookingResponseDTO;
import com.mybookingsservice.domain.MyBookingsDTO;
import com.mybookingsservice.service.MyBookingsService;

@RestController
@RequestMapping( path = "/v1/api/bookings")
public class MyBookingsController {

	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
	
	private final MyBookingsService service;
	
	
	@Autowired
	public MyBookingsController(MyBookingsService service) {
		this.service = service;
	}
	
	@GetMapping( value = "/getall")
	public List<MyBookingsDTO> getbookings(){
		return service.getall();
	}
	
	@GetMapping("/customer/{custId}")
    public ResponseEntity<List<CustomerBookingResponseDTO>> getBookingsByCustomer(@PathVariable Long custId) {
        List<CustomerBookingResponseDTO> bookings = service.getBookingsByCustomerId(custId);
        return ResponseEntity.ok(bookings);
    }
	
	public void dummyMethod() {
		
	}
}
