package com.mybookingsservice.domain;


public class MyBookingsRequest {
	
	private MyBookingsDTO myBookingsDTO;

	public MyBookingsDTO getMyBookingsDTO() {
		return myBookingsDTO;
	}

	public void setMyBookingsDTO(MyBookingsDTO myBookingsDTO) {
		this.myBookingsDTO = myBookingsDTO;
	}

	@Override
	public String toString() {
		return "MyBookingsRequest [myBookingsDTO=" + myBookingsDTO + "]";
	}
	
}
