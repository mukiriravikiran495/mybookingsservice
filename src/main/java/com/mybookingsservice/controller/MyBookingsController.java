package com.mybookingsservice.controller;

import java.lang.invoke.MethodHandles;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mybookingsservice.constants.AppConstants;
import com.mybookingsservice.domain.CancelResponseDTO;
import com.mybookingsservice.domain.CancelledBookingResponseDTO;
import com.mybookingsservice.domain.CustomerBookingResponseDTO;
import com.mybookingsservice.domain.MyBookingsDTO;
import com.mybookingsservice.exceptions.InvalidRequestException;
import com.mybookingsservice.exceptions.StatusHandler;
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
	
	
	@GetMapping( value = "/customer/{custId}/{bookingId}")
	public ResponseEntity<CustomerBookingResponseDTO> getBookingsByBookingId(@PathVariable Long custId, 
																	  @PathVariable Long bookingId) {
		logger.info("START : Get Bookings By ID Controller : "+custId+" : "+bookingId);
		StatusHandler statusHandler = new StatusHandler();
		CustomerBookingResponseDTO customerBookingResponse = new CustomerBookingResponseDTO();
		try {
			if( null == custId || null == bookingId ) {
				throw new InvalidRequestException(AppConstants.INVALID_REQUEST);
			}
			customerBookingResponse = service.getBookingsByBookingId(custId, bookingId, statusHandler, customerBookingResponse);
			ResponseEntity<CustomerBookingResponseDTO> response = new ResponseEntity<>(customerBookingResponse, HttpStatus.OK);
			logger.info("END : Get Bookings By ID Controller : "+customerBookingResponse);
			return response;
			
		}catch(InvalidRequestException ex) {
			statusHandler.setErrorCode("400");
			statusHandler.setErrorMessage(AppConstants.INVALID_REQUEST);
			customerBookingResponse.setStatusHandler(statusHandler);
			return new ResponseEntity<>(customerBookingResponse, HttpStatus.BAD_REQUEST);
		}catch(Exception e) {
			statusHandler.setErrorCode("400");
			statusHandler.setErrorMessage(e.getMessage());
			customerBookingResponse.setStatusHandler(statusHandler);
			return new ResponseEntity<>(customerBookingResponse, HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@PostMapping( value = "/cancelbooking/{custId}/{bookingId}")
	public ResponseEntity<CancelResponseDTO> cancelBookingById( @PathVariable Long custId, @PathVariable Long bookingId) throws InvalidRequestException {
		logger.info("START Cancel Booking By Id Controller : "+custId+" "+bookingId);
		StatusHandler statusHandler = new StatusHandler();
		CancelResponseDTO cancelResponse = new CancelResponseDTO();
		
		if( null == custId || null == bookingId ) {
			throw new InvalidRequestException(AppConstants.INVALID_REQUEST);
		}
		try {
			cancelResponse = service.cancelBookingById(custId, bookingId, cancelResponse, statusHandler);
			
		}catch(Exception ex) {
			
		}
		ResponseEntity<CancelResponseDTO> response = new ResponseEntity<>(cancelResponse, HttpStatus.OK);
		return response;
	}
	
	@GetMapping("/customer/{custId}")
    public ResponseEntity<List<CustomerBookingResponseDTO>> getBookingsByCustomer(@PathVariable Long custId) throws InvalidRequestException {
		logger.info("START Customer Bookings Controller : ");
		if(null == custId) {
			throw new InvalidRequestException(AppConstants.INVALID_REQUEST);
		}
        List<CustomerBookingResponseDTO> bookings = service.getBookingsByCustomerId(custId);
        logger.info("END : Customer Bookings Controller : ");
        return ResponseEntity.ok(bookings);
    }
	
	
	@GetMapping( value = "/getcancelbookings")
	public ResponseEntity<List<CancelledBookingResponseDTO>> getAllCancelledBookings( @RequestParam Long custId, 
																	 @RequestParam String status) throws InvalidRequestException{
		logger.info("Start : Get all Cancelled Bookings controller for custId : "+custId);
		StatusHandler statusHandler = new StatusHandler();
		CancelledBookingResponseDTO cancelledBookings = new CancelledBookingResponseDTO();
		
		if( null == custId ||  status.isEmpty() || null == status) {
			throw new InvalidRequestException(AppConstants.INVALID_REQUEST);
		}
		
		List<CancelledBookingResponseDTO> response = service.getAllCancelledBookings(custId, status, cancelledBookings, statusHandler);
		
		
		
		logger.info("END : Get all Cancelled Bookings controller for custId :"+custId);
		return ResponseEntity.ok(response);
	}
	
}















