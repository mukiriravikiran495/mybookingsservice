package com.mybookingsservice.domain;

public class ConfirmBookingRequest {

	private MyBookingsDTO myBookingsDTO; 
	private AvailableVehiclesDTO availableVehiclesDTO;
	public MyBookingsDTO getMyBookingsDTO() {
		return myBookingsDTO;
	}
	public void setMyBookingsDTO(MyBookingsDTO myBookingsDTO) {
		this.myBookingsDTO = myBookingsDTO;
	}
	public AvailableVehiclesDTO getAvailableVehiclesDTO() {
		return availableVehiclesDTO;
	}
	public void setAvailableVehiclesDTO(AvailableVehiclesDTO availableVehiclesDTO) {
		this.availableVehiclesDTO = availableVehiclesDTO;
	}
	@Override
	public String toString() {
		return "ConfirmBookingRequest [myBookingsDTO=" + myBookingsDTO + ", availableVehiclesDTO="
				+ availableVehiclesDTO + "]";
	}	
}
