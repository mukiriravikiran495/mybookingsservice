package com.mybookingsservice.service;

import java.util.List;
import com.mybookingsservice.domain.BookingSummaryRequest;
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
import com.mybookingsservice.entity.Vendor;
import com.mybookingsservice.exceptions.StatusHandler;

public interface MyBookingsService {

	
	List<MyBookingsRequest> getall();

	VendorBookingResponseDTO getBookingByVendorIdbookingId(Long vendorId, Long bookingId,
			VendorBookingResponseDTO vendorbooking, StatusHandler statusHandler);

	VendorBookingResponseDTO cancelVendorBooking(Long vendorId, Long bookingId, VendorBookingResponseDTO vendorResponse,
			StatusHandler statusHandler);
	
	HouseholdItemsResponse getHouseHoldItems(String estcategory, HouseholdItemsResponse itemResponse,
			StatusHandler statusHandler);

	Vendor getvendorProfile(long vendorId);

	MyBookingsResponse saveVehicleBookings(MyBookingsRequest mybookingsDTO, MyBookingsResponse myBookingsResponse,
			String token, String appId, StatusHandler statusHandler);


	HouseholdItemsResponse savePandMBookings(MyBookingsRequest mybookingsDTO, HouseholdItemsResponse response, String accessToken, String appId,
			StatusHandler statusHandler);


	VendorBookingResponseDTO updatePickup(Long vendorId, Long bookingId, Long custId, String otp, VendorBookingResponseDTO vendorBooking,
			StatusHandler statushandler, String token, String appId);


	VendorBookingResponseDTO updateDrop(Long vendorId, Long bookingId, Long custId, String otp, VendorBookingResponseDTO vendorBooking,
			StatusHandler statushandler, String token, String appId);


	BookingTransactionResponse createTransaction(BookingTransactionDTO dto, BookingTransactionResponse response,
			StatusHandler statusHandler);


	MyBookingsResponse getBookingSummary(BookingSummaryRequest bookingSummary, MyBookingsResponse response,
			StatusHandler statusHandler);


	ConfirmBookingResponse confirmTruckBooking(ConfirmBookingRequest confirmBookingRequest,
			ConfirmBookingResponse confirmBookingresponse, String token, String appId, StatusHandler statusHandler);


	VendorBookingResponseDTO customerLocated(Long vendorId, Long bookingId, Long custId,
			VendorBookingResponseDTO vendorBooking, StatusHandler statushandler, String token, String appId);


	VendorBookingResponseDTO reachedDropLocation(Long vendorId, Long bookingId, Long custId,
			VendorBookingResponseDTO vendorBooking, StatusHandler statushandler, String token, String appId);

	SelectPackersAndMoversResponse selectPandMBooking(SelectPackersAndMoversRequest selectPackersAndMoversRequest,
			SelectPackersAndMoversResponse selectPackersAndMoversResponse, String token, String appId, StatusHandler statusHandler);



}
