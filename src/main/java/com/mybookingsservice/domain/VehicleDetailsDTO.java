package com.mybookingsservice.domain;

public class VehicleDetailsDTO {

	private Long vendorId;
    private String companyName;
    private String ownerName;
    private String vehicleType;
    private double vehicleWeight;
    private String vehicleUnit;
    private double vehicleSizeValue;
    private String vehicleSizeUnit;
    private double baseFare;
    private double perKmRate;
    private double pickupLatitude;
    private double pickupLongitude;
    private double distance; // in km
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
	public String getVehicleType() {
		return vehicleType;
	}
	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}
	public double getVehicleWeight() {
		return vehicleWeight;
	}
	public void setVehicleWeight(double vehicleWeight) {
		this.vehicleWeight = vehicleWeight;
	}
	public String getVehicleUnit() {
		return vehicleUnit;
	}
	public void setVehicleUnit(String vehicleUnit) {
		this.vehicleUnit = vehicleUnit;
	}
	public double getVehicleSizeValue() {
		return vehicleSizeValue;
	}
	public void setVehicleSizeValue(double vehicleSizeValue) {
		this.vehicleSizeValue = vehicleSizeValue;
	}
	public String getVehicleSizeUnit() {
		return vehicleSizeUnit;
	}
	public void setVehicleSizeUnit(String vehicleSizeUnit) {
		this.vehicleSizeUnit = vehicleSizeUnit;
	}
	public double getBaseFare() {
		return baseFare;
	}
	public void setBaseFare(double baseFare) {
		this.baseFare = baseFare;
	}
	public double getPerKmRate() {
		return perKmRate;
	}
	public void setPerKmRate(double perKmRate) {
		this.perKmRate = perKmRate;
	}
	public double getPickupLatitude() {
		return pickupLatitude;
	}
	public void setPickupLatitude(double pickupLatitude) {
		this.pickupLatitude = pickupLatitude;
	}
	public double getPickupLongitude() {
		return pickupLongitude;
	}
	public void setPickupLongitude(double pickupLongitude) {
		this.pickupLongitude = pickupLongitude;
	}
	public double getDistance() {
		return distance;
	}
	public void setDistance(double distance) {
		this.distance = distance;
	}
	@Override
	public String toString() {
		return "VehicleDetailsDTO [vendorId=" + vendorId + ", companyName=" + companyName + ", ownerName=" + ownerName
				+ ", vehicleType=" + vehicleType + ", vehicleWeight=" + vehicleWeight + ", vehicleUnit=" + vehicleUnit
				+ ", vehicleSizeValue=" + vehicleSizeValue + ", vehicleSizeUnit=" + vehicleSizeUnit + ", baseFare="
				+ baseFare + ", perKmRate=" + perKmRate + ", pickupLatitude=" + pickupLatitude + ", pickupLongitude="
				+ pickupLongitude + ", distance=" + distance + "]";
	}
    
    
}
