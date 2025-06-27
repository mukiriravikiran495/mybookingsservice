package com.mybookingsservice.domain;


public class VendorDTO {

	private Long vendorId;
	private String vfirstname;
	private String vLastname;
	private String vMobile;
	private String vEmail;
	private String vAddress1;
	private String vCity;
	private String vState;
	private String vZipcode;
	private int vBasePricePerKM;
	private int vpricePerKG;
	private int vEstimatedPrice;
	private int vAvgDeliveryTimeInDays;
	private String vEstimatedDeliveryDate;
    

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

	public int getvBasePricePerKM() {
		return vBasePricePerKM;
	}

	public void setvBasePricePerKM(int vBasePricePerKM) {
		this.vBasePricePerKM = vBasePricePerKM;
	}

	public int getVpricePerKG() {
		return vpricePerKG;
	}

	public void setVpricePerKG(int vpricePerKG) {
		this.vpricePerKG = vpricePerKG;
	}

	public int getvEstimatedPrice() {
		return vEstimatedPrice;
	}

	public void setvEstimatedPrice(int vEstimatedPrice) {
		this.vEstimatedPrice = vEstimatedPrice;
	}

	public int getvAvgDeliveryTimeInDays() {
		return vAvgDeliveryTimeInDays;
	}

	public void setvAvgDeliveryTimeInDays(int vAvgDeliveryTimeInDays) {
		this.vAvgDeliveryTimeInDays = vAvgDeliveryTimeInDays;
	}

	public String getvEstimatedDeliveryDate() {
		return vEstimatedDeliveryDate;
	}

	public void setvEstimatedDeliveryDate(String vEstimatedDeliveryDate) {
		this.vEstimatedDeliveryDate = vEstimatedDeliveryDate;
	}

	public Long getVendorId() {
		return vendorId;
	}

	public void setVendorId(Long vendorId) {
		this.vendorId = vendorId;
	}

	public String getVfirstname() {
		return vfirstname;
	}

	public void setVfirstname(String vfirstname) {
		this.vfirstname = vfirstname;
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

	@Override
	public String toString() {
		return "VendorDTO [vendorId=" + vendorId + ", vfirstname=" + vfirstname + ", vLastname=" + vLastname
				+ ", vMobile=" + vMobile + ", vEmail=" + vEmail + ", vAddress1=" + vAddress1 + ", vCity=" + vCity
				+ ", vState=" + vState + ", vZipcode=" + vZipcode + ", vBasePricePerKM=" + vBasePricePerKM
				+ ", vpricePerKG=" + vpricePerKG + ", vEstimatedPrice=" + vEstimatedPrice + ", vAvgDeliveryTimeInDays="
				+ vAvgDeliveryTimeInDays + ", vEstimatedDeliveryDate=" + vEstimatedDeliveryDate + "]";
	}

}
