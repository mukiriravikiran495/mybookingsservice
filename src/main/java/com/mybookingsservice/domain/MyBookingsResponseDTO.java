package com.mybookingsservice.domain;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mybookingsservice.exceptions.StatusHandler;

public class MyBookingsResponseDTO {

	private MyBookingsRequestDTO myBookingRequestDTO;
	
	private StatusHandler statusHandler;

	public MyBookingsRequestDTO getMyBookingRequestDTO() {
		return myBookingRequestDTO;
	}

	public void setMyBookingRequestDTO(MyBookingsRequestDTO myBookingRequestDTO) {
		this.myBookingRequestDTO = myBookingRequestDTO;
	}

	public StatusHandler getStatusHandler() {
		return statusHandler;
	}

	public void setStatusHandler(StatusHandler statusHandler) {
		this.statusHandler = statusHandler;
	}

	
	
}
