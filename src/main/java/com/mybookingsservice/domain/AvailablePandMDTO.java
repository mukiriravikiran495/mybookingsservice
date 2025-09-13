package com.mybookingsservice.domain;

import java.time.LocalDateTime;

public class AvailablePandMDTO {

	private Long vendorId;
	private String companyName;
	private String ownerName;
	private String vmobile;
	private Double fare;
	private LocalDateTime estimatedDrop;
	private Long companyDiscount;
	private Long vendorDiscount;
	private String discountType;
	private String vpimageurl;
	public Long getVendorId() {
		return vendorId;
	}
	public void setVendorId(Long vendorId) {
		this.vendorId = vendorId;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getOwnerName() {
		return ownerName;
	}
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}
	public String getVmobile() {
		return vmobile;
	}
	public void setVmobile(String vmobile) {
		this.vmobile = vmobile;
	}
	public Double getFare() {
		return fare;
	}
	public void setFare(Double fare) {
		this.fare = fare;
	}
	public LocalDateTime getEstimatedDrop() {
		return estimatedDrop;
	}
	public void setEstimatedDrop(LocalDateTime estimatedDrop) {
		this.estimatedDrop = estimatedDrop;
	}
	public Long getCompanyDiscount() {
		return companyDiscount;
	}
	public void setCompanyDiscount(Long companyDiscount) {
		this.companyDiscount = companyDiscount;
	}
	public Long getVendorDiscount() {
		return vendorDiscount;
	}
	public void setVendorDiscount(Long vendorDiscount) {
		this.vendorDiscount = vendorDiscount;
	}
	public String getDiscountType() {
		return discountType;
	}
	public void setDiscountType(String discountType) {
		this.discountType = discountType;
	}
	public String getVpimageurl() {
		return vpimageurl;
	}
	public void setVpimageurl(String vpimageurl) {
		this.vpimageurl = vpimageurl;
	}
	
	
	
}
