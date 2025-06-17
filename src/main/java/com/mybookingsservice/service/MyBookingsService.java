package com.mybookingsservice.service;

import java.util.List;

import com.mybookingsservice.domain.CustCancelledBookingResponse;
import com.mybookingsservice.domain.CustCancelledBookingResponseDTO;
import com.mybookingsservice.domain.CustomerBookingResponseDTO;
import com.mybookingsservice.domain.HouseholdItemsResponse;
import com.mybookingsservice.domain.MyBookingsDTO;
import com.mybookingsservice.domain.VendorBookingResponseDTO;
import com.mybookingsservice.domain.VendorCancelledBookingResponse;
import com.mybookingsservice.domain.VendorEstimateRequest;
import com.mybookingsservice.domain.VendorEstimateResponse;
import com.mybookingsservice.entity.Vendor;
import com.mybookingsservice.exceptions.StatusHandler;

public interface MyBookingsService {

	
	List<MyBookingsDTO> getall();

	
	List<CustomerBookingResponseDTO> getBookingsByCustomerId(Long custId);
	
	CustomerBookingResponseDTO getBookingsByBookingId(Long custId, Long bookingId, StatusHandler statusHandler,
			CustomerBookingResponseDTO customerBookingResponse);
	
	CustomerBookingResponseDTO cancelBookingById(Long custId, Long bookingId, 
			CustomerBookingResponseDTO cancelResponse, StatusHandler statusHandler);


	CustCancelledBookingResponse getAllCancelledBookings(Long custId, String status,
			CustCancelledBookingResponse cancelledBookings, StatusHandler statusHandler);
	
	List<VendorBookingResponseDTO> getBookingByVendorId(long vendorId);


	VendorBookingResponseDTO getBookingByVendorIdbookingId(Long vendorId, Long bookingId,
			VendorBookingResponseDTO vendorbooking, StatusHandler statusHandler);


	VendorBookingResponseDTO cancelVendorBooking(Long vendorId, Long bookingId, VendorBookingResponseDTO vendorResponse,
			StatusHandler statusHandler);


	VendorCancelledBookingResponse getVendorCancelledBookings(Long vendorId, String status,
			VendorCancelledBookingResponse cancelledBookings, StatusHandler statusHandler);


	VendorBookingResponseDTO acceptBpooking(Long vendorId, Long bookingId, VendorBookingResponseDTO vendorBookings,
			StatusHandler statusHandler);


	HouseholdItemsResponse getHouseHoldItems(String estcategory, HouseholdItemsResponse itemResponse,
			StatusHandler statusHandler);


	Vendor getvendorProfile(long vendorId);


	VendorEstimateResponse getvendorEstimates(VendorEstimateRequest request,
			VendorEstimateResponse vendorEstimatesResponse, StatusHandler statusHandler);

}
