package com.mybookingsservice.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table( name = "BOOKINGS", schema = "MYBOOKINGS")
public class MyBookings implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "booking_seq_gen")
	@SequenceGenerator(name = "booking_seq_gen", sequenceName = "BOOKINGS_SEQ", allocationSize = 1)
	@Column(name = "BOOKINGID", nullable = false)
	private Long bookingId;
	
	@Column(name = "CUSTID", nullable = false)
    private Long custId;

    @Column(name = "VENDORID")
    private Long vendorId;

    @Column(name = "BOOKINGSTATUS", length = 50)
    private String bookingStatus;

    @Column(name = "BOOKINGDATE")
    private LocalDateTime bookingDate;
    
    @Column(name = "SHIFTDATE")
    private LocalDateTime shiftDate;

    @Column(name = "VENDORTYPE", length = 20)
    private String vendorType;

    @Column(name = "BOOKINGTYPE", length = 20)
    private String bookingType;

    // Customer details
    @Column(name = "CFIRSTNAME", length = 20)
    private String cFirstName;

    @Column(name = "CLASTNAME", length = 20)
    private String cLastName;

    @Column(name = "CMOBILE", length = 20)
    private String cMobile;

    @Column(name = "CEMAIL", length = 50)
    private String cEmail;

    @Column(name = "CPICKUPLATITUDE")
    private Double cPickupLatitude;

    @Column(name = "CPICKUPLONGITUDE")
    private Double cPickupLongitude;

    @Column(name = "CADDRESS1", length = 50)
    private String cAddress1;

    @Column(name = "CADDRESS2", length = 50)
    private String cAddress2;

    @Column(name = "CCITY", length = 20)
    private String cCity;

    @Column(name = "CSTATE", length = 20)
    private String cState;

    @Column(name = "CZIPCODE", length = 10)
    private String cZipCode;

    // Vendor details
    @Column(name = "COMPANYNAME", length = 30)
    private String companyName;

    @Column(name = "OWNERNAME", length = 30)
    private String ownerName;

    @Column(name = "CDROPLATITUDE")
    private Double cDropLatitude;

    @Column(name = "CDROPLONGITUDE")
    private Double cDropLongitude;

    @Column(name = "VADDRESS1", length = 50)
    private String vAddress1;

    @Column(name = "VADDRESS2", length = 50)
    private String vAddress2;

    @Column(name = "VCITY", length = 20)
    private String vCity;

    @Column(name = "VSTATE", length = 20)
    private String vState;

    @Column(name = "VZIPCODE", length = 10)
    private String vZipCode;

    // Vehicle details
    @Column(name = "VEHICLE_WEIGHT", length = 20)
    private String vehicleWeight;

    @Column(name = "VEHICLE_UNIT", length = 20)
    private String vehicleUnit;

    @Column(name = "VEHICLE_SIZE_VALUE", length = 20)
    private String vehicleSizeValue;

    @Column(name = "VEHICLE_SIZE_UNIT", length = 20)
    private String vehicleSizeUnit;

    @Column(name = "VPIMAGE_URL", length = 255)
    private String vpImageUrl;

    // Audit fields
    @Column(name = "CREATEDAT")
    @JsonProperty("createdAt")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
    private LocalDateTime createdAt;

    @Column(name = "CREATEDBY")
    private Long createdBy;

    @Column(name = "UPDATEDAT")
    @JsonProperty("updatedAt")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
    private LocalDateTime updatedAt;

    @Column(name = "UPDATEDBY")
    private Long updatedBy;
    
    @Column(name = "VEHICLE_TYPE")
    private String vehicleType;
    
    
    @Column(name = "PICKUPOTP")
    private String pickupOTP;
    
    @Column(name = "DROPOTP")
    private String dropOTP;
    
    @Column(name = "PICKUP_OTP_ISVERIFIED", length = 1)
    private String pickupOtpIsVerified;
    
    @Column(name = "DROP_OTP_ISVERIFIED", length = 1)
    private String dropOtpIsVerified;
    
    @Column(name = "FARE")
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

	public LocalDateTime getShiftDate() {
		return shiftDate;
	}

	public void setShiftDate(LocalDateTime shiftDate) {
		this.shiftDate = shiftDate;
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

	@Override
	public String toString() {
		return "MyBookings [bookingId=" + bookingId + ", custId=" + custId + ", vendorId=" + vendorId
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

	public MyBookings() {
		super();
	}

	
}
