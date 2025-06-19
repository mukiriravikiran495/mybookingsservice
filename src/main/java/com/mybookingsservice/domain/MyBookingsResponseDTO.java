package com.mybookingsservice.domain;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import com.mybookingsservice.exceptions.StatusHandler;

public class MyBookingsResponseDTO {

private Long bookingId;
	
	private String status;
	
	private String pickup_address;
    private Double pickup_latitude;
    private Double pickup_longitude;
    private String pickup_zipcode;
    private String drop_address;
    private Double drop_latitude;
    private Double drop_longitude;
    private String drop_zipcode;
	private LocalDateTime BOOKING_DATE; 
	private LocalDateTime SCHEDULED_DATE;
	private String PICKUP_TIME_SLOT; 
	private LocalDateTime DELIVERY_DATE;
	private String SERVICE_TYPE;
	private BigInteger ITEM_COUNT;   
	private BigDecimal  ESTIMATED_WEIGHT;  
	private BigDecimal  ESTIMATED_COST; 
	private BigDecimal  DISCOUNT_AMOUNT;  
	private BigDecimal  FINAL_COST; 
	private String PAYMENT_STATUS ; 
	private String PAYMENT_MODE;  
	private String TRANSACTION_ID;
	private String BOOKING_STATUS;  
	private String VEHICLE_NUMBER; 
	private String TRACKING_URL;
	private String OTP_FOR_DELIVERY;  
	private Timestamp CREATED_AT;  
	private Long CREATED_BY; 
	private Timestamp UPDATED_AT;  
	private Long UPDATED_BY;
	
	private CustomerDetailsDTO customerDetails;
	
	private VendorDetailsDTO vendorDetails;
	
	private List<SelectedItemsDTO> selectedItems;

	private StatusHandler statusHandler;

	public Long getBookingId() {
		return bookingId;
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

	public LocalDateTime getSCHEDULED_DATE() {
		return SCHEDULED_DATE;
	}

	public void setSCHEDULED_DATE(LocalDateTime sCHEDULED_DATE) {
		SCHEDULED_DATE = sCHEDULED_DATE;
	}

	public String getPICKUP_TIME_SLOT() {
		return PICKUP_TIME_SLOT;
	}

	public void setPICKUP_TIME_SLOT(String pICKUP_TIME_SLOT) {
		PICKUP_TIME_SLOT = pICKUP_TIME_SLOT;
	}

	public LocalDateTime getDELIVERY_DATE() {
		return DELIVERY_DATE;
	}

	public void setDELIVERY_DATE(LocalDateTime dELIVERY_DATE) {
		DELIVERY_DATE = dELIVERY_DATE;
	}

	public String getSERVICE_TYPE() {
		return SERVICE_TYPE;
	}

	public void setSERVICE_TYPE(String sERVICE_TYPE) {
		SERVICE_TYPE = sERVICE_TYPE;
	}

	public BigInteger getITEM_COUNT() {
		return ITEM_COUNT;
	}

	public void setITEM_COUNT(BigInteger iTEM_COUNT) {
		ITEM_COUNT = iTEM_COUNT;
	}

	public BigDecimal getESTIMATED_WEIGHT() {
		return ESTIMATED_WEIGHT;
	}

	public void setESTIMATED_WEIGHT(BigDecimal eSTIMATED_WEIGHT) {
		ESTIMATED_WEIGHT = eSTIMATED_WEIGHT;
	}

	public BigDecimal getESTIMATED_COST() {
		return ESTIMATED_COST;
	}

	public void setESTIMATED_COST(BigDecimal eSTIMATED_COST) {
		ESTIMATED_COST = eSTIMATED_COST;
	}

	public BigDecimal getDISCOUNT_AMOUNT() {
		return DISCOUNT_AMOUNT;
	}

	public void setDISCOUNT_AMOUNT(BigDecimal dISCOUNT_AMOUNT) {
		DISCOUNT_AMOUNT = dISCOUNT_AMOUNT;
	}

	public BigDecimal getFINAL_COST() {
		return FINAL_COST;
	}

	public void setFINAL_COST(BigDecimal fINAL_COST) {
		FINAL_COST = fINAL_COST;
	}

	public String getPAYMENT_STATUS() {
		return PAYMENT_STATUS;
	}

	public void setPAYMENT_STATUS(String pAYMENT_STATUS) {
		PAYMENT_STATUS = pAYMENT_STATUS;
	}

	public String getPAYMENT_MODE() {
		return PAYMENT_MODE;
	}

	public void setPAYMENT_MODE(String pAYMENT_MODE) {
		PAYMENT_MODE = pAYMENT_MODE;
	}

	public String getTRANSACTION_ID() {
		return TRANSACTION_ID;
	}

	public void setTRANSACTION_ID(String tRANSACTION_ID) {
		TRANSACTION_ID = tRANSACTION_ID;
	}

	public String getBOOKING_STATUS() {
		return BOOKING_STATUS;
	}

	public void setBOOKING_STATUS(String bOOKING_STATUS) {
		BOOKING_STATUS = bOOKING_STATUS;
	}

	public String getVEHICLE_NUMBER() {
		return VEHICLE_NUMBER;
	}

	public void setVEHICLE_NUMBER(String vEHICLE_NUMBER) {
		VEHICLE_NUMBER = vEHICLE_NUMBER;
	}

	public String getTRACKING_URL() {
		return TRACKING_URL;
	}

	public void setTRACKING_URL(String tRACKING_URL) {
		TRACKING_URL = tRACKING_URL;
	}

	public String getOTP_FOR_DELIVERY() {
		return OTP_FOR_DELIVERY;
	}

	public void setOTP_FOR_DELIVERY(String oTP_FOR_DELIVERY) {
		OTP_FOR_DELIVERY = oTP_FOR_DELIVERY;
	}

	public Timestamp getCREATED_AT() {
		return CREATED_AT;
	}

	public void setCREATED_AT(Timestamp cREATED_AT) {
		CREATED_AT = cREATED_AT;
	}

	public Long getCREATED_BY() {
		return CREATED_BY;
	}

	public void setCREATED_BY(Long cREATED_BY) {
		CREATED_BY = cREATED_BY;
	}

	public Timestamp getUPDATED_AT() {
		return UPDATED_AT;
	}

	public void setUPDATED_AT(Timestamp uPDATED_AT) {
		UPDATED_AT = uPDATED_AT;
	}

	public Long getUPDATED_BY() {
		return UPDATED_BY;
	}

	public void setUPDATED_BY(Long uPDATED_BY) {
		UPDATED_BY = uPDATED_BY;
	}

	public CustomerDetailsDTO getCustomerDetails() {
		return customerDetails;
	}

	public void setCustomerDetails(CustomerDetailsDTO customerDetails) {
		this.customerDetails = customerDetails;
	}

	public VendorDetailsDTO getVendorDetails() {
		return vendorDetails;
	}

	public void setVendorDetails(VendorDetailsDTO vendorDetails) {
		this.vendorDetails = vendorDetails;
	}

	public List<SelectedItemsDTO> getSelectedItems() {
		return selectedItems;
	}

	public void setSelectedItems(List<SelectedItemsDTO> selectedItems) {
		this.selectedItems = selectedItems;
	}

	public StatusHandler getStatusHandler() {
		return statusHandler;
	}

	public void setStatusHandler(StatusHandler statusHandler) {
		this.statusHandler = statusHandler;
	}
	
	
	
}
