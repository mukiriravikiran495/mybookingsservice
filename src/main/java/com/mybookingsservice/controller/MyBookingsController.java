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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mybookingsservice.constants.AppConstants;
import com.mybookingsservice.domain.CustCancelledBookingResponse;
import com.mybookingsservice.domain.CustomerBookingResponseDTO;
import com.mybookingsservice.domain.HouseholdItemsResponse;
import com.mybookingsservice.domain.MyBookingsDTO;
import com.mybookingsservice.domain.VendorBookingResponseDTO;
import com.mybookingsservice.domain.VendorCancelledBookingResponse;
import com.mybookingsservice.domain.VendorEstimateRequest;
import com.mybookingsservice.domain.VendorEstimateResponse;
import com.mybookingsservice.entity.Vendor;
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
	public ResponseEntity<CustCancelledBookingResponse> getCustCancelledBookings( @RequestParam Long custId, 
																	 @RequestParam String status) throws InvalidRequestException{
		logger.info("Start : Get all Cancelled Bookings controller for custId : "+custId);
		StatusHandler statusHandler = new StatusHandler();
		CustCancelledBookingResponse cancelledBookings = new CustCancelledBookingResponse();
		try {
			if( null == custId ||  status.isEmpty() || null == status) {
				throw new InvalidRequestException(AppConstants.INVALID_REQUEST);
			}
			
			cancelledBookings = service.getAllCancelledBookings(custId, status, cancelledBookings, statusHandler);
			
		}catch(InvalidRequestException ex) {
			statusHandler.setErrorCode("400");
			statusHandler.setErrorMessage(ex.getMessage());
			cancelledBookings.setStatusHandler(statusHandler);
		}catch(Exception ex) {
			statusHandler.setErrorCode("500");
			statusHandler.setErrorMessage(ex.getMessage());
			cancelledBookings.setStatusHandler(statusHandler);
		}
		statusHandler.setErrorCode("200");
		statusHandler.setMessage(AppConstants.SUCCESS);
		cancelledBookings.setStatusHandler(statusHandler);
		logger.info("END : Get all Cancelled Bookings controller for custId :"+custId);
		return ResponseEntity.ok(cancelledBookings);
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
		if(null == vendorbooking ) {
			statusHandler.setStatusCode("400");
			statusHandler.setMessage(AppConstants.FAILED);
			vendorbooking.setStatusHandler(statusHandler);
		}else {
			statusHandler.setStatusCode("200");
			statusHandler.setMessage(AppConstants.SUCCESS);
			vendorbooking.setStatusHandler(statusHandler);
		}
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
		
		if( null == vendorResponse) {
			statusHandler.setStatusCode("400");
			statusHandler.setMessage(AppConstants.FAILED);
			vendorResponse.setStatusHandler(statusHandler);
		}else {
			statusHandler.setStatusCode("200");
			statusHandler.setMessage(AppConstants.SUCCESS);
			vendorResponse.setStatusHandler(statusHandler);
		}
		
		ResponseEntity<VendorBookingResponseDTO> response = new ResponseEntity<>(vendorResponse, HttpStatus.OK);
		logger.info("End : Cancel vendor Booking Controller :  "+vendorId+" "+bookingId);
		return response;
	}
	
	@GetMapping( value = "/vendor/cancel/getall")
	public ResponseEntity<VendorCancelledBookingResponse> getVendorCancelledBookings( @RequestParam Long vendorId, 
																	 @RequestParam String status) throws InvalidRequestException{
		logger.info("Start : Get all Cancelled Bookings controller for custId : "+vendorId);
		StatusHandler statusHandler = new StatusHandler();
		VendorCancelledBookingResponse cancelledBookings = new VendorCancelledBookingResponse();
		
		if( null == vendorId ||  status.isEmpty() || null == status) {
			throw new InvalidRequestException(AppConstants.INVALID_REQUEST);
		}
		
		cancelledBookings = service.getVendorCancelledBookings(vendorId, status, cancelledBookings, statusHandler);
		
		statusHandler.setStatusCode("200");
		statusHandler.setMessage(AppConstants.SUCCESS);
		cancelledBookings.setStatusHandler(statusHandler);
		
		logger.info("END : Get all Cancelled Bookings controller for custId :"+vendorId);
		return ResponseEntity.ok(cancelledBookings);
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
		
		if( null == vendorBookings ) {
			statusHandler.setStatusCode("400");
			statusHandler.setMessage(AppConstants.FAILED);
			vendorBookings.setStatusHandler(statusHandler);
			
		}else {
			statusHandler.setStatusCode("200");
			statusHandler.setMessage(AppConstants.SUCCESS);
			vendorBookings.setStatusHandler(statusHandler);
		}
		
		ResponseEntity<VendorBookingResponseDTO> response = new ResponseEntity<>(vendorBookings, HttpStatus.OK);
		
		logger.info("End : Accept Booking Controller : "+vendorId+" "+bookingId);
		return response;
	}
	
	
	
	@GetMapping( value = "/items/{estCategory}")
	public ResponseEntity<HouseholdItemsResponse> getHouseHoldItems(@PathVariable String estCategory){
		logger.info("Start : get ONE BHK Household Items : "+estCategory);
		StatusHandler statusHandler = new StatusHandler();
		HouseholdItemsResponse itemResponse = new HouseholdItemsResponse();
		ResponseEntity<HouseholdItemsResponse> response = null;
		try {
			if( null == estCategory || estCategory.isEmpty()) {
				throw new InvalidRequestException(AppConstants.INVALID_REQUEST);
			}
			
			itemResponse = service.getHouseHoldItems(estCategory, itemResponse, statusHandler);
			response = new ResponseEntity<>(itemResponse, HttpStatus.OK);
		}catch(InvalidRequestException ex) {
			statusHandler.setErrorCode("400");
			statusHandler.setErrorMessage(ex.getMessage());
			itemResponse.setStatusHandler(statusHandler);
		}catch(Exception ex) {
			statusHandler.setErrorCode("500");
			statusHandler.setErrorMessage(AppConstants.INTERNAL_SERVER_ERROR);
			itemResponse.setStatusHandler(statusHandler);
		}
		statusHandler.setStatusCode("200");
		statusHandler.setMessage(AppConstants.SUCCESS);
		itemResponse.setStatusHandler(statusHandler);
		logger.info("END : get ONE BHK Household Items "+estCategory);
		return response;
	}
	
	
	@GetMapping( value = "/vendorprofile/{vendorId}")
	public Vendor getVendorProfile(@PathVariable long vendorId) {
		Vendor vendor = service.getvendorProfile(vendorId);
		return vendor;
	}
	
	@GetMapping( value = "/vendor/vendorestimates")
	public ResponseEntity<VendorEstimateResponse> getVendorEstimates( @RequestBody VendorEstimateRequest request) {
		logger.info("Start : Get Vendor Estimates : "+request);
		StatusHandler statusHandler = new StatusHandler();
		VendorEstimateResponse vendorEstimatesResponse = new VendorEstimateResponse();
		vendorEstimatesResponse = service.getvendorEstimates(request, vendorEstimatesResponse, statusHandler);
		ResponseEntity<VendorEstimateResponse> response = new ResponseEntity<>(vendorEstimatesResponse, HttpStatus.OK);
		logger.info("END : Get Vendor Estimates : ");
		return response;
	}
	
}















