package com.mybookingsservice.domain;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mybookingsservice.entity.SelectedItems;


public class VendorEstimateRequest {
	
	private Long custId;
	private String cMobile;
	
	@JsonProperty("bookingDate")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MMM-yyyy HH:mm:ss.SSS")
	private LocalDateTime bookingDate; 
	
	@JsonProperty("scheduledDate")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MMM-yyyy HH:mm:ss.SSS")
	private LocalDateTime scheduledDate;
	private String serviceType;
	private String pickupAddress;
    private Double pickupLatitude;
    private Double pickupLongitude;
    
    private String dropAddress;
    private Double dropLatitude;
    private Double dropLongitude;
    private String pickupZipcode;
    private String dropZipcode;
    
    
    private List<SelectedItems> selectedItems;


	public Long getCustId() {
		return custId;
	}

	public void setCustId(Long custId) {
		this.custId = custId;
	}

	
	public String getcMobile() {
		return cMobile;
	}

	public void setcMobile(String cMobile) {
		this.cMobile = cMobile;
	}

	public LocalDateTime getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(LocalDateTime bookingDate) {
		this.bookingDate = bookingDate;
	}

	public LocalDateTime getScheduledDate() {
		return scheduledDate;
	}

	public void setScheduledDate(LocalDateTime scheduledDate) {
		this.scheduledDate = scheduledDate;
	}

	public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	public String getPickupAddress() {
		return pickupAddress;
	}

	public void setPickupAddress(String pickupAddress) {
		this.pickupAddress = pickupAddress;
	}

	public Double getPickupLatitude() {
		return pickupLatitude;
	}

	public void setPickupLatitude(Double pickupLatitude) {
		this.pickupLatitude = pickupLatitude;
	}

	public Double getPickupLongitude() {
		return pickupLongitude;
	}

	public void setPickupLongitude(Double pickupLongitude) {
		this.pickupLongitude = pickupLongitude;
	}

	public String getDropAddress() {
		return dropAddress;
	}

	public void setDropAddress(String dropAddress) {
		this.dropAddress = dropAddress;
	}

	public Double getDropLatitude() {
		return dropLatitude;
	}

	public void setDropLatitude(Double dropLatitude) {
		this.dropLatitude = dropLatitude;
	}

	public Double getDropLongitude() {
		return dropLongitude;
	}

	public void setDropLongitude(Double dropLongitude) {
		this.dropLongitude = dropLongitude;
	}

	public String getPickupZipcode() {
		return pickupZipcode;
	}

	public void setPickupZipcode(String pickupZipcode) {
		this.pickupZipcode = pickupZipcode;
	}

	public String getDropZipcode() {
		return dropZipcode;
	}

	public void setDropZipcode(String dropZipcode) {
		this.dropZipcode = dropZipcode;
	}

	public List<SelectedItems> getSelectedItems() {
		return selectedItems;
	}

	public void setSelectedItems(List<SelectedItems> selectedItems) {
		this.selectedItems = selectedItems;
	}
    

}
