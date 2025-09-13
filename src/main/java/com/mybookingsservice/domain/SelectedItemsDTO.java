package com.mybookingsservice.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;

public class SelectedItemsDTO {

	private Long itemId;
    private String itemName;
    private Long itemQuantity;
    private String itemCategory;
    private BigDecimal itemWeight;
    private String itemWeightUnit;
    private LocalDateTime createdAt;
    private Long createdBy;
    private LocalDateTime updatedAt;
    private Long updatedBy;
    
	public Long getItemId() {
		return itemId;
	}
	
	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}
	
	public String getItemName() {
		return itemName;
	}
	
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	
	public Long getItemQuantity() {
		return itemQuantity;
	}

	public void setItemQuantity(Long itemQuantity) {
		this.itemQuantity = itemQuantity;
	}

	public String getItemCategory() {
		return itemCategory;
	}
	
	public void setItemCategory(String itemCategory) {
		this.itemCategory = itemCategory;
	}
	
	public BigDecimal getItemWeight() {
		return itemWeight;
	}
	
	public void setItemWeight(BigDecimal itemWeight) {
		this.itemWeight = itemWeight;
	}
	
	public String getItemWeightUnit() {
		return itemWeightUnit;
	}
	
	public void setItemWeightUnit(String itemWeightUnit) {
		this.itemWeightUnit = itemWeightUnit;
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
    
	
}
