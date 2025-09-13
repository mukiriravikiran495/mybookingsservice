package com.mybookingsservice.controller;

import java.lang.invoke.MethodHandles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mybookingsservice.constants.AppConstants;
import com.mybookingsservice.domain.BookingTransactionDTO;
import com.mybookingsservice.domain.BookingTransactionResponse;
import com.mybookingsservice.domain.ConfirmBookingRequest;
import com.mybookingsservice.domain.ConfirmBookingResponse;
import com.mybookingsservice.domain.HouseholdItemsResponse;
import com.mybookingsservice.domain.MyBookingsRequest;
import com.mybookingsservice.domain.MyBookingsResponse;
import com.mybookingsservice.domain.SelectPackersAndMoversRequest;
import com.mybookingsservice.domain.SelectPackersAndMoversResponse;
import com.mybookingsservice.domain.VendorBookingResponseDTO;
import com.mybookingsservice.exceptions.InvalidRequestException;
import com.mybookingsservice.exceptions.StatusHandler;
import com.mybookingsservice.repository.CustomerRepository;
import com.mybookingsservice.service.MyBookingsService;

@RestController
@RequestMapping(path = "/v1/api/bookings")
public class MyBookingsController {
	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	private final CustomerRepository customerRepository;
	private final MyBookingsService service;

	@Autowired
	public MyBookingsController(MyBookingsService service, CustomerRepository customerRepository) {
		this.service = service;
		this.customerRepository = customerRepository;
	}

//	/*
//	 * Below All API's are related to Customer Bookings
//	 */

	@PostMapping(value = "/save/vehicle/bookings")
	public ResponseEntity<MyBookingsResponse> saveVehicleBookings(@RequestBody MyBookingsRequest mybookingsDTO,
			@RequestHeader("Authorization") String accessToken, @RequestHeader("APPID") String appId) {
		logger.info("Start : Create vehicle booking Controller : " + mybookingsDTO);
		StatusHandler statusHandler = new StatusHandler();
		MyBookingsResponse myBookingsResponse = new MyBookingsResponse();
		String token = accessToken.replace("Bearer ", "");
		myBookingsResponse = service.saveVehicleBookings(mybookingsDTO, myBookingsResponse, token, appId,
				statusHandler);
		ResponseEntity<MyBookingsResponse> response = new ResponseEntity<MyBookingsResponse>(myBookingsResponse,
				HttpStatus.OK);
		logger.info("END : Create vehicle booking Controller : ");
		return response;
	}

	@PostMapping(value = "/save/pandm/bookings")
	public ResponseEntity<HouseholdItemsResponse> savePandMBookings(@RequestBody MyBookingsRequest mybookingsDTO,
			@RequestHeader("Authorization") String accessToken, @RequestHeader("APPID") String appId) {
		logger.info(" Start : Save pandm Booking Type Controller : " + mybookingsDTO);
		StatusHandler statusHandler = new StatusHandler();
		String token = accessToken.replace("Bearer ", "");
		HouseholdItemsResponse response = new HouseholdItemsResponse();
		System.out.println(mybookingsDTO.getMyBookingsDTO().getBookingDate());
		response = service.savePandMBookings(mybookingsDTO, response, token, appId, statusHandler);
		ResponseEntity<HouseholdItemsResponse> houseHoldResponse = new ResponseEntity<>(response, HttpStatus.OK);

		logger.info("End : Save pandm Booking type Controller ");
		return houseHoldResponse;
	}

	@PostMapping(value = "/truck/bookings/confirm")
	public ResponseEntity<ConfirmBookingResponse> confirmTruckBooking(
			@RequestBody ConfirmBookingRequest confirmBookingRequest,
			@RequestHeader("Authorization") String accessToken, @RequestHeader("APPID") String appId) {
		ConfirmBookingResponse confirmBookingresponse = new ConfirmBookingResponse();
		logger.info(" Start : confirm Booking Controller : " + confirmBookingRequest);
		StatusHandler statusHandler = new StatusHandler();
		String token = accessToken.replace("Bearer ", "");
		confirmBookingresponse = service.confirmTruckBooking(confirmBookingRequest, confirmBookingresponse, token,
				appId, statusHandler);

		logger.info(" End : confirm Booking Controller : " + confirmBookingresponse);
		ResponseEntity<ConfirmBookingResponse> response = new ResponseEntity<>(confirmBookingresponse, HttpStatus.OK);
		return response;
	}
	
	@PostMapping(value = "/pandm/bookings/select")
	public ResponseEntity<SelectPackersAndMoversResponse> selectPandMBooking(
																@RequestBody SelectPackersAndMoversRequest selectPackersAndMoversRequest,
																@RequestHeader("Authorization") String accessToken, 
																@RequestHeader("APPID") String appId) {
		SelectPackersAndMoversResponse selectPackersAndMoversResponse = new SelectPackersAndMoversResponse();
		logger.info(" Start : confirm Booking Controller : " + selectPackersAndMoversRequest);
		StatusHandler statusHandler = new StatusHandler();
		String token = accessToken.replace("Bearer ", "");
		selectPackersAndMoversResponse = service.selectPandMBooking(selectPackersAndMoversRequest, selectPackersAndMoversResponse, token,
				appId, statusHandler);

		logger.info(" End : confirm Booking Controller : " + selectPackersAndMoversResponse);
		ResponseEntity<SelectPackersAndMoversResponse> response = new ResponseEntity<>(selectPackersAndMoversResponse, HttpStatus.OK);
		return response;
	}

	@PostMapping(value = "/truck/update/pickup/{vendorId}/{bookingId}/{custId}/{otp}")
	public ResponseEntity<VendorBookingResponseDTO> updateTruckPickup(@PathVariable Long vendorId,
			@PathVariable Long bookingId, @PathVariable Long custId, @PathVariable String otp,
			@RequestHeader("Authorization") String accessToken, @RequestHeader("APPID") String appId) {
		logger.info("Start : Update vendor pickup controller : " + vendorId + " : " + bookingId + " : " + custId);
		StatusHandler statushandler = new StatusHandler();
		String token = accessToken.replace("Bearer ", "");
		VendorBookingResponseDTO vendorBooking = new VendorBookingResponseDTO();
		try {
			if (null == vendorId || null == bookingId || null == custId) {
				throw new InvalidRequestException(AppConstants.INVALID_REQUEST);
			}
			vendorBooking = service.updatePickup(vendorId, bookingId, custId, otp, vendorBooking, statushandler, token,
					appId);

			statushandler.setStatusCode("200");
			statushandler.setMessage(AppConstants.SUCCESS);
			vendorBooking.setStatusHandler(statushandler);
		} catch (InvalidRequestException ex) {
			statushandler.setStatusCode("400");
			statushandler.setError(ex.getMessage());
			vendorBooking.setStatusHandler(statushandler);
		} catch (Exception ex) {
			statushandler.setStatusCode("500");
			statushandler.setError(ex.getMessage());
			vendorBooking.setStatusHandler(statushandler);
		}

		logger.info("End : Update vendor pickup controller : " + vendorId);
		return ResponseEntity.ok(vendorBooking);
	}

	@PostMapping(value = "/truck/update/customerlocated/{vendorId}/{bookingId}/{custId}")
	public ResponseEntity<VendorBookingResponseDTO> customerLocated(@PathVariable Long vendorId,
			@PathVariable Long bookingId, @PathVariable Long custId, @RequestHeader("Authorization") String accessToken,
			@RequestHeader("APPID") String appId) {
		logger.info("Start : Update vendor pickup controller : " + vendorId + " : " + bookingId + " : " + custId);
		StatusHandler statushandler = new StatusHandler();
		String token = accessToken.replace("Bearer ", "");
		VendorBookingResponseDTO vendorBooking = new VendorBookingResponseDTO();
		try {
			if (null == vendorId || null == bookingId || null == custId) {
				throw new InvalidRequestException(AppConstants.INVALID_REQUEST);
			}
			vendorBooking = service.customerLocated(vendorId, bookingId, custId, vendorBooking, statushandler, token,
					appId);

			statushandler.setStatusCode("200");
			statushandler.setMessage(AppConstants.SUCCESS);
			vendorBooking.setStatusHandler(statushandler);
		} catch (InvalidRequestException ex) {
			statushandler.setStatusCode("400");
			statushandler.setError(ex.getMessage());
			vendorBooking.setStatusHandler(statushandler);
		} catch (Exception ex) {
			statushandler.setStatusCode("500");
			statushandler.setError(ex.getMessage());
			vendorBooking.setStatusHandler(statushandler);
		}

		logger.info("End : Update vendor pickup controller : " + vendorId);
		return ResponseEntity.ok(vendorBooking);
	}

	@PostMapping(value = "/truck/update/reacheddroplocation/{vendorId}/{bookingId}/{custId}")
	public ResponseEntity<VendorBookingResponseDTO> reachedDropLocation(@PathVariable Long vendorId,
			@PathVariable Long bookingId, @PathVariable Long custId, @RequestHeader("Authorization") String accessToken,
			@RequestHeader("APPID") String appId) {
		logger.info("Start : Update vendor pickup controller : " + vendorId + " : " + bookingId + " : " + custId);
		StatusHandler statushandler = new StatusHandler();
		String token = accessToken.replace("Bearer ", "");
		VendorBookingResponseDTO vendorBooking = new VendorBookingResponseDTO();
		try {
			if (null == vendorId || null == bookingId || null == custId) {
				throw new InvalidRequestException(AppConstants.INVALID_REQUEST);
			}
			vendorBooking = service.reachedDropLocation(vendorId, bookingId, custId, vendorBooking, statushandler,
					token, appId);

			statushandler.setStatusCode("200");
			statushandler.setMessage(AppConstants.SUCCESS);
			vendorBooking.setStatusHandler(statushandler);
		} catch (InvalidRequestException ex) {
			statushandler.setStatusCode("400");
			statushandler.setError(ex.getMessage());
			vendorBooking.setStatusHandler(statushandler);
		} catch (Exception ex) {
			statushandler.setStatusCode("500");
			statushandler.setError(ex.getMessage());
			vendorBooking.setStatusHandler(statushandler);
		}

		logger.info("End : Update vendor pickup controller : " + vendorId);
		return ResponseEntity.ok(vendorBooking);
	}

	@PostMapping(value = "/truck/update/drop/{vendorId}/{bookingId}/{custId}/{otp}")
	public ResponseEntity<VendorBookingResponseDTO> updateTruckDrop(@PathVariable Long vendorId,
			@PathVariable Long bookingId, @PathVariable Long custId, @PathVariable String otp,
			@RequestHeader("Authorization") String accessToken, @RequestHeader("APPID") String appId) {
		logger.info("Start : Update vendor drop controller : " + vendorId + " : " + bookingId + " : " + custId);
		StatusHandler statushandler = new StatusHandler();
		VendorBookingResponseDTO vendorBooking = new VendorBookingResponseDTO();
		String token = accessToken.replace("Bearer ", "");
		try {
			if (null == vendorId || null == bookingId || null == custId) {
				throw new InvalidRequestException(AppConstants.INVALID_REQUEST);
			}
			vendorBooking = service.updateDrop(vendorId, bookingId, custId, otp, vendorBooking, statushandler, token,
					appId);

			statushandler.setStatusCode("200");
			statushandler.setMessage(AppConstants.SUCCESS);
			vendorBooking.setStatusHandler(statushandler);
		} catch (InvalidRequestException ex) {
			statushandler.setStatusCode("400");
			statushandler.setError(AppConstants.INVALID_REQUEST);
			vendorBooking.setStatusHandler(statushandler);
		} catch (Exception ex) {
			statushandler.setStatusCode("500");
			statushandler.setError(ex.getMessage());
			vendorBooking.setStatusHandler(statushandler);
		}

		logger.info("End : Update vendor drop controller : " + vendorId);
		return ResponseEntity.ok(vendorBooking);
	}

	@PostMapping(value = "/transaction/create")
	public ResponseEntity<BookingTransactionResponse> createTransaction(@RequestBody BookingTransactionDTO dto) {

		return null;
	}

}
