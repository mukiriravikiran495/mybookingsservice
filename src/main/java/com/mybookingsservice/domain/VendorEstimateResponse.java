package com.mybookingsservice.domain;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import com.mybookingsservice.exceptions.StatusHandler;

public class VendorEstimateResponse {

	
	private LocalDateTime BOOKING_DATE; 
	private LocalDateTime SCHEDULED_DATE;
	private String SERVICE_TYPE;
	private String pickupAddress;
    private Double pickupLatitude;
    private Double pickupLongitude;
    private String pickup_zipcode;
    private String drop_zipcode;
    private String dropAddress;
    private Double dropLatitude;
    private Double dropLongitude;
    
   
    private List<VendorDTO> vendorDTO;
    
    private StatusHandler statusHandler;

	public LocalDateTime getBOOKING_DATE() {
		return BOOKING_DATE;
	}

	public void setBOOKING_DATE(LocalDateTime bOOKING_DATE) {
		BOOKING_DATE = bOOKING_DATE;
	}


	public LocalDateTime getSCHEDULED_DATE() {
		return SCHEDULED_DATE;
	}

	public void setSCHEDULED_DATE(LocalDateTime sCHEDULED_DATE) {
		SCHEDULED_DATE = sCHEDULED_DATE;
	}

	public String getSERVICE_TYPE() {
		return SERVICE_TYPE;
	}

	public void setSERVICE_TYPE(String sERVICE_TYPE) {
		SERVICE_TYPE = sERVICE_TYPE;
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
