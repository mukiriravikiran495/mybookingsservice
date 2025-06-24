package com.mybookingsservice.domain;

import java.util.List;

import com.mybookingsservice.exceptions.StatusHandler;

public class VendorBookingsDTO {
	
	private List<MyBookingsRequestDTO> requestDTO;
	private StatusHandler statusHandler;
	public List<MyBookingsRequestDTO> getRequestDTO() {
		return requestDTO;
	}
	public void setRequestDTO(List<MyBookingsRequestDTO> requestDTO) {
		this.requestDTO = requestDTO;
	}
	public StatusHandler getStatusHandler() {
		return statusHandler;
	}
	public void setStatusHandler(StatusHandler statusHandler) {
		this.statusHandler = statusHandler;
	}
	
}
