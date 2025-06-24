package com.mybookingsservice.entity;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table( name = "vendor_service_area", schema = "VENDOR")
public class VendorServiceArea {
	
	@Id
	private Long vServiceId;
	private String vAddress1;
	private String vCity;
	private String vState;
	private String vZipcode;
	private int basePricePerKm;
    private int pricePerKg;
    private int avgDeliveryTimeInDays;
    private int estimatedPrice;
    private LocalDateTime estimatedDeliveryDate;
    
	@ManyToOne
    @JoinColumn(name = "vendorId")
    @JsonBackReference
    private Vendor vendor;

	public Long getvServiceId() {
		return vServiceId;
	}

	public void setvServiceId(Long vServiceId) {
		this.vServiceId = vServiceId;
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

	public Vendor getVendor() {
		return vendor;
	}

	public void setVendor(Vendor vendor) {
		this.vendor = vendor;
	}

	
	
}
