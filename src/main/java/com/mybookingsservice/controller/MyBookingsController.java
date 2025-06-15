package com.mybookingsservice.controller;

import java.lang.invoke.MethodHandles;
import java.util.List;

import javax.sql.DataSource;

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
import com.mybookingsservice.domain.CustCancelledBookingResponseDTO;
import com.mybookingsservice.domain.VendorCancelledBookingResponse;
import com.mybookingsservice.domain.CustCancelResponseDTO;
import com.mybookingsservice.domain.CustomerBookingResponseDTO;
import com.mybookingsservice.domain.MyBookingsDTO;
import com.mybookingsservice.domain.VendorBookingResponseDTO;
import com.mybookingsservice.domain.VendorCancelResponseDTO;
import com.mybookingsservice.exceptions.InvalidRequestException;
import com.mybookingsservice.exceptions.StatusHandler;
import com.mybookingsservice.service.MyBookingsService;

@RestController
@RequestMapping( path = "/v1/api/bookings")
public class MyBookingsController {

    private final DataSource dataSource;

	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
	
	private final MyBookingsService service;
	
	
	@Autowired
	public MyBookingsController(MyBookingsService service, DataSource dataSource) {
		this.service = service;
		this.dataSource = dataSource;
	}
	
	@GetMapping( value = "/getall")
	public List<MyBookingsDTO> getbookings(){
		return service.getall();
	}
	
	/* 
	 	 Below All API's are related to Customer Bookings 
	 */
	
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
	
	@PostMapping( value = "/customer/cancel/{custId}/{bookingId}")
	public ResponseEntity<CustomerBookingResponseDTO> cancelBookingById( @PathVariable Long custId, @PathVariable Long bookingId) throws InvalidRequestException {
		logger.info("START Cancel Booking By Id Controller : "+custId+" "+bookingId);
		StatusHandler statusHandler = new StatusHandler();
		CustomerBookingResponseDTO cancelResponse = new CustomerBookingResponseDTO();
		
		if( null == custId || null == bookingId ) {
			throw new InvalidRequestException(AppConstants.INVALID_REQUEST);
		}
		try {
			cancelResponse = service.cancelBookingById(custId, bookingId,  cancelResponse, statusHandler);
			
		}catch(Exception ex) {
			
		}
		ResponseEntity<CustomerBookingResponseDTO> response = new ResponseEntity<>(cancelResponse, HttpStatus.OK);
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
	
	
	@GetMapping( value = "/customer/cancel/getall")
	public ResponseEntity<List<CustCancelledBookingResponseDTO>> getCustCancelledBookings( @RequestParam Long custId, 
																	 @RequestParam String status) throws InvalidRequestException{
		logger.info("Start : Get all Cancelled Bookings controller for custId : "+custId);
		StatusHandler statusHandler = new StatusHandler();
		CustCancelledBookingResponseDTO cancelledBookings = new CustCancelledBookingResponseDTO();
		
		if( null == custId ||  status.isEmpty() || null == status) {
			throw new InvalidRequestException(AppConstants.INVALID_REQUEST);
		}
		
		List<CustCancelledBookingResponseDTO> response = service.getAllCancelledBookings(custId, status, cancelledBookings, statusHandler);
		
		
		
		logger.info("END : Get all Cancelled Bookings controller for custId :"+custId);
		return ResponseEntity.ok(response);
	}
	
	/*
	 * Below All API's are related to Customer Bookings
	 */
	
	@GetMapping(value = "/vendor/{vendorId}")
	public ResponseEntity<List<VendorBookingResponseDTO>> getBookingsByVendorId(@PathVariable Long vendorId) throws InvalidRequestException {
		logger.info("STRAT : Fetch all Vendor Bookings : ");
		
		if(null == vendorId) {
			throw new InvalidRequestException(AppConstants.INVALID_REQUEST);
		}
		
		List<VendorBookingResponseDTO>  vendor = service.getBookingByVendorId(vendorId);
		ResponseEntity<List<VendorBookingResponseDTO>> response = new ResponseEntity<>(vendor, HttpStatus.OK);

		logger.info("END : Fetch all Vendor Bookings : " + vendor);
		return response;
	}
	
	@GetMapping( value = "/vendor/{vendorId}/{bookingId}")
	public ResponseEntity<VendorBookingResponseDTO> getBookingByVendorIdbookingId(@PathVariable Long vendorId, @PathVariable Long bookingId) throws InvalidRequestException{
		logger.info("Start : get booking details by vendorId and bookingId controller : "+vendorId+" "+bookingId);
		StatusHandler statusHandler = new StatusHandler();
		VendorBookingResponseDTO vendorbooking = new VendorBookingResponseDTO();
		if( null == vendorId || null == bookingId) {
			throw new InvalidRequestException(AppConstants.INVALID_REQUEST);
		}
		
		vendorbooking = service.getBookingByVendorIdbookingId(vendorId, bookingId, vendorbooking, statusHandler);
		ResponseEntity<VendorBookingResponseDTO> response = new ResponseEntity<>(vendorbooking, HttpStatus.OK);
		
		logger.info("End : get booking details by vendorId and bookingId controller : "+vendorId+" "+bookingId);
		return response;
	}
	
	@PostMapping( value = "/vendor/cancel/{vendorId}/{bookingId}")
	public ResponseEntity<VendorBookingResponseDTO> cancelVendorBooking(@PathVariable Long vendorId, @PathVariable Long bookingId) throws InvalidRequestException{
		logger.info("Start : Cancel vendor Booking Controller : "+vendorId+" "+bookingId);
		StatusHandler statusHandler = new StatusHandler();
		VendorBookingResponseDTO vendorResponse = new VendorBookingResponseDTO();
		if(null == vendorId || null == bookingId) {
			throw new InvalidRequestException(AppConstants.INVALID_REQUEST);
		}
		
		vendorResponse = service.cancelVendorBooking(vendorId, bookingId, vendorResponse, statusHandler);
		ResponseEntity<VendorBookingResponseDTO> response = new ResponseEntity<>(vendorResponse, HttpStatus.OK);
		logger.info("End : Cancel vendor Booking Controller :  "+vendorId+" "+bookingId);
		return response;
	}
	
	@GetMapping( value = "/vendor/cancel/getall")
	public ResponseEntity<List<VendorCancelledBookingResponse>> getVendorCancelledBookings( @RequestParam Long vendorId, 
																	 @RequestParam String status) throws InvalidRequestException{
		logger.info("Start : Get all Cancelled Bookings controller for custId : "+vendorId);
		StatusHandler statusHandler = new StatusHandler();
		VendorCancelledBookingResponse cancelledBookings = new VendorCancelledBookingResponse();
		
		if( null == vendorId ||  status.isEmpty() || null == status) {
			throw new InvalidRequestException(AppConstants.INVALID_REQUEST);
		}
		
		List<VendorCancelledBookingResponse> response = service.getVendorCancelledBookings(vendorId, status, cancelledBookings, statusHandler);
		
		
		
		logger.info("END : Get all Cancelled Bookings controller for custId :"+vendorId);
		return ResponseEntity.ok(response);
	}
	
	@PostMapping( value = "/vendor/accept/{vendorId}/{bookingId}")
	public ResponseEntity<VendorBookingResponseDTO> acceptBooking( @PathVariable Long vendorId, @PathVariable Long bookingId) throws InvalidRequestException{
		logger.info("Start : Accept booking Controller : "+vendorId+" "+bookingId);
		StatusHandler statusHandler = new StatusHandler();
		VendorBookingResponseDTO vendorBookings = new VendorBookingResponseDTO();
		if( null == vendorId || null == bookingId) {
			throw new InvalidRequestException(AppConstants.INVALID_REQUEST);
		}
		vendorBookings =  service.acceptBpooking(vendorId, bookingId, vendorBookings, statusHandler);
		ResponseEntity<VendorBookingResponseDTO> response = new ResponseEntity<>(vendorBookings, HttpStatus.OK);
		
		logger.info("End : Accept Booking Controller : "+vendorId+" "+bookingId);
		return response;
	}
	
	
	
}















