package com.mybookingsservice.entity;


import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table( name = "VENDOR_ADDRESS", schema = "VENDOR")
public class VendorAddress implements Serializable{
	
	private static final long serialVersionUID = 7820591758908747440L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "VENDOR_ADDRESS_SEQ_GEN")
	@SequenceGenerator(name = "VENDOR_ADDRESS_SEQ_GEN", sequenceName = "VENDOR.VENDOR_ADDRESS_SEQ", allocationSize = 1)
	private Long vAddressId;
	private String vAddress1;
	private String vCity;
	private String vState;
	private String vZipcode;
	private int basePricePerKm;
    private int pricePerKg;
    private int avgDeliveryTimeInDays;
    private int estimatedPrice;
    private LocalDateTime estimatedDeliveryDate;

    @OneToOne(mappedBy = "vendorAddress", cascade = CascadeType.ALL)
    @JoinColumn(name = "vendorId")
    private VendorDetails vendorDetails;

	
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

	public int getBasePricePerKm() {
		return basePricePerKm;
	}


	public void setBasePricePerKm(int basePricePerKm) {
		this.basePricePerKm = basePricePerKm;
	}


	public int getPricePerKg() {
		return pricePerKg;
	}


	public void setPricePerKg(int pricePerKg) {
		this.pricePerKg = pricePerKg;
	}


	public int getAvgDeliveryTimeInDays() {
		return avgDeliveryTimeInDays;
	}


	public void setAvgDeliveryTimeInDays(int avgDeliveryTimeInDays) {
		this.avgDeliveryTimeInDays = avgDeliveryTimeInDays;
	}


	public int getEstimatedPrice() {
		return estimatedPrice;
	}


	public void setEstimatedPrice(int estimatedPrice) {
		this.estimatedPrice = estimatedPrice;
	}


	public LocalDateTime getEstimatedDeliveryDate() {
		return estimatedDeliveryDate;
	}


	public void setEstimatedDeliveryDate(LocalDateTime estimatedDeliveryDate) {
		this.estimatedDeliveryDate = estimatedDeliveryDate;
	}


	public VendorDetails getVendorDetails() {
		return vendorDetails;
	}


	public void setVendorDetails(VendorDetails vendorDetails) {
		this.vendorDetails = vendorDetails;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public VendorAddress() {
		
	}

	
	
}
