package com.mybookingsservice.service;

import java.util.List;

import com.mybookingsservice.domain.CancelResponseDTO;
import com.mybookingsservice.domain.CancelledBookingResponseDTO;
import com.mybookingsservice.domain.CustomerBookingResponseDTO;
import com.mybookingsservice.domain.MyBookingsDTO;
import com.mybookingsservice.exceptions.StatusHandler;

public interface MyBookingsService {

	
	List<MyBookingsDTO> getall();

	
	List<CustomerBookingResponseDTO> getBookingsByCustomerId(Long custId);
	
	CustomerBookingResponseDTO getBookingsByBookingId(Long custId, Long bookingId, StatusHandler statusHandler,
			CustomerBookingResponseDTO customerBookingResponse);
	CancelResponseDTO cancelBookingById(Long custId, Long bookingId, CancelResponseDTO cancelResponse,
			StatusHandler statusHandler);


	List<CancelledBookingResponseDTO> getAllCancelledBookings(Long custId, String status,
			CancelledBookingResponseDTO cancelledBookings, StatusHandler statusHandler);

}
