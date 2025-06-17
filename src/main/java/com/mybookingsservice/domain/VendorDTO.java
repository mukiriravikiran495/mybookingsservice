package com.mybookingsservice.domain;

import java.util.List;

import com.mybookingsservice.entity.VendorServiceArea;



public class VendorDTO {

	private long vendorId;
	private String v_firstname;
	private String v_lastname;
	private String v_mobile;
	private String v_email;
	
    private List<VendorServiceAreaDTO> vendorServiceAreaDTO;


	public long getVendorId() {
		return vendorId;
	}


	public void setVendorId(long vendorId) {
		this.vendorId = vendorId;
	}


	public String getV_firstname() {
		return v_firstname;
	}


	public void setV_firstname(String v_firstname) {
		this.v_firstname = v_firstname;
	}


	public String getV_lastname() {
		return v_lastname;
	}


	public void setV_lastname(String v_lastname) {
		this.v_lastname = v_lastname;
	}


	public String getV_mobile() {
		return v_mobile;
	}


	public void setV_mobile(String v_mobile) {
		this.v_mobile = v_mobile;
	}


	public String getV_email() {
		return v_email;
	}


	public void setV_email(String v_email) {
		this.v_email = v_email;
	}


	public List<VendorServiceAreaDTO> getVendorServiceAreaDTO() {
		return vendorServiceAreaDTO;
	}


	public void setVendorServiceAreaDTO(List<VendorServiceAreaDTO> vendorServiceAreaDTO) {
		this.vendorServiceAreaDTO = vendorServiceAreaDTO;
	}


}
