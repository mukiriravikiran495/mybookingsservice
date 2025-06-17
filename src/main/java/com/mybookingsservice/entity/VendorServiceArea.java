package com.mybookingsservice.entity;

import java.sql.Timestamp;

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
	private long v_service_id;
	private String v_address1;
	private String v_city;
	private String v_state;
	private String v_zipcode;
	private long basePricePerKm;
    private long pricePerKg;
    private long avgDeliveryTimeInDays;
    private double estimatedPrice;
    private Timestamp estimatedDeliveryDate;
    
	@ManyToOne
    @JoinColumn(name = "vendorId")
    @JsonBackReference
    private Vendor vendor;

	public Timestamp getEstimatedDeliveryDate() {
		return estimatedDeliveryDate;
	}

	public void setEstimatedDeliveryDate(Timestamp estimatedDeliveryDate) {
		this.estimatedDeliveryDate = estimatedDeliveryDate;
	}

	public double getEstimatedPrice() {
		return estimatedPrice;
	}

	public void setEstimatedPrice(double estimatedPrice) {
		this.estimatedPrice = estimatedPrice;
	}

	public long getV_service_id() {
		return v_service_id;
	}

	public void setV_service_id(long v_service_id) {
		this.v_service_id = v_service_id;
	}

	public String getV_address1() {
		return v_address1;
	}

	public void setV_address1(String v_address1) {
		this.v_address1 = v_address1;
	}

	public String getV_city() {
		return v_city;
	}

	public void setV_city(String v_city) {
		this.v_city = v_city;
	}

	public String getV_state() {
		return v_state;
	}

	public void setV_state(String v_state) {
		this.v_state = v_state;
	}

	public String getV_zipcode() {
		return v_zipcode;
	}

	public void setV_zipcode(String v_zipcode) {
		this.v_zipcode = v_zipcode;
	}

	public Vendor getVendor() {
		return vendor;
	}

	public void setVendor(Vendor vendor) {
		this.vendor = vendor;
	}

	public long getBasePricePerKm() {
		return basePricePerKm;
	}

	public void setBasePricePerKm(long basePricePerKm) {
		this.basePricePerKm = basePricePerKm;
	}

	public long getPricePerKg() {
		return pricePerKg;
	}

	public void setPricePerKg(long pricePerKg) {
		this.pricePerKg = pricePerKg;
	}

	public long getAvgDeliveryTimeInDays() {
		return avgDeliveryTimeInDays;
	}

	public void setAvgDeliveryTimeInDays(long avgDeliveryTimeInDays) {
		this.avgDeliveryTimeInDays = avgDeliveryTimeInDays;
	}

	

}
