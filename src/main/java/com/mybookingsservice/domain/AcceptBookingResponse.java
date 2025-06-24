package com.mybookingsservice.domain;

import com.mybookingsservice.exceptions.StatusHandler;

public class AcceptBookingResponse {

	private MyBookingsRequestDTO mybookingsDTO;
	private StatusHandler statusHandler;
	public MyBookingsRequestDTO getMybookingsDTO() {
		return mybookingsDTO;
	}
	public void setMybookingsDTO(MyBookingsRequestDTO mybookingsDTO) {
		this.mybookingsDTO = mybookingsDTO;
	}
	public StatusHandler getStatusHandler() {
		return statusHandler;
	}
	public void setStatusHandler(StatusHandler statusHandler) {
		this.statusHandler = statusHandler;
	}
	
	
	
}
