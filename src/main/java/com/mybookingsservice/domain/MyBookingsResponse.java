package com.mybookingsservice.domain;

import java.util.List;

import com.mybookingsservice.exceptions.StatusHandler;

public class MyBookingsResponse {

	private Long bookingId;
	private Long custId;
	private List<AvailableVehiclesDTO> avaiableVehiclesDTO;
	private StatusHandler statusHandler;
	
	
	public Long getBookingId() {
		return bookingId;
	}
	public void setBookingId(Long bookingId) {
		this.bookingId = bookingId;
	}
	public Long getCustId() {
		return custId;
	}
	public void setCustId(Long custId) {
		this.custId = custId;
	}
	public List<AvailableVehiclesDTO> getAvaiableVehiclesDTO() {
		return avaiableVehiclesDTO;
	}
	public void setAvaiableVehiclesDTO(List<AvailableVehiclesDTO> avaiableVehiclesDTO) {
		this.avaiableVehiclesDTO = avaiableVehiclesDTO;
	}
	public StatusHandler getStatusHandler() {
		return statusHandler;
	}
	public void setStatusHandler(StatusHandler statusHandler) {
		this.statusHandler = statusHandler;
	}

	
	
}
