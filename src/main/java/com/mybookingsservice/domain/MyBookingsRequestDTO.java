package com.mybookingsservice.domain;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class MyBookingsRequestDTO {
	
	private Long bookingId;
	private Long custId;
	private String cMobile;
	private String status;
	private Long vendorId;
	private String pickupAddress;
    private Double pickupLatitude;
    private Double pickupLongitude;
    private String pickupZipcode;
    private String dropAddress;
    private Double dropLatitude;
    private Double dropLongitude;
    private String dropZipcode;
    private String serviceType;
	private BigInteger itemCount;   
	private BigDecimal  estimatedWeight;  
	private BigDecimal  estimatedCost; 
	private BigDecimal  discountAmount;  
	private BigDecimal  finalCost; 
	private String paymentStatus ; 
	private String paymentMode;  
	private String transactionId;
	private String bookingStatus;  
	private String vehicleNumber; 
	private String trackingUrl;
	private String otpforDelivery;
	private String vFirstname;
	private String vLastname;
	private String vMobile;
	private String vEmail;
	private Long vAddressId;
	private String vAddress1;
	private String vCity;
	private String vState;
	private String vZipcode;
	private int basePricePerKM;
	private int pricePerKG;
	private int estimatedPrice;
	private int avgDeliveryTimeInDays;
	
	@JsonProperty("estimatedDeliveryDate")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MMM-yyyy HH:mm:ss.SSS")
	private LocalDateTime estimatedDeliverydate;
	
	private Long cAddressId;
	private String cAddress1;
	private String cCity;
	private String cState;
	private String cZipcode;
	private String cFirstname;
	private String cLastname;
	private String cEmail;
	
    @JsonProperty("bookingDate")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MMM-yyyy HH:mm:ss.SSS")
	private LocalDateTime bookingDate; 
    
    @JsonProperty("scheduledDate")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MMM-yyyy HH:mm:ss.SSS")
	private LocalDateTime scheduledDate;
    
	private String pickupTimeSlot; 
	
	@JsonProperty("deliveryDate")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MMM-yyyy HH:mm:ss.SSS")
	private LocalDateTime deliveryDate;
	
	@JsonProperty("createdAt")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MMM-yyyy HH:mm:ss.SSS")
	private LocalDateTime createdAt; 
	
	private Long createdBy;
	
	@JsonProperty("updatedAt")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MMM-yyyy HH:mm:ss.SSS")
	private LocalDateTime updatedAt;  
	private Long updatedBy;
	
//	private CustomerDetailsDTO customerDetails;
//	
//	private VendorDetailsDTO vendorDetails;
	
	private List<SelectedItemsDTO> selectedItems;


	public String getcMobile() {
		return cMobile;
	}

	public String getvFirstname() {
		return vFirstname;
	}

	public void setvFirstname(String vFirstname) {
		this.vFirstname = vFirstname;
	}

	public String getvLastname() {
		return vLastname;
	}

	public void setvLastname(String vLastname) {
		this.vLastname = vLastname;
	}

	public String getvMobile() {
		return vMobile;
	}

	public void setvMobile(String vMobile) {
		this.vMobile = vMobile;
	}

	public String getvEmail() {
		return vEmail;
	}

	public void setvEmail(String vEmail) {
		this.vEmail = vEmail;
	}

	public Long getvAddressId() {
		return vAddressId;
	}

	public void setvAddressId(Long vAddressId) {
		this.vAddressId = vAddressId;
	}

	public String getvAddress1() {
		return vAddress1;
	}

	public void setvAddress1(String vAddress1) {
		this.vAddress1 = vAddress1;
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

	public String getvZipcode() {
		return vZipcode;
	}

	public void setvZipcode(String vZipcode) {
		this.vZipcode = vZipcode;
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

	public LocalDateTime getEstimatedDeliverydate() {
		return estimatedDeliverydate;
	}

	public void setEstimatedDeliverydate(LocalDateTime estimatedDeliverydate) {
		this.estimatedDeliverydate = estimatedDeliverydate;
	}

	public int getAvgDeliveryTimeInDays() {
		return avgDeliveryTimeInDays;
	}

	public void setAvgDeliveryTimeInDays(int avgDeliveryTimeInDays) {
		this.avgDeliveryTimeInDays = avgDeliveryTimeInDays;
	}

	public Long getcAddressId() {
		return cAddressId;
	}

	public void setcAddressId(Long cAddressId) {
		this.cAddressId = cAddressId;
	}

	public String getcAddress1() {
		return cAddress1;
	}

	public void setcAddress1(String cAddress1) {
		this.cAddress1 = cAddress1;
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

	public String getcZipcode() {
		return cZipcode;
	}

	public void setcZipcode(String cZipcode) {
		this.cZipcode = cZipcode;
	}

	public String getcFirstname() {
		return cFirstname;
	}

	public void setcFirstname(String cFirstname) {
		this.cFirstname = cFirstname;
	}

	public String getcLastname() {
		return cLastname;
	}

	public void setcLastname(String cLastname) {
		this.cLastname = cLastname;
	}

	public String getcEmail() {
		return cEmail;
	}

	public void setcEmail(String cEmail) {
		this.cEmail = cEmail;
	}

	public void setcMobile(String cMobile) {
		this.cMobile = cMobile;
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

	public String getPickupTimeSlot() {
		return pickupTimeSlot;
	}

	public void setPickupTimeSlot(String pickupTimeSlot) {
		this.pickupTimeSlot = pickupTimeSlot;
	}

	public LocalDateTime getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(LocalDateTime deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	public BigInteger getItemCount() {
		return itemCount;
	}

	public void setItemCount(BigInteger itemCount) {
		this.itemCount = itemCount;
	}

	public BigDecimal getEstimatedWeight() {
		return estimatedWeight;
	}

	public void setEstimatedWeight(BigDecimal estimatedWeight) {
		this.estimatedWeight = estimatedWeight;
	}

	public BigDecimal getEstimatedCost() {
		return estimatedCost;
	}

	public void setEstimatedCost(BigDecimal estimatedCost) {
		this.estimatedCost = estimatedCost;
	}

	public BigDecimal getDiscountAmount() {
		return discountAmount;
	}

	public void setDiscountAmount(BigDecimal discountAmount) {
		this.discountAmount = discountAmount;
	}

	public BigDecimal getFinalCost() {
		return finalCost;
	}

	public void setFinalCost(BigDecimal finalCost) {
		this.finalCost = finalCost;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public String getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getBookingStatus() {
		return bookingStatus;
	}

	public void setBookingStatus(String bookingStatus) {
		this.bookingStatus = bookingStatus;
	}

	public String getVehicleNumber() {
		return vehicleNumber;
	}

	public void setVehicleNumber(String vehicleNumber) {
		this.vehicleNumber = vehicleNumber;
	}

	public String getTrackingUrl() {
		return trackingUrl;
	}

	public void setTrackingUrl(String trackingUrl) {
		this.trackingUrl = trackingUrl;
	}

	public String getOtpforDelivery() {
		return otpforDelivery;
	}

	public void setOtpforDelivery(String otpforDelivery) {
		this.otpforDelivery = otpforDelivery;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public Long getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Long updatedBy) {
		this.updatedBy = updatedBy;
	}

	

	public List<SelectedItemsDTO> getSelectedItems() {
		return selectedItems;
	}

	public void setSelectedItems(List<SelectedItemsDTO> selectedItems) {
		this.selectedItems = selectedItems;
	}


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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public MyBookingsRequestDTO() {
		
	}
	
	
}
