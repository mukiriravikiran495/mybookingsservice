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
@Table(name = "AVAILABLE_ITEMS")
public class AvailableItems {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ITEM_ID", nullable = false)
    private Long itemId;

    @Column(name = "ITEM_NAME", nullable = false, length = 100)
    private String itemName;

    @Column(name = "ITEM_CATEGORY", length = 50)
    private String itemCategory;

    @Column(name = "ITEM_WEIGHT", precision = 10, scale = 2)
    private BigDecimal itemWeight;

    @Column(name = "ITEM_WEIGHT_UNIT", length = 10)
    private String itemWeightUnit;

    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt;

    @Column(name = "CREATED_BY")
    private Long createdBy;

    @Column(name = "UPDATED_AT")
    private LocalDateTime updatedAt;

    @Column(name = "UPDATED_BY")
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