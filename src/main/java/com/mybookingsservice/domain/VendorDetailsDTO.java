package com.mybookingsservice.domain;

import java.time.LocalDateTime;

public class VendorDetailsDTO {
	
    private Long vendorId;
    private String companyName;
    private String ownerName;
    private String vMobile;
    private String vEmail;
    private String vAddress1;
    private String vAddress2;
    private String vCity;
    private String vState;
    private String vZipCode;
    private LocalDateTime createdAt;
    private Long createdBy;
    private LocalDateTime updatedAt;
    private Long updatedBy;
    private Double vPickupLatitude;
    private Double vPickupLongitude;
    private String vPImage;
    private String vPImageUrl;
    private String vPImageFileName;
    private String vPImageFileExtension;
    private String vendorType;
    private String vehicleType;
    private Double vehicleWeight;
    private String vehicleUnit;
    private Double vehicleSizeValue;
    private String vehicleSizeUnit;
    private LocalDateTime companyRegistrationDate;
    private String vehicleNumber;
    private Double baseFare;
    private Double perKmRate;
    private Double perKgRate;

	@Override
	public String toString() {
		return "VendorDetailsDTO [vendorId=" + vendorId + ", companyName=" + companyName + ", ownerName=" + ownerName
				+ ", vMobile=" + vMobile + ", vEmail=" + vEmail + ", vAddress1=" + vAddress1 + ", vAddress2="
				+ vAddress2 + ", vCity=" + vCity + ", vState=" + vState + ", vZipCode=" + vZipCode + ", createdAt="
				+ createdAt + ", createdBy=" + createdBy + ", updatedAt=" + updatedAt + ", updatedBy=" + updatedBy
				+ ", vPickupLatitude=" + vPickupLatitude + ", vPickupLongitude=" + vPickupLongitude + ", vPImage="
				+ vPImage + ", vPImageUrl=" + vPImageUrl + ", vPImageFileName=" + vPImageFileName
				+ ", vPImageFileExtension=" + vPImageFileExtension + ", vendorType=" + vendorType + ", vehicleType="
				+ vehicleType + ", vehicleWeight=" + vehicleWeight + ", vehicleUnit=" + vehicleUnit
				+ ", vehicleSizeValue=" + vehicleSizeValue + ", vehicleSizeUnit=" + vehicleSizeUnit
				+ ", companyRegistrationDate=" + companyRegistrationDate + "]";
	}
	
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
	
	public Double getBaseFare() {
		return baseFare;
	}

	public void setBaseFare(Double baseFare) {
		this.baseFare = baseFare;
	}

	public String getVehicleNumber() {
		return vehicleNumber;
	}

	public void setVehicleNumber(String vehicleNumber) {
		this.vehicleNumber = vehicleNumber;
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
	public String getvAddress1() {
		return vAddress1;
	}
	public void setvAddress1(String vAddress1) {
		this.vAddress1 = vAddress1;
	}
	public String getvAddress2() {
		return vAddress2;
	}
	public void setvAddress2(String vAddress2) {
		this.vAddress2 = vAddress2;
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
	public String getvZipCode() {
		return vZipCode;
	}
	public void setvZipCode(String vZipCode) {
		this.vZipCode = vZipCode;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	public Long getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}
	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
	public Long getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(Long updatedBy) {
		this.updatedBy = updatedBy;
	}
	public Double getvPickupLatitude() {
		return vPickupLatitude;
	}
	public void setvPickupLatitude(Double vPickupLatitude) {
		this.vPickupLatitude = vPickupLatitude;
	}
	public Double getvPickupLongitude() {
		return vPickupLongitude;
	}
	public void setvPickupLongitude(Double vPickupLongitude) {
		this.vPickupLongitude = vPickupLongitude;
	}
	public String getvPImage() {
		return vPImage;
	}
	public void setvPImage(String vPImage) {
		this.vPImage = vPImage;
	}
	public String getvPImageUrl() {
		return vPImageUrl;
	}
	public void setvPImageUrl(String vPImageUrl) {
		this.vPImageUrl = vPImageUrl;
	}
	public String getvPImageFileName() {
		return vPImageFileName;
	}
	public void setvPImageFileName(String vPImageFileName) {
		this.vPImageFileName = vPImageFileName;
	}
	public String getvPImageFileExtension() {
		return vPImageFileExtension;
	}
	public void setvPImageFileExtension(String vPImageFileExtension) {
		this.vPImageFileExtension = vPImageFileExtension;
	}
	public String getVendorType() {
		return vendorType;
	}
	public void setVendorType(String vendorType) {
		this.vendorType = vendorType;
	}
	public String getVehicleType() {
		return vehicleType;
	}
	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}
	public Double getVehicleWeight() {
		return vehicleWeight;
	}
	public void setVehicleWeight(Double vehicleWeight) {
		this.vehicleWeight = vehicleWeight;
	}
	public String getVehicleUnit() {
		return vehicleUnit;
	}
	public void setVehicleUnit(String vehicleUnit) {
		this.vehicleUnit = vehicleUnit;
	}
	public Double getVehicleSizeValue() {
		return vehicleSizeValue;
	}
	public void setVehicleSizeValue(Double vehicleSizeValue) {
		this.vehicleSizeValue = vehicleSizeValue;
	}
	public String getVehicleSizeUnit() {
		return vehicleSizeUnit;
	}
	public void setVehicleSizeUnit(String vehicleSizeUnit) {
		this.vehicleSizeUnit = vehicleSizeUnit;
	}
	public LocalDateTime getCompanyRegistrationDate() {
		return companyRegistrationDate;
	}
	public void setCompanyRegistrationDate(LocalDateTime companyRegistrationDate) {
		this.companyRegistrationDate = companyRegistrationDate;
	}

	public Double getPerKmRate() {
		return perKmRate;
	}

	public void setPerKmRate(Double perKmRate) {
		this.perKmRate = perKmRate;
	}

	public Double getPerKgRate() {
		return perKgRate;
	}

	public void setPerKgRate(Double perKgRate) {
		this.perKgRate = perKgRate;
	}
    
}