package com.mybookingsservice.domain;

import java.time.LocalDateTime;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;


public class BookingTypeRequest {

	private Long bookingId;
	
	
	private Long custId;
	private String status;
	private String cCity;
	private String cState;
	private Long createdBy;
	private String cMobile;
	private String pickupAddress;
    private Double pickupLatitude;
    private Double pickupLongitude;
    private String pickupZipcode;
    private String dropAddress;
    private Double dropLatitude;
    private Double dropLongitude;
    private String dropZipcode;
    private int basePricePerKM;
	private int pricePerKG;
	private int estimatedPrice;
	private int avgDeliveryTimeInDays;
	
    @JsonProperty("bookingDate")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MMM-yyyy HH:mm:ss.SSS")
	private LocalDateTime bookingDate; 
    
    @JsonProperty("scheduledDate")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MMM-yyyy HH:mm:ss.SSS")
	private LocalDateTime scheduledDate;
    
	public String getcCity() {
		return cCity;
	}

	public void setcCity(String cCity) {
		this.cCity = cCity;
	}

	public String getcState() {
		return cState;
	}

	public void setcState(String cState) {
		this.cState = cState;
	}

	public int getBasePricePerKM() {
		return basePricePerKM;
	}

	public void setBasePricePerKM(int basePricePerKM) {
		this.basePricePerKM = basePricePerKM;
	}

	public int getPricePerKG() {
		return pricePerKG;
	}

	public void setPricePerKG(int pricePerKG) {
		this.pricePerKG = pricePerKG;
	}

	public int getEstimatedPrice() {
		return estimatedPrice;
	}

	public void setEstimatedPrice(int estimatedPrice) {
		this.estimatedPrice = estimatedPrice;
	}

	public int getAvgDeliveryTimeInDays() {
		return avgDeliveryTimeInDays;
	}

	public void setAvgDeliveryTimeInDays(int avgDeliveryTimeInDays) {
		this.avgDeliveryTimeInDays = avgDeliveryTimeInDays;
	}

	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public Long getBookingId() {
		return bookingId;
	}

	public String getcMobile() {
		return cMobile;
	}

	public void setcMobile(String cMobile) {
		this.cMobile = cMobile;
	}

	private String serviceType;

	

	public Long getCustId() {
		return custId;
	}

	public void setCustId(Long custId) {
		this.custId = custId;
	}

	public void setBookingId(Long bookingId) {
		this.bookingId = bookingId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public String getDropZipcode() {
		return dropZipcode;
	}

	public void setDropZipcode(String dropZipcode) {
		this.dropZipcode = dropZipcode;
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

	
}
