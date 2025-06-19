package com.mybookingsservice.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table( name = "VENDOR", schema = "VENDOR")
public class Vendor {

	@Id
	private Long vendorId;
	private String vFirstname;
	private String vLastname;
	private String vMobile;
	private String vEmail;
	
	
	@OneToMany(mappedBy = "vendor", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<VendorServiceArea> vendorServiceArea;


	public Long getVendorId() {
		return vendorId;
	}


	public void setVendorId(Long vendorId) {
		this.vendorId = vendorId;
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


	public List<VendorServiceArea> getVendorServiceArea() {
		return vendorServiceArea;
	}


	public void setVendorServiceArea(List<VendorServiceArea> vendorServiceArea) {
		this.vendorServiceArea = vendorServiceArea;
	}



}
