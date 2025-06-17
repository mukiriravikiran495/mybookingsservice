package com.mybookingsservice.domain;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import com.mybookingsservice.exceptions.StatusHandler;

public class VendorEstimateResponse {

	private LocalDateTime BOOKING_DATE; 
	private String SCHEDULED_DATE;
	private String SERVICE_TYPE;
	private String pickupAddress;
    private double pickupLatitude;
    private double pickupLongitude;
    private String pickup_zipcode;
    private String drop_zipcode;
    private String dropAddress;
    private double dropLatitude;
    private double dropLongitude;
    
   
    private List<VendorDTO> vendorDTO;
    
    private StatusHandler statusHandler;

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

	

	public LocalDateTime getBOOKING_DATE() {
		return BOOKING_DATE;
	}

	public void setBOOKING_DATE(LocalDateTime bOOKING_DATE) {
		BOOKING_DATE = bOOKING_DATE;
	}

	public String getSCHEDULED_DATE() {
		return SCHEDULED_DATE;
	}

	public void setSCHEDULED_DATE(String sCHEDULED_DATE) {
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

	public double getPickupLatitude() {
		return pickupLatitude;
	}

	public void setPickupLatitude(double pickupLatitude) {
		this.pickupLatitude = pickupLatitude;
	}

	public double getPickupLongitude() {
		return pickupLongitude;
	}

	public void setPickupLongitude(double pickupLongitude) {
		this.pickupLongitude = pickupLongitude;
	}

	public String getDropAddress() {
		return dropAddress;
	}

	public void setDropAddress(String dropAddress) {
		this.dropAddress = dropAddress;
	}

	public double getDropLatitude() {
		return dropLatitude;
	}

	public void setDropLatitude(double dropLatitude) {
		this.dropLatitude = dropLatitude;
	}

	public double getDropLongitude() {
		return dropLongitude;
	}

	public void setDropLongitude(double dropLongitude) {
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
