package com.mybookingsservice.domain;

import java.util.List;

import com.mybookingsservice.exceptions.StatusHandler;




public class CustomerBookingResponseDTO {
	
	private List<MyBookingsRequest> requestDTO;
	private StatusHandler statusHandler;
	public List<MyBookingsRequest> getRequestDTO() {
		return requestDTO;
	}
	public void setRequestDTO(List<MyBookingsRequest> requestDTO) {
		this.requestDTO = requestDTO;
	}
	public StatusHandler getStatusHandler() {
		return statusHandler;
	}
	public void setStatusHandler(StatusHandler statusHandler) {
		this.statusHandler = statusHandler;
	}
	
	
}
