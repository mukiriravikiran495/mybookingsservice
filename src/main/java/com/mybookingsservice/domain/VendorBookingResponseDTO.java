package com.mybookingsservice.domain;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;

import com.mybookingsservice.entity.CustomerDetails;
import com.mybookingsservice.entity.VendorDetails;
import com.mybookingsservice.exceptions.StatusHandler;



public class VendorBookingResponseDTO {
	
	private MyBookingsDTO myBookingsDTO;
	private StatusHandler statusHandler;
	
	public MyBookingsDTO getMyBookingsDTO() {
		return myBookingsDTO;
	}
	public void setMyBookingsDTO(MyBookingsDTO myBookingsDTO) {
		this.myBookingsDTO = myBookingsDTO;
	}
	public StatusHandler getStatusHandler() {
		return statusHandler;
	}
	public void setStatusHandler(StatusHandler statusHandler) {
		this.statusHandler = statusHandler;
	}
	@Override
	public String toString() {
		return "VendorBookingResponseDTO [myBookingsDTO=" + myBookingsDTO + ", statusHandler=" + statusHandler
				+ ", getMyBookingsDTO()=" + getMyBookingsDTO() + ", getStatusHandler()=" + getStatusHandler()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}
	
}
