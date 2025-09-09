package com.mybookingsservice.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


public class AvailableVehiclesDTO {
 
    private Long vehicleId;
    private String vehicleName;
    private BigDecimal vehicleWeight;
    private String vehicleWeightUnit;
    private BigDecimal length;
    private BigDecimal width;
    private BigDecimal height;
    private String vehicleSizeUnit;
    private BigDecimal baseFare;
    private BigDecimal perKmRate;
    private LocalDateTime createdAt;
    private Long createdBy;
    private BigDecimal fare;
    private Double distance;

	public Long getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(Long vehicleId) {
		this.vehicleId = vehicleId;
	}

	public String getVehicleName() {
		return vehicleName;
	}

	public void setVehicleName(String vehicleName) {
		this.vehicleName = vehicleName;
	}

	public BigDecimal getVehicleWeight() {
		return vehicleWeight;
	}

	public void setVehicleWeight(BigDecimal vehicleWeight) {
		this.vehicleWeight = vehicleWeight;
	}

	public String getVehicleWeightUnit() {
		return vehicleWeightUnit;
	}

	public void setVehicleWeightUnit(String vehicleWeightUnit) {
		this.vehicleWeightUnit = vehicleWeightUnit;
	}

	public BigDecimal getLength() {
		return length;
	}

	public Double getDistance() {
		return distance;
	}

	public void setDistance(Double distance) {
		this.distance = distance;
	}

	public void setLength(BigDecimal length) {
		this.length = length;
	}

	public BigDecimal getWidth() {
		return width;
	}

	public void setWidth(BigDecimal width) {
		this.width = width;
	}

	public BigDecimal getHeight() {
		return height;
	}

	public void setHeight(BigDecimal height) {
		this.height = height;
	}

	public String getVehicleSizeUnit() {
		return vehicleSizeUnit;
	}

	public void setVehicleSizeUnit(String vehicleSizeUnit) {
		this.vehicleSizeUnit = vehicleSizeUnit;
	}

	public BigDecimal getBaseFare() {
		return baseFare;
	}

	public void setBaseFare(BigDecimal baseFare) {
		this.baseFare = baseFare;
	}

	public BigDecimal getPerKmRate() {
		return perKmRate;
	}

	public void setPerKmRate(BigDecimal perKmRate) {
		this.perKmRate = perKmRate;
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

	public BigDecimal getFare() {
		return fare;
	}

	public void setFare(BigDecimal fare) {
		this.fare = fare;
	}
    
}