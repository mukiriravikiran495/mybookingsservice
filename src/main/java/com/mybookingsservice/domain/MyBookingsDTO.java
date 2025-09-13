package com.mybookingsservice.domain;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;

public class MyBookingsDTO {

	private Long bookingId;
    private Long custId;
    private Long vendorId;
    private String bookingStatus;
    private LocalDateTime bookingDate;
    private LocalDateTime shiftDate;
    private String vendorType;
    private String bookingType;
    private String cFirstName;
    private String cLastName;
    private String cMobile;
    private String cEmail;
    private Double cPickupLatitude;
    private Double cPickupLongitude;
    private String cAddress1;
    private String cAddress2;
    private String cCity;
    private String cState;
    private String cZipCode;
    private String companyName;
    private String ownerName;
    private Double cDropLatitude;
    private Double cDropLongitude;
    private String vAddress1;
    private String vAddress2;
    private String vCity;
    private String vState;
    private String vZipCode;
    private String vehicleWeight;
    private String vehicleUnit;
    private String vehicleSizeValue;
    private String vehicleSizeUnit;
    private String vpImageUrl;
    private LocalDateTime createdAt;
    private Long createdBy;
    private LocalDateTime updatedAt;
    private Long updatedBy;
    private String vehicleType;
    private String pickupOTP;
    private String dropOTP;
    private String pickupOtpIsVerified;
    private String dropOtpIsVerified;
    private Double fare;
    
	public Long getBookingId() {
		return bookingId;
	}
	public void setBookingId(Long bookingId) {
		this.bookingId = bookingId;
	}
	public Long getCustId() {
		return custId;
	}
	public void setCustId(Long custId) {
		this.custId = custId;
	}
	public Long getVendorId() {
		return vendorId;
	}
	public void setVendorId(Long vendorId) {
		this.vendorId = vendorId;
	}
	
	public String getVehicleType() {
		return vehicleType;
	}
	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}
	public String getBookingStatus() {
		return bookingStatus;
	}
	public void setBookingStatus(String bookingStatus) {
		this.bookingStatus = bookingStatus;
	}
	public LocalDateTime getBookingDate() {
		return bookingDate;
	}
	public void setBookingDate(LocalDateTime bookingDate) {
		this.bookingDate = bookingDate;
	}
	
	public LocalDateTime getShiftDate() {
		return shiftDate;
	}
	public void setShiftDate(LocalDateTime shiftDate) {
		this.shiftDate = shiftDate;
	}
	public String getVendorType() {
		return vendorType;
	}
	public void setVendorType(String vendorType) {
		this.vendorType = vendorType;
	}
	public String getBookingType() {
		return bookingType;
	}
	public void setBookingType(String bookingType) {
		this.bookingType = bookingType;
	}
	public String getcFirstName() {
		return cFirstName;
	}
	public void setcFirstName(String cFirstName) {
		this.cFirstName = cFirstName;
	}
	public String getcLastName() {
		return cLastName;
	}
	public void setcLastName(String cLastName) {
		this.cLastName = cLastName;
	}
	public String getcMobile() {
		return cMobile;
	}
	public void setcMobile(String cMobile) {
		this.cMobile = cMobile;
	}
	public String getcEmail() {
		return cEmail;
	}
	public void setcEmail(String cEmail) {
		this.cEmail = cEmail;
	}
	public Double getcPickupLatitude() {
		return cPickupLatitude;
	}
	public void setcPickupLatitude(Double cPickupLatitude) {
		this.cPickupLatitude = cPickupLatitude;
	}
	public Double getcPickupLongitude() {
		return cPickupLongitude;
	}
	public void setcPickupLongitude(Double cPickupLongitude) {
		this.cPickupLongitude = cPickupLongitude;
	}
	public String getcAddress1() {
		return cAddress1;
	}
	public void setcAddress1(String cAddress1) {
		this.cAddress1 = cAddress1;
	}
	public String getcAddress2() {
		return cAddress2;
	}
	public void setcAddress2(String cAddress2) {
		this.cAddress2 = cAddress2;
	}
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
	public String getcZipCode() {
		return cZipCode;
	}
	public void setcZipCode(String cZipCode) {
		this.cZipCode = cZipCode;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getOwnerName() {
		return ownerName;
	}
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}
	
	
	public Double getcDropLatitude() {
		return cDropLatitude;
	}
	public void setcDropLatitude(Double cDropLatitude) {
		this.cDropLatitude = cDropLatitude;
	}
	public Double getcDropLongitude() {
		return cDropLongitude;
	}
	public void setcDropLongitude(Double cDropLongitude) {
		this.cDropLongitude = cDropLongitude;
	}
	public String getvAddress1() {
		return vAddress1;
	}
	public void setvAddress1(String vAddress1) {
		this.vAddress1 = vAddress1;
	}
	public String getvAddress2() {
		return vAddress2;
	}
	public void setvAddress2(String vAddress2) {
		this.vAddress2 = vAddress2;
	}
	public String getvCity() {
		return vCity;
	}
	public void setvCity(String vCity) {
		this.vCity = vCity;
	}
	public String getvState() {
		return vState;
	}
	public void setvState(String vState) {
		this.vState = vState;
	}
	public String getvZipCode() {
		return vZipCode;
	}
	public void setvZipCode(String vZipCode) {
		this.vZipCode = vZipCode;
	}
	public String getVehicleWeight() {
		return vehicleWeight;
	}
	public void setVehicleWeight(String vehicleWeight) {
		this.vehicleWeight = vehicleWeight;
	}
	public String getVehicleUnit() {
		return vehicleUnit;
	}
	public void setVehicleUnit(String vehicleUnit) {
		this.vehicleUnit = vehicleUnit;
	}
	public String getVehicleSizeValue() {
		return vehicleSizeValue;
	}
	public void setVehicleSizeValue(String vehicleSizeValue) {
		this.vehicleSizeValue = vehicleSizeValue;
	}
	public String getVehicleSizeUnit() {
		return vehicleSizeUnit;
	}
	public void setVehicleSizeUnit(String vehicleSizeUnit) {
		this.vehicleSizeUnit = vehicleSizeUnit;
	}
	public String getVpImageUrl() {
		return vpImageUrl;
	}
	public void setVpImageUrl(String vpImageUrl) {
		this.vpImageUrl = vpImageUrl;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	public Long getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}
	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
	public Long getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(Long updatedBy) {
		this.updatedBy = updatedBy;
	}
	public String getPickupOTP() {
		return pickupOTP;
	}
	public void setPickupOTP(String pickupOTP) {
		this.pickupOTP = pickupOTP;
	}
	public String getDropOTP() {
		return dropOTP;
	}
	public void setDropOTP(String dropOTP) {
		this.dropOTP = dropOTP;
	}
	public String getPickupOtpIsVerified() {
		return pickupOtpIsVerified;
	}
	public void setPickupOtpIsVerified(String pickupOtpIsVerified) {
		this.pickupOtpIsVerified = pickupOtpIsVerified;
	}
	public String getDropOtpIsVerified() {
		return dropOtpIsVerified;
	}
	public void setDropOtpIsVerified(String dropOtpIsVerified) {
		this.dropOtpIsVerified = dropOtpIsVerified;
	}
	
	public Double getFare() {
		return fare;
	}
	public void setFare(Double fare) {
		this.fare = fare;
	}
	public MyBookingsDTO() {
		super();
	}
	@Override
	public String toString() {
		return "MyBookingsDTO [bookingId=" + bookingId + ", custId=" + custId + ", vendorId=" + vendorId
				+ ", bookingStatus=" + bookingStatus + ", bookingDate=" + bookingDate + ", vendorType=" + vendorType
				+ ", bookingType=" + bookingType + ", cFirstName=" + cFirstName + ", cLastName=" + cLastName
				+ ", cMobile=" + cMobile + ", cEmail=" + cEmail + ", cPickupLatitude=" + cPickupLatitude
				+ ", cPickupLongitude=" + cPickupLongitude + ", cAddress1=" + cAddress1 + ", cAddress2=" + cAddress2
				+ ", cCity=" + cCity + ", cState=" + cState + ", cZipCode=" + cZipCode + ", companyName=" + companyName
				+ ", ownerName=" + ownerName + ", cDropLatitude=" + cDropLatitude + ", cDropLongitude=" + cDropLongitude
				+ ", vAddress1=" + vAddress1 + ", vAddress2=" + vAddress2 + ", vCity=" + vCity + ", vState=" + vState
				+ ", vZipCode=" + vZipCode + ", vehicleWeight=" + vehicleWeight + ", vehicleUnit=" + vehicleUnit
				+ ", vehicleSizeValue=" + vehicleSizeValue + ", vehicleSizeUnit=" + vehicleSizeUnit + ", vpImageUrl="
				+ vpImageUrl + ", createdAt=" + createdAt + ", createdBy=" + createdBy + ", updatedAt=" + updatedAt
				+ ", updatedBy=" + updatedBy + "]";
	}
	
	
    
}
