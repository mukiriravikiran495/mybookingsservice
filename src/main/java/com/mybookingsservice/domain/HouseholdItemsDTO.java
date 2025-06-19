package com.mybookingsservice.domain;

import java.time.LocalDateTime;

import com.mybookingsservice.exceptions.StatusHandler;

public class HouseholdItemsDTO {

	private Long itemId;
	private Long itemCode;
	private String itemName;
	private String category;
	private String estCategory;
	private int weight;
	private int qty;
	private Long createdBy;
	private LocalDateTime updatedBy;
	private String isActive;
	private StatusHandler statusHandler;
	public Long getItemId() {
		return itemId;
	}
	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}
	public Long getItemCode() {
		return itemCode;
	}
	public void setItemCode(Long itemCode) {
		this.itemCode = itemCode;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getEstCategory() {
		return estCategory;
	}
	public void setEstCategory(String estCategory) {
		this.estCategory = estCategory;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	public Long getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}
	public LocalDateTime getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(LocalDateTime updatedBy) {
		this.updatedBy = updatedBy;
	}
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	public StatusHandler getStatusHandler() {
		return statusHandler;
	}
	public void setStatusHandler(StatusHandler statusHandler) {
		this.statusHandler = statusHandler;
	}
	
	
	
	
}
