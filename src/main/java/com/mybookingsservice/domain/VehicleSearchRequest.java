package com.mybookingsservice.domain;

public class VehicleSearchRequest {

	private Double cPickupLatitude;
    private Double cPickupLongitude;
    private String cCity;
    private String cState;
    private String cZipCode;
    private String vehicleType;
    
	public Double getcPickupLatitude() {
		return cPickupLatitude;
	}
	public void setcPickupLatitude(Double cPickupLatitude) {
		this.cPickupLatitude = cPickupLatitude;
	}
	public Double getcPickupLongitude() {
		return cPickupLongitude;
	}
	public void setcPickupLongitude(Double cPickupLongitude) {
		this.cPickupLongitude = cPickupLongitude;
	}
	public String getcCity() {
		return cCity;
	}
	public void setcCity(String cCity) {
		this.cCity = cCity;
	}
	public String getcState() {
		return cState;
	}
	public void setcState(String cState) {
		this.cState = cState;
	}
	public String getcZipCode() {
		return cZipCode;
	}
	public void setcZipCode(String cZipCode) {
		this.cZipCode = cZipCode;
	}
	public String getVehicleType() {
		return vehicleType;
	}
	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}
	@Override
	public String toString() {
		return "VehicleSearchRequest [cPickupLatitude=" + cPickupLatitude + ", cPickupLongitude=" + cPickupLongitude
				+ ", cCity=" + cCity + ", cState=" + cState + ", cZipCode=" + cZipCode + ", vehicleType=" + vehicleType
				+ "]";
	}
    
    
}
