package com.mybookingsservice.domain;

import java.util.List;

public class VendorDetailsDTO {

	private long vendorId;
	private String vFirstname;
	private String vLastname;
	private String vMobile;
	private String vEmail;
//	private List<VendorAddressDTO> vendorAddress;
	private VendorAddressDTO vendorAddress;
	
	
	
	public long getVendorId() {
		return vendorId;
	}
	public void setVendorId(long vendorId) {
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
	public VendorAddressDTO getVendorAddress() {
		return vendorAddress;
	}
	public void setVendorAddress(VendorAddressDTO vendorAddress) {
		this.vendorAddress = vendorAddress;
	}
	
}
