package com.mybookingsservice.domain;

import java.util.List;

import com.mybookingsservice.exceptions.StatusHandler;

public class SelectPackersAndMoversResponse {

	private MyBookingsDTO myBookingsDTO;
	private List<AvailablePandMDTO> availablePandMDTO;
	private StatusHandler statusHandler;
	
	public MyBookingsDTO getMyBookingsDTO() {
		return myBookingsDTO;
	}
	public void setMyBookingsDTO(MyBookingsDTO myBookingsDTO) {
		this.myBookingsDTO = myBookingsDTO;
	}
	public List<AvailablePandMDTO> getAvailablePandMDTO() {
		return availablePandMDTO;
	}
	public void setAvailablePandMDTO(List<AvailablePandMDTO> availablePandMDTO) {
		this.availablePandMDTO = availablePandMDTO;
	}
	public StatusHandler getStatusHandler() {
		return statusHandler;
	}
	public void setStatusHandler(StatusHandler statusHandler) {
		this.statusHandler = statusHandler;
	}
	
}
