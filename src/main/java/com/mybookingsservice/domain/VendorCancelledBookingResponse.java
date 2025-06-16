package com.mybookingsservice.domain;

import java.util.List;

import com.mybookingsservice.exceptions.StatusHandler;

public class VendorCancelledBookingResponse {
	
	private List<VendorCancelledBookingDTO> vendorCancelledBookingDTO;
	
	private StatusHandler statusHandler;

	public List<VendorCancelledBookingDTO> getVendorCancelledBookingDTO() {
		return vendorCancelledBookingDTO;
	}

	public void setVendorCancelledBookingDTO(List<VendorCancelledBookingDTO> vendorCancelledBookingDTO) {
		this.vendorCancelledBookingDTO = vendorCancelledBookingDTO;
	}

	public StatusHandler getStatusHandler() {
		return statusHandler;
	}

	public void setStatusHandler(StatusHandler statusHandler) {
		this.statusHandler = statusHandler;
	}
	
	

}
