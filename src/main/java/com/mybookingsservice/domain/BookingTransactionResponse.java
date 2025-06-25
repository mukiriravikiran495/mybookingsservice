package com.mybookingsservice.domain;

import com.mybookingsservice.exceptions.StatusHandler;

public class BookingTransactionResponse {
	
	private BookingTransactionDTO transactionDTO;
	private StatusHandler statusHandler;
	public BookingTransactionDTO getTransactionDTO() {
		return transactionDTO;
	}
	public void setTransactionDTO(BookingTransactionDTO transactionDTO) {
		this.transactionDTO = transactionDTO;
	}
	public StatusHandler getStatusHandler() {
		return statusHandler;
	}
	public void setStatusHandler(StatusHandler statusHandler) {
		this.statusHandler = statusHandler;
	}
	

}
