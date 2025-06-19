package com.mybookingsservice.domain;

import java.time.LocalDateTime;
import java.util.List;

import com.mybookingsservice.entity.SelectedItems;


public class VendorEstimateRequest {
	
	private Long custId;
	private String c_mobile;
	private LocalDateTime booking_date; 
	private LocalDateTime scheduled_date;
	private String service_type;
	private String pickup_address;
    private Double pickup_latitude;
    private Double pickup_longitude;
    
    private String drop_address;
    private Double drop_latitude;
    private Double drop_longitude;
    private String pickup_zipcode;
    private String drop_zipcode;
    
    private List<SelectedItems> selectedItems;

	public Long getCustId() {
		return custId;
	}

	public void setCustId(Long custId) {
		this.custId = custId;
	}

	public String getC_mobile() {
		return c_mobile;
	}

	public void setC_mobile(String c_mobile) {
		this.c_mobile = c_mobile;
	}

	public LocalDateTime getBooking_date() {
		return booking_date;
	}

	public void setBooking_date(LocalDateTime booking_date) {
		this.booking_date = booking_date;
	}

	public LocalDateTime getScheduled_date() {
		return scheduled_date;
	}

	public void setScheduled_date(LocalDateTime scheduled_date) {
		this.scheduled_date = scheduled_date;
	}

	public String getService_type() {
		return service_type;
	}

	public void setService_type(String service_type) {
		this.service_type = service_type;
	}

	public String getPickup_address() {
		return pickup_address;
	}

	public void setPickup_address(String pickup_address) {
		this.pickup_address = pickup_address;
	}

	public Double getPickup_latitude() {
		return pickup_latitude;
	}

	public void setPickup_latitude(Double pickup_latitude) {
		this.pickup_latitude = pickup_latitude;
	}

	public Double getPickup_longitude() {
		return pickup_longitude;
	}

	public void setPickup_longitude(Double pickup_longitude) {
		this.pickup_longitude = pickup_longitude;
	}

	public String getDrop_address() {
		return drop_address;
	}

	public void setDrop_address(String drop_address) {
		this.drop_address = drop_address;
	}

	public Double getDrop_latitude() {
		return drop_latitude;
	}

	public void setDrop_latitude(Double drop_latitude) {
		this.drop_latitude = drop_latitude;
	}

	public Double getDrop_longitude() {
		return drop_longitude;
	}

	public void setDrop_longitude(Double drop_longitude) {
		this.drop_longitude = drop_longitude;
	}

	public String getPickup_zipcode() {
		return pickup_zipcode;
	}

	public void setPickup_zipcode(String pickup_zipcode) {
		this.pickup_zipcode = pickup_zipcode;
	}

	public String getDrop_zipcode() {
		return drop_zipcode;
	}

	public void setDrop_zipcode(String drop_zipcode) {
		this.drop_zipcode = drop_zipcode;
	}

	public List<SelectedItems> getSelectedItems() {
		return selectedItems;
	}

	public void setSelectedItems(List<SelectedItems> selectedItems) {
		this.selectedItems = selectedItems;
	}
    

}
