package com.mybookingsservice.entity;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table( name = "VENDOR_DETAILS", schema = "VENDOR")
public class VendorDetails implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	private Long vendorId;
	private String vFirstname;
	private String vLastname;
	private String vMobile;
	private String vEmail;
	
	@OneToOne
	@JoinColumn(name = "vendorId")
    private VendorAddress vendorAddress;
	

//	@OneToMany(mappedBy = "vendorDetails", cascade = CascadeType.ALL)
//	@JsonIgnore
//    private List<MyBookings> bookings;
//	
	
	
	
	public VendorAddress getVendorAddress() {
		return vendorAddress;
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public void setVendorAddress(VendorAddress vendorAddress) {
		this.vendorAddress = vendorAddress;
	}
	
	public Long getVendorId() {
		return vendorId;
	}
	public void setVendorId(Long vendorId) {
		this.vendorId = vendorId;
	}
	public VendorDetails() {
		
	}
	
	
	
	
}
