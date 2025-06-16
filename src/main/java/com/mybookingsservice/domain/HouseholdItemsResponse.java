package com.mybookingsservice.domain;

import java.util.List;

import com.mybookingsservice.exceptions.StatusHandler;

public class HouseholdItemsResponse {

	private List<HouseholdItemsDTO> householdItemsDTO;
	private StatusHandler statusHandler;
	public List<HouseholdItemsDTO> getHouseholdItemsDTO() {
		return householdItemsDTO;
	}
	public void setHouseholdItemsDTO(List<HouseholdItemsDTO> householdItemsDTO) {
		this.householdItemsDTO = householdItemsDTO;
	}
	public StatusHandler getStatusHandler() {
		return statusHandler;
	}
	public void setStatusHandler(StatusHandler statusHandler) {
		this.statusHandler = statusHandler;
	}
	
	
}
