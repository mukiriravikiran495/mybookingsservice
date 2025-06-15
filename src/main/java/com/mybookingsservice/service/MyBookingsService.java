package com.mybookingsservice.service;

import java.util.List;

import com.mybookingsservice.domain.CustCancelResponseDTO;
import com.mybookingsservice.domain.VendorCancelResponseDTO;
import com.mybookingsservice.domain.VendorCancelledBookingResponse;
import com.mybookingsservice.domain.CustCancelledBookingResponseDTO;
import com.mybookingsservice.domain.CustomerBookingResponseDTO;
import com.mybookingsservice.domain.MyBookingsDTO;
import com.mybookingsservice.domain.VendorBookingResponseDTO;
import com.mybookingsservice.exceptions.StatusHandler;

public interface MyBookingsService {

	
	List<MyBookingsDTO> getall();

	
	List<CustomerBookingResponseDTO> getBookingsByCustomerId(Long custId);
	
	CustomerBookingResponseDTO getBookingsByBookingId(Long custId, Long bookingId, StatusHandler statusHandler,
			CustomerBookingResponseDTO customerBookingResponse);
	
	CustomerBookingResponseDTO cancelBookingById(Long custId, Long bookingId, 
			CustomerBookingResponseDTO cancelResponse, StatusHandler statusHandler);


	List<CustCancelledBookingResponseDTO> getAllCancelledBookings(Long custId, String status,
			CustCancelledBookingResponseDTO cancelledBookings, StatusHandler statusHandler);
	
	List<VendorBookingResponseDTO> getBookingByVendorId(long vendorId);


	VendorBookingResponseDTO getBookingByVendorIdbookingId(Long vendorId, Long bookingId,
			VendorBookingResponseDTO vendorbooking, StatusHandler statusHandler);


	VendorBookingResponseDTO cancelVendorBooking(Long vendorId, Long bookingId, VendorBookingResponseDTO vendorResponse,
			StatusHandler statusHandler);


	List<VendorCancelledBookingResponse> getVendorCancelledBookings(Long vendorId, String status,
			VendorCancelledBookingResponse cancelledBookings, StatusHandler statusHandler);


	VendorBookingResponseDTO acceptBpooking(Long vendorId, Long bookingId, VendorBookingResponseDTO vendorBookings,
			StatusHandler statusHandler);

}
