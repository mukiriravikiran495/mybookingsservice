package com.mybookingsservice.domain;

import com.mybookingsservice.exceptions.StatusHandler;

public class CustCancelResponseDTO {

	private long custId;
	private long bookingId;
	private String status;
	private StatusHandler statusHandler;
	public long getCustId() {
		return custId;
	}
	public void setCustId(long custId) {
		this.custId = custId;
	}
	
	public long getBookingId() {
		return bookingId;
	}
	public void setBookingId(long bookingId) {
		this.bookingId = bookingId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public StatusHandler getStatusHandler() {
		return statusHandler;
	}
	public void setStatusHandler(StatusHandler statusHandler) {
		this.statusHandler = statusHandler;
	}
	
	
	public CustCancelResponseDTO() {
		super();
	}
	
	
}
