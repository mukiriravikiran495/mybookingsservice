package com.mybookingsservice.domain;

import com.mybookingsservice.exceptions.StatusHandler;

public class CustUpdateCancelResponseDTO {

	private Long custId;
	private Long bookingId;
	private String status;
	private StatusHandler statusHandler;
	
	public CustUpdateCancelResponseDTO() {
		super();
	}

	public Long getCustId() {
		return custId;
	}

	public void setCustId(Long custId) {
		this.custId = custId;
	}

	public Long getBookingId() {
		return bookingId;
	}

	public void setBookingId(Long bookingId) {
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
	
	
}
