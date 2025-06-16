package com.mybookingsservice.domain;

import java.util.List;

import com.mybookingsservice.exceptions.StatusHandler;

public class CustCancelledBookingResponse {
	
	private List<CustCancelledBookingResponseDTO> custCancelledBookingResponse;
	private StatusHandler statusHandler;
	
	public List<CustCancelledBookingResponseDTO> getCustCancelledBookingResponse() {
		return custCancelledBookingResponse;
	}
	public void setCustCancelledBookingResponse(List<CustCancelledBookingResponseDTO> custCancelledBookingResponse) {
		this.custCancelledBookingResponse = custCancelledBookingResponse;
	}
	public StatusHandler getStatusHandler() {
		return statusHandler;
	}
	public void setStatusHandler(StatusHandler statusHandler) {
		this.statusHandler = statusHandler;
	}
	
	

}
