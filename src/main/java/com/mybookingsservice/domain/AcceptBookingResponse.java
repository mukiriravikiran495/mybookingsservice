package com.mybookingsservice.domain;

import com.mybookingsservice.exceptions.StatusHandler;

public class AcceptBookingResponse {

	private MyBookingsRequest mybookingsDTO;
	private StatusHandler statusHandler;
	public MyBookingsRequest getMybookingsDTO() {
		return mybookingsDTO;
	}
	public void setMybookingsDTO(MyBookingsRequest mybookingsDTO) {
		this.mybookingsDTO = mybookingsDTO;
	}
	public StatusHandler getStatusHandler() {
		return statusHandler;
	}
	public void setStatusHandler(StatusHandler statusHandler) {
		this.statusHandler = statusHandler;
	}
	
	
	
}
