package com.mybookingsservice.domain;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mybookingsservice.exceptions.StatusHandler;

public class VendorEstimateResponse {

	@JsonProperty("bookingDate")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MMM-yyyy HH:mm:ss.SSS")
	private LocalDateTime bookingDate; 
	
	@JsonProperty("scheduledDate")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MMM-yyyy HH:mm:ss.SSS")
	private LocalDateTime scheduledDate;
	private String servicetype;
	private String pickupAddress;
    private Double pickupLatitude;
    private Double pickupLongitude;
    private String pickupZipcode;
    private String dropZipcode;
    private String dropAddress;
    private Double dropLatitude;
    private Double dropLongitude;
    
   
    private List<VendorDTO> vendorDTO;
    
    private StatusHandler statusHandler;

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

	public String getServicetype() {
		return servicetype;
	}

	public void setServicetype(String servicetype) {
		this.servicetype = servicetype;
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

	public List<VendorDTO> getVendorDTO() {
		return vendorDTO;
	}

	public void setVendorDTO(List<VendorDTO> vendorDTO) {
		this.vendorDTO = vendorDTO;
	}

	public StatusHandler getStatusHandler() {
		return statusHandler;
	}

	public void setStatusHandler(StatusHandler statusHandler) {
		this.statusHandler = statusHandler;
	}

    
}
