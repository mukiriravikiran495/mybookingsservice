package com.mybookingsservice.domain;

import com.mybookingsservice.exceptions.StatusHandler;

public class CustBookingResponse {

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
