package com.mybookingsservice.domain;

import java.time.LocalDateTime;
import java.util.List;


public class VendorEstimateRequest {

	private LocalDateTime booking_date; 
	private String scheduled_date;
	private String service_type;
	private String pickup_address;
    private double pickup_latitude;
    private double pickup_longitude;
    
    private String drop_address;
    private double drop_latitude;
    private double drop_longitude;
    private String pickup_zipcode;
    private String drop_zipcode;
    
    private List<SelectedItemsDTO> selectedItemsDTO;
    
    
    
	public List<SelectedItemsDTO> getSelectedItemsDTO() {
		return selectedItemsDTO;
	}
	
	public LocalDateTime getBooking_date() {
		return booking_date;
	}

	public void setBooking_date(LocalDateTime booking_date) {
		this.booking_date = booking_date;
	}

	public String getScheduled_date() {
		return scheduled_date;
	}

	public void setScheduled_date(String scheduled_date) {
		this.scheduled_date = scheduled_date;
	}

	public String getService_type() {
		return service_type;
	}

	public void setService_type(String service_type) {
		this.service_type = service_type;
	}

	public void setSelectedItemsDTO(List<SelectedItemsDTO> selectedItemsDTO) {
		this.selectedItemsDTO = selectedItemsDTO;
	}

	public String getPickup_address() {
		return pickup_address;
	}
	public void setPickup_address(String pickup_address) {
		this.pickup_address = pickup_address;
	}
	public double getPickup_latitude() {
		return pickup_latitude;
	}
	public void setPickup_latitude(double pickup_latitude) {
		this.pickup_latitude = pickup_latitude;
	}
	public double getPickup_longitude() {
		return pickup_longitude;
	}
	public void setPickup_longitude(double pickup_longitude) {
		this.pickup_longitude = pickup_longitude;
	}
	public String getPickup_zipcode() {
		return pickup_zipcode;
	}
	public void setPickup_zipcode(String pickup_zipcode) {
		this.pickup_zipcode = pickup_zipcode;
	}
	public String getDrop_address() {
		return drop_address;
	}
	public void setDrop_address(String drop_address) {
		this.drop_address = drop_address;
	}
	public double getDrop_latitude() {
		return drop_latitude;
	}
	public void setDrop_latitude(double drop_latitude) {
		this.drop_latitude = drop_latitude;
	}
	public double getDrop_longitude() {
		return drop_longitude;
	}
	public void setDrop_longitude(double drop_longitude) {
		this.drop_longitude = drop_longitude;
	}
	public String getDrop_zipcode() {
		return drop_zipcode;
	}
	public void setDrop_zipcode(String drop_zipcode) {
		this.drop_zipcode = drop_zipcode;
	}

	@Override
	public String toString() {
		return "VendorEstimateRequest [booking_date=" + booking_date + ", scheduled_date=" + scheduled_date
				+ ", service_type=" + service_type + ", pickup_address=" + pickup_address + ", pickup_latitude="
				+ pickup_latitude + ", pickup_longitude=" + pickup_longitude + ", pickup_zipcode=" + pickup_zipcode
				+ ", drop_address=" + drop_address + ", drop_latitude=" + drop_latitude + ", drop_longitude="
				+ drop_longitude + ", drop_zipcode=" + drop_zipcode + ", selectedItemsDTO=" + selectedItemsDTO + "]";
	}

}
