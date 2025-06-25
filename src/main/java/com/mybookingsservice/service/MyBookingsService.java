package com.mybookingsservice.service;

import java.util.List;

import com.mybookingsservice.domain.AcceptBookingRequest;
import com.mybookingsservice.domain.AcceptBookingResponse;
import com.mybookingsservice.domain.BookingSummaryRequest;
import com.mybookingsservice.domain.BookingTransactionDTO;
import com.mybookingsservice.domain.BookingTransactionResponse;
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
import com.mybookingsservice.entity.Vendor;
import com.mybookingsservice.exceptions.StatusHandler;

public interface MyBookingsService {

	
	List<MyBookingsRequestDTO> getall();

	
	CustomerBookingResponseDTO getBookingsByCustomerId(Long custId, CustomerBookingResponseDTO response, StatusHandler statusHandler);
	
	CustBookingResponse getBookingsByBookingId(Long custId, Long bookingId, StatusHandler statusHandler,
			CustBookingResponse custBookingResponse);
	
	CustBookingResponse cancelBookingById(Long custId, Long bookingId, 
			CustBookingResponse cancelResponse, StatusHandler statusHandler);


	CustomerBookingResponseDTO getAllCancelledBookings(Long custId, String status,
			CustomerBookingResponseDTO cancelledBookings, StatusHandler statusHandler);
	
	VendorBookingsDTO getBookingByVendorId(Long vendorId, VendorBookingsDTO vendor, StatusHandler statusHandler);


	VendorBookingResponseDTO getBookingByVendorIdbookingId(Long vendorId, Long bookingId,
			VendorBookingResponseDTO vendorbooking, StatusHandler statusHandler);


	VendorBookingResponseDTO cancelVendorBooking(Long vendorId, Long bookingId, VendorBookingResponseDTO vendorResponse,
			StatusHandler statusHandler);


	VendorBookingsDTO getVendorCancelledBookings(Long vendorId, String status,
			VendorBookingsDTO cancelledBookings, StatusHandler statusHandler);


	AcceptBookingResponse acceptBooking(AcceptBookingRequest acceptBookingRequest, AcceptBookingResponse acceptBookingResponse,
			StatusHandler statusHandler);


	HouseholdItemsResponse getHouseHoldItems(String estcategory, HouseholdItemsResponse itemResponse,
			StatusHandler statusHandler);


	Vendor getvendorProfile(long vendorId);


	VendorEstimateResponse getvendorEstimates(VendorEstimateRequest request,
			VendorEstimateResponse vendorEstimatesResponse, StatusHandler statusHandler);


	MyBookingsResponseDTO createBookings(MyBookingsRequestDTO mybookingsDTO, MyBookingsResponseDTO myBookingsResponse,
			StatusHandler statusHandler);


	HouseholdItemsResponse savebookingType(BookingTypeRequest request, HouseholdItemsResponse response,
			StatusHandler statusHandler);


	VendorBookingResponseDTO updatePickup(Long vendorId, Long bookingId, VendorBookingResponseDTO vendorBooking,
			StatusHandler statushandler);


	VendorBookingResponseDTO updateDrop(Long vendorId, Long bookingId, VendorBookingResponseDTO vendorBooking,
			StatusHandler statushandler);


	BookingTransactionResponse createTransaction(BookingTransactionDTO dto, BookingTransactionResponse response,
			StatusHandler statusHandler);


	MyBookingsResponseDTO getBookingSummary(BookingSummaryRequest bookingSummary, MyBookingsResponseDTO response,
			StatusHandler statusHandler);



}
