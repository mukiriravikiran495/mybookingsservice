package com.mybookingsservice.controller;

import java.lang.invoke.MethodHandles;

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
import com.mybookingsservice.domain.AcceptBookingRequest;
import com.mybookingsservice.domain.AcceptBookingResponse;
import com.mybookingsservice.domain.BookingTypeRequest;
import com.mybookingsservice.domain.CustBookingResponse;
import com.mybookingsservice.domain.CustomerBookingResponseDTO;
import com.mybookingsservice.domain.HouseholdItemsResponse;
import com.mybookingsservice.domain.MyBookingsRequestDTO;
import com.mybookingsservice.domain.MyBookingsResponseDTO;
import com.mybookingsservice.domain.VendorBookingResponseDTO;
import com.mybookingsservice.domain.VendorBookingsDTO;
import com.mybookingsservice.domain.VendorEstimateRequest;
import com.mybookingsservice.domain.VendorEstimateResponse;
import com.mybookingsservice.exceptions.InvalidRequestException;
import com.mybookingsservice.exceptions.StatusHandler;
import com.mybookingsservice.service.MyBookingsService;

import jakarta.validation.Valid;

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
	
//	@GetMapping( value = "/getall")
//	public List<MyBookingsDTO> getbookings(){
//		return service.getall();
//	}
//	
//	/* 
//	 	 Below All API's are related to Customer Bookings 
//	 */
//	
	@GetMapping( value = "/customer/{custId}/{bookingId}")
	public ResponseEntity<CustBookingResponse> getBookingsByBookingId(@PathVariable Long custId, 
																	  @PathVariable Long bookingId) {
		logger.info("START : Get Bookings By ID Controller : "+custId+" : "+bookingId);
		StatusHandler statusHandler = new StatusHandler();
		CustBookingResponse custBookingResponse = new CustBookingResponse();
		
		try {
			if( null == custId || null == bookingId ) {
				throw new InvalidRequestException(AppConstants.INVALID_REQUEST);
			}
			custBookingResponse = service.getBookingsByBookingId(custId, bookingId, statusHandler, custBookingResponse);
			
			logger.info("END : Get Bookings By ID Controller : "+custBookingResponse);
			statusHandler.setErrorCode("200");
			statusHandler.setErrorMessage(AppConstants.SUCCESS);
			custBookingResponse.setStatusHandler(statusHandler);
			ResponseEntity<CustBookingResponse> response = new ResponseEntity<>(custBookingResponse, HttpStatus.OK);
			return response;
			
		}catch(InvalidRequestException ex) {
			statusHandler.setErrorCode("400");
			statusHandler.setErrorMessage(AppConstants.INVALID_REQUEST);
			custBookingResponse.setStatusHandler(statusHandler);
			return new ResponseEntity<>(custBookingResponse, HttpStatus.BAD_REQUEST);
		}catch(Exception e) {
			statusHandler.setErrorCode("400");
			statusHandler.setErrorMessage(e.getMessage());
			custBookingResponse.setStatusHandler(statusHandler);
			return new ResponseEntity<>(custBookingResponse, HttpStatus.BAD_REQUEST);
		}
		
	}
//	
	@PostMapping( value = "/customer/cancel/{custId}/{bookingId}")
	public ResponseEntity<CustBookingResponse> cancelBookingById( @PathVariable Long custId, @PathVariable Long bookingId)  {
		logger.info("START Cancel Booking By Id Controller : "+custId+" "+bookingId);
		StatusHandler statusHandler = new StatusHandler();
		CustBookingResponse cancelResponse = new CustBookingResponse();
		
		try {
			if( null == custId || null == bookingId ) {
				throw new InvalidRequestException(AppConstants.INVALID_REQUEST);
			}
			cancelResponse = service.cancelBookingById(custId, bookingId,  cancelResponse, statusHandler);
			statusHandler.setErrorCode("200");
			statusHandler.setErrorMessage(AppConstants.SUCCESS);
			cancelResponse.setStatusHandler(statusHandler);
		}catch(InvalidRequestException ex) {
			statusHandler.setErrorCode("400");
			statusHandler.setErrorMessage(AppConstants.INVALID_REQUEST);
			cancelResponse.setStatusHandler(statusHandler);
		}catch(Exception ex) {
			statusHandler.setErrorCode("500");
			statusHandler.setErrorMessage(ex.getMessage());
			cancelResponse.setStatusHandler(statusHandler);
		}
		ResponseEntity<CustBookingResponse> response = new ResponseEntity<>(cancelResponse, HttpStatus.OK);
		logger.info("End : cancel Booking by Id controller : "+cancelResponse);
		return response;
	}
//	
	@GetMapping("/customer/{custId}")
    public ResponseEntity<CustomerBookingResponseDTO> getBookingsByCustomer(@PathVariable Long custId) throws InvalidRequestException {
		logger.info("START Customer Bookings Controller : ");
		StatusHandler statusHandler = new StatusHandler();
		CustomerBookingResponseDTO response = new CustomerBookingResponseDTO();
		try {
			if(null == custId) {
				throw new InvalidRequestException(AppConstants.INVALID_REQUEST);
			}
			response = service.getBookingsByCustomerId(custId, response);
			statusHandler.setErrorCode("200");
			statusHandler.setErrorMessage(AppConstants.SUCCESS);
			response.setStatusHandler(statusHandler);
		}catch(InvalidRequestException ex) {
			statusHandler.setErrorCode("400");
			statusHandler.setErrorMessage(ex.getMessage());
			response.setStatusHandler(statusHandler);
		}catch(Exception ex) {
			statusHandler.setErrorCode("500");
			statusHandler.setErrorMessage(AppConstants.INTERNAL_SERVER_ERROR);
			response.setStatusHandler(statusHandler);
		}
		
        logger.info("END : Customer Bookings Controller : ");
        return ResponseEntity.ok(response);
    }
	
	
	@GetMapping( value = "/customer/cancel/getall")
	public ResponseEntity<CustomerBookingResponseDTO> getCustCancelledBookings( @RequestParam Long custId, 
																	 @RequestParam String status) throws InvalidRequestException{
		logger.info("Start : Get all Cancelled Bookings controller for custId : "+custId);
		StatusHandler statusHandler = new StatusHandler();
		CustomerBookingResponseDTO cancelledBookings = new CustomerBookingResponseDTO();
		try {
			if( null == custId ||  status.isEmpty() || null == status) {
				throw new InvalidRequestException(AppConstants.INVALID_REQUEST);
			}
			
			cancelledBookings = service.getAllCancelledBookings(custId, status, cancelledBookings, statusHandler);
			statusHandler.setErrorCode("200");
			statusHandler.setMessage(AppConstants.SUCCESS);
			cancelledBookings.setStatusHandler(statusHandler);
		}catch(InvalidRequestException ex) {
			statusHandler.setErrorCode("400");
			statusHandler.setErrorMessage(AppConstants.INVALID_REQUEST);
			cancelledBookings.setStatusHandler(statusHandler);
		}catch(Exception ex) {
			statusHandler.setErrorCode("500");
			statusHandler.setErrorMessage(ex.getMessage());
			cancelledBookings.setStatusHandler(statusHandler);
		}
		
		logger.info("END : Get all Cancelled Bookings controller for custId :"+custId);
		return ResponseEntity.ok(cancelledBookings);
	}
//	
//	
//	
//	
//	
//	/*
//	 * Below All API's are related to Customer Bookings
//	 */
//	
	@GetMapping(value = "/vendor/{vendorId}")
	public ResponseEntity<VendorBookingsDTO> getBookingsByVendorId(@PathVariable Long vendorId) {
		logger.info("STRAT : Fetch all Vendor Bookings : ");
		VendorBookingsDTO  vendor = new VendorBookingsDTO();
		StatusHandler statusHandler = new StatusHandler();
		try {
			if(null == vendorId) {
				throw new InvalidRequestException(AppConstants.INVALID_REQUEST);
			}
			
			vendor = service.getBookingByVendorId(vendorId, vendor, statusHandler);
			statusHandler.setErrorCode("200");
			statusHandler.setMessage(AppConstants.SUCCESS);
			vendor.setStatusHandler(statusHandler);
			logger.info("END : Fetch all Vendor Bookings : " + vendor);
			
		}catch(InvalidRequestException ex) {
			statusHandler.setErrorCode("400");
			statusHandler.setMessage(AppConstants.INVALID_REQUEST);
			vendor.setStatusHandler(statusHandler);
		}catch(Exception ex) {
			statusHandler.setErrorCode("500");
			statusHandler.setMessage(ex.getMessage());
			vendor.setStatusHandler(statusHandler);
		}
		return ResponseEntity.ok(vendor);
	}
	
	@GetMapping( value = "/vendor/{vendorId}/{bookingId}")
	public ResponseEntity<VendorBookingResponseDTO> getBookingByVendorIdbookingId(@PathVariable Long vendorId, @PathVariable Long bookingId) throws InvalidRequestException{
		logger.info("Start : get booking details by vendorId and bookingId controller : "+vendorId+" "+bookingId);
		StatusHandler statusHandler = new StatusHandler();
		VendorBookingResponseDTO vendorbooking = new VendorBookingResponseDTO();
		try {
			if( null == vendorId || null == bookingId) {
				throw new InvalidRequestException(AppConstants.INVALID_REQUEST);
			}
			vendorbooking = service.getBookingByVendorIdbookingId(vendorId, bookingId, vendorbooking, statusHandler);
			statusHandler.setStatusCode("200");
			statusHandler.setMessage(AppConstants.SUCCESS);
			vendorbooking.setStatusHandler(statusHandler);
		}catch(InvalidRequestException ex) {
			statusHandler.setStatusCode("400");
			statusHandler.setMessage(AppConstants.INVALID_REQUEST);
			vendorbooking.setStatusHandler(statusHandler);
		}catch(Exception ex) {
			statusHandler.setStatusCode("500");
			statusHandler.setMessage(ex.getMessage());
			vendorbooking.setStatusHandler(statusHandler);
		}
		
		ResponseEntity<VendorBookingResponseDTO> response = new ResponseEntity<>(vendorbooking, HttpStatus.OK);
		
		logger.info("End : get booking details by vendorId and bookingId controller : "+vendorId+" "+bookingId);
		return response;
	}
//	
	@PostMapping( value = "/vendor/cancel/{vendorId}/{bookingId}")
	public ResponseEntity<VendorBookingResponseDTO> cancelVendorBooking(@PathVariable Long vendorId, @PathVariable Long bookingId) throws InvalidRequestException{
		logger.info("Start : Cancel vendor Booking Controller : "+vendorId+" "+bookingId);
		StatusHandler statusHandler = new StatusHandler();
		VendorBookingResponseDTO vendorResponse = new VendorBookingResponseDTO();
		
		try {
			if(null == vendorId || null == bookingId) {
				throw new InvalidRequestException(AppConstants.INVALID_REQUEST);
			}
			vendorResponse = service.cancelVendorBooking(vendorId, bookingId, vendorResponse, statusHandler);
			statusHandler.setStatusCode("200");
			statusHandler.setMessage(AppConstants.SUCCESS);
			vendorResponse.setStatusHandler(statusHandler);
			
		}catch(InvalidRequestException ex) {
			statusHandler.setStatusCode("400");
			statusHandler.setMessage(AppConstants.INVALID_REQUEST);
			vendorResponse.setStatusHandler(statusHandler);
			return ResponseEntity.ok(vendorResponse);
		}catch(Exception ex) {
			statusHandler.setStatusCode("500");
			statusHandler.setMessage(ex.getMessage());
			vendorResponse.setStatusHandler(statusHandler);
			return ResponseEntity.ok(vendorResponse);
		}
		
		logger.info("End : Cancel vendor Booking Controller :  "+vendorId+" "+bookingId);
		return ResponseEntity.ok(vendorResponse);
	}
	
	@GetMapping( value = "/vendor/cancel/getall")
	public ResponseEntity<VendorBookingsDTO> getVendorCancelledBookings( @RequestParam Long vendorId, 
																	 @RequestParam String status){
		logger.info("Start : Get all Cancelled Bookings controller for custId : "+vendorId);
		StatusHandler statusHandler = new StatusHandler();
		VendorBookingsDTO cancelledBookings = new VendorBookingsDTO();
		try {
			if( null == vendorId ||  status.isEmpty() || null == status) {
				throw new InvalidRequestException(AppConstants.INVALID_REQUEST);
			}
			cancelledBookings = service.getVendorCancelledBookings(vendorId, status, cancelledBookings, statusHandler);
			statusHandler.setStatusCode("200");
			statusHandler.setMessage(AppConstants.SUCCESS);
			cancelledBookings.setStatusHandler(statusHandler);
		}catch(InvalidRequestException ex) {
			statusHandler.setStatusCode("400");
			statusHandler.setMessage(AppConstants.INVALID_REQUEST);
			cancelledBookings.setStatusHandler(statusHandler);
			return ResponseEntity.ok(cancelledBookings);
		}catch(Exception ex) {
			statusHandler.setStatusCode("500");
			statusHandler.setMessage(ex.getMessage());
			cancelledBookings.setStatusHandler(statusHandler);
			return ResponseEntity.ok(cancelledBookings);
		}
		
		logger.info("END : Get all Cancelled Bookings controller for custId :"+vendorId);
		return ResponseEntity.ok(cancelledBookings);
	}
//	
	@PostMapping( value = "/vendor/accept")
	public ResponseEntity<AcceptBookingResponse> acceptBooking( @RequestBody AcceptBookingRequest acceptBookingRequest) throws InvalidRequestException{
		logger.info("Start : Accept booking Controller : "+acceptBookingRequest);
		StatusHandler statusHandler = new StatusHandler();
		AcceptBookingResponse acceptBookingresponse = new AcceptBookingResponse();
		try {
			if(null == acceptBookingRequest.getBookingId() || null == acceptBookingRequest.getCustId() || null == acceptBookingRequest.getVendorId()) {
				throw new InvalidRequestException(AppConstants.INVALID_REQUEST);
			}
			acceptBookingresponse =  service.acceptBooking(acceptBookingRequest, acceptBookingresponse, statusHandler);
			
			statusHandler.setStatusCode("200");
			statusHandler.setMessage(AppConstants.ACCEPTED);
			acceptBookingresponse.setStatusHandler(statusHandler);
			
			
		}catch(InvalidRequestException ex) {
			statusHandler.setErrorCode("400");
			statusHandler.setErrorMessage(ex.getMessage());
			acceptBookingresponse.setStatusHandler(statusHandler);
		}catch(Exception ex) {
			statusHandler.setErrorCode("500");
			statusHandler.setErrorMessage(AppConstants.INTERNAL_SERVER_ERROR);
			acceptBookingresponse.setStatusHandler(statusHandler);
		}
		
		logger.info("End : Accept Booking Controller : ");
		return new ResponseEntity<>(acceptBookingresponse, HttpStatus.OK);
	}
//	
//	
//	
//	@GetMapping( value = "/items/{estCategory}")
//	public ResponseEntity<HouseholdItemsResponse> getHouseHoldItems(@PathVariable String estCategory){
//		logger.info("Start : get ONE BHK Household Items : "+estCategory);
//		StatusHandler statusHandler = new StatusHandler();
//		HouseholdItemsResponse itemResponse = new HouseholdItemsResponse();
//		ResponseEntity<HouseholdItemsResponse> response = null;
//		try {
//			if( null == estCategory || estCategory.isEmpty()) {
//				throw new InvalidRequestException(AppConstants.INVALID_REQUEST);
//			}
//			
//			itemResponse = service.getHouseHoldItems(estCategory, itemResponse, statusHandler);
//			response = new ResponseEntity<>(itemResponse, HttpStatus.OK);
//		}catch(InvalidRequestException ex) {
//			statusHandler.setErrorCode("400");
//			statusHandler.setErrorMessage(ex.getMessage());
//			itemResponse.setStatusHandler(statusHandler);
//		}catch(Exception ex) {
//			statusHandler.setErrorCode("500");
//			statusHandler.setErrorMessage(AppConstants.INTERNAL_SERVER_ERROR);
//			itemResponse.setStatusHandler(statusHandler);
//		}
//		statusHandler.setStatusCode("200");
//		statusHandler.setMessage(AppConstants.SUCCESS);
//		itemResponse.setStatusHandler(statusHandler);
//		logger.info("END : get ONE BHK Household Items "+estCategory);
//		return response;
//	}
//	
//	
//	@GetMapping( value = "/vendorprofile/{vendorId}")
//	public Vendor getVendorProfile(@PathVariable long vendorId) {
//		Vendor vendor = service.getvendorProfile(vendorId);
//		return vendor;
//	}
	
	@PostMapping( value = "/vendor/vendorestimates")
	public ResponseEntity<VendorEstimateResponse> getVendorEstimates( @RequestBody VendorEstimateRequest request) {
		logger.info("Start : Get Vendor Estimates : "+request);
		StatusHandler statusHandler = new StatusHandler();
		VendorEstimateResponse vendorEstimatesResponse = new VendorEstimateResponse();
		vendorEstimatesResponse = service.getvendorEstimates(request, vendorEstimatesResponse, statusHandler);
		ResponseEntity<VendorEstimateResponse> response = new ResponseEntity<>(vendorEstimatesResponse, HttpStatus.OK);
		logger.info("END : Get Vendor Estimates : ");
		return response;
	}
	
	@PostMapping( value = "/createbookings")
	public ResponseEntity<MyBookingsResponseDTO> createBookings(@RequestBody MyBookingsRequestDTO mybookingsDTO){
		logger.info("Start : Create booking Controller : "+mybookingsDTO);
		StatusHandler statusHandler = new StatusHandler();
		MyBookingsResponseDTO myBookingsResponse = new MyBookingsResponseDTO();
		myBookingsResponse = service.createBookings(mybookingsDTO, myBookingsResponse, statusHandler);
		ResponseEntity<MyBookingsResponseDTO> response = new ResponseEntity<MyBookingsResponseDTO>(myBookingsResponse, HttpStatus.OK);
		logger.info("END : Create booking Controller : ");
		return response;
	}
	
	@PostMapping( value = "/save/bookingtype")
	public ResponseEntity<HouseholdItemsResponse> savebookingType( @Valid @RequestBody BookingTypeRequest request){
		logger.info(" Start : Save Booking Type Controller : "+request);
		StatusHandler statusHandler = new StatusHandler();
		HouseholdItemsResponse response = new HouseholdItemsResponse();
		System.out.println(request.getBookingDate());
		response = service.savebookingType(request, response, statusHandler);
		ResponseEntity<HouseholdItemsResponse> houseHoldResponse = new ResponseEntity<>(response, HttpStatus.OK);
		
		logger.info("End : Save Booking type Controller ");
		return houseHoldResponse;
	}
	
	@PostMapping( value = "/vendor/update/pickup/{vendorId}/{bookingId}")
	public ResponseEntity<VendorBookingResponseDTO> updatePickup( @PathVariable Long vendorId, @PathVariable Long bookingId){
		logger.info("Start : Update vendor pickup controller : "+vendorId);
		StatusHandler statushandler = new StatusHandler();
		VendorBookingResponseDTO vendorBooking = new VendorBookingResponseDTO();
		try {
			if( null == vendorId || null == bookingId) {
				throw new InvalidRequestException(AppConstants.INVALID_REQUEST);
			}
			vendorBooking = service.updatePickup(vendorId, bookingId, vendorBooking, statushandler);
			
			
			statushandler.setStatusCode("200");
			statushandler.setMessage(AppConstants.SUCCESS);
			vendorBooking.setStatusHandler(statushandler);
		}catch(InvalidRequestException ex) {
			statushandler.setStatusCode("400");
			statushandler.setMessage(AppConstants.INVALID_REQUEST);
			vendorBooking.setStatusHandler(statushandler);
		}catch(Exception ex) {
			statushandler.setStatusCode("500");
			statushandler.setMessage(ex.getMessage());
			vendorBooking.setStatusHandler(statushandler);
		}
		
		logger.info("End : Update vendor pickup controller : "+vendorId);
		return ResponseEntity.ok(vendorBooking);
	}
	
	@PostMapping( value = "/vendor/update/drop/{vendorId}/{bookingId}")
	public ResponseEntity<VendorBookingResponseDTO> updateDrop( @PathVariable Long vendorId, @PathVariable Long bookingId){
		logger.info("Start : Update vendor pickup controller : "+vendorId);
		StatusHandler statushandler = new StatusHandler();
		VendorBookingResponseDTO vendorBooking = new VendorBookingResponseDTO();
		try {
			if( null == vendorId || null == bookingId) {
				throw new InvalidRequestException(AppConstants.INVALID_REQUEST);
			}
			vendorBooking = service.updateDrop(vendorId, bookingId, vendorBooking, statushandler);
			
			
			statushandler.setStatusCode("200");
			statushandler.setMessage(AppConstants.SUCCESS);
			vendorBooking.setStatusHandler(statushandler);
		}catch(InvalidRequestException ex) {
			statushandler.setStatusCode("400");
			statushandler.setMessage(AppConstants.INVALID_REQUEST);
			vendorBooking.setStatusHandler(statushandler);
		}catch(Exception ex) {
			statushandler.setStatusCode("500");
			statushandler.setMessage(ex.getMessage());
			vendorBooking.setStatusHandler(statushandler);
		}
		
		logger.info("End : Update vendor pickup controller : "+vendorId);
		return ResponseEntity.ok(vendorBooking);
	}
	
	
	
}















