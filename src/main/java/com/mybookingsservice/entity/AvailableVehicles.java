package com.mybookingsservice.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "AVAIABLE_VEHICLES")
public class AvailableVehicles {

    @Id
    @Column(name = "VEHICLEID", nullable = false)
    private Long vehicleId;

    @Column(name = "VEHICLE_NAME", nullable = false, length = 100)
    private String vehicleName;

    @Column(name = "VEHICLE_WEIGHT", precision = 10, scale = 2)
    private BigDecimal vehicleWeight;

    @Column(name = "VEHICLE_WEIGHT_UNIT", length = 10)
    private String vehicleWeightUnit;

    @Column(name = "LENGTH", precision = 5, scale = 2)
    private BigDecimal length;

    @Column(name = "WIDTH", precision = 5, scale = 2)
    private BigDecimal width;

    @Column(name = "HEIGHT", precision = 5, scale = 2)
    private BigDecimal height;

    @Column(name = "VEHICLE_SIZE_UNIT", length = 10)
    private String vehicleSizeUnit;

    @Column(name = "BASE_FARE", precision = 10, scale = 2)
    private BigDecimal baseFare;

    @Column(name = "PER_KM_RATE", precision = 10, scale = 2)
    private BigDecimal perKmRate;

    @Column(name = "CREATEDAT")
    private LocalDateTime createdAt;

    @Column(name = "CREATEDBY")
    private Long createdBy;

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
    
}