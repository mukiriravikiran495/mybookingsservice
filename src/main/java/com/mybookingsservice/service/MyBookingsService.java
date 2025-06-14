package com.mybookingsservice.service;

import java.util.List;

import com.mybookingsservice.domain.CustomerBookingResponseDTO;
import com.mybookingsservice.domain.MyBookingsDTO;

public interface MyBookingsService {

	
	List<MyBookingsDTO> getall();

	List<CustomerBookingResponseDTO> getBookingsByCustomerId(Long custId);

}
