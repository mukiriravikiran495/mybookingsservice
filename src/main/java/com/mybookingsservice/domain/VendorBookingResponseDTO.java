package com.mybookingsservice.domain;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;

import com.mybookingsservice.entity.CustomerDetails;
import com.mybookingsservice.entity.VendorDetails;
import com.mybookingsservice.exceptions.StatusHandler;



public class VendorBookingResponseDTO {
	
	private MyBookingsRequest requestDTO;
	private StatusHandler statusHandler;
	public MyBookingsRequest getRequestDTO() {
		return requestDTO;
	}
	public void setRequestDTO(MyBookingsRequest requestDTO) {
		this.requestDTO = requestDTO;
	}
	public StatusHandler getStatusHandler() {
		return statusHandler;
	}
	public void setStatusHandler(StatusHandler statusHandler) {
		this.statusHandler = statusHandler;
	}
	
}
