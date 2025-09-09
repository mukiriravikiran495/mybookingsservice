package com.mybookingsservice.domain;

import com.mybookingsservice.exceptions.StatusHandler;

public class ConfirmBookingResponse {

	private StatusHandler statusHandler;

	public StatusHandler getStatusHandler() {
		return statusHandler;
	}

	public void setStatusHandler(StatusHandler statusHandler) {
		this.statusHandler = statusHandler;
	}
	
}
