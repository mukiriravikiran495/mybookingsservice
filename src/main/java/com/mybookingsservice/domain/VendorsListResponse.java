package com.mybookingsservice.domain;

import java.util.List;

import com.mybookingsservice.exceptions.StatusHandler;

public class VendorsListResponse {
	private List<VendorDetailsDTO> vendorDetailsDTO;
	private StatusHandler statusHandler;
	public List<VendorDetailsDTO> getVendorDetailsDTO() {
		return vendorDetailsDTO;
	}
	public void setVendorDetailsDTO(List<VendorDetailsDTO> vendorDetailsDTO) {
		this.vendorDetailsDTO = vendorDetailsDTO;
	}
	public StatusHandler getStatusHandler() {
		return statusHandler;
	}
	public void setStatusHandler(StatusHandler statusHandler) {
		this.statusHandler = statusHandler;
	}
	@Override
	public String toString() {
		return "VendorsListResponse [vendorDetailsDTO=" + vendorDetailsDTO + ", statusHandler=" + statusHandler + "]";
	}
	
	
}
