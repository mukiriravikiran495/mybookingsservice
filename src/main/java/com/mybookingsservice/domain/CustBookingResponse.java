package com.mybookingsservice.domain;

import com.mybookingsservice.exceptions.StatusHandler;

public class CustBookingResponse {

	private MyBookingsRequestDTO requestDTO;
	private StatusHandler statusHandler;
	public MyBookingsRequestDTO getRequestDTO() {
		return requestDTO;
	}
	public void setRequestDTO(MyBookingsRequestDTO requestDTO) {
		this.requestDTO = requestDTO;
	}
	public StatusHandler getStatusHandler() {
		return statusHandler;
	}
	public void setStatusHandler(StatusHandler statusHandler) {
		this.statusHandler = statusHandler;
	}
	
	
}
