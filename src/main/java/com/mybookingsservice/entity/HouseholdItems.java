package com.mybookingsservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
 

@Entity
@Table( name = "householditems")
public class HouseholdItems {
	
	@Id
    @Column(name = "ITEMID")
    private Long itemId;

    @Column(name = "ITEMCODE")
    private Long itemCode;

    @Column(name = "ITEMNAME")
    private String itemName;

    @Column(name = "CATEGORY")
    private String category;

    @Column(name = "ESTCATEGORY")
    private String estCategory;

    @Column(name = "WEIGHT")
    private String weight; // DB shows '3kg' → must be String

    @Column(name = "QTY")
    private Long qty;

    @Column(name = "CREATEDBY")
    private String createdBy;

    @Column(name = "UPDATEDBY")
    private String updatedBy;

    @Column(name = "ISACTIVE")
    private String isActive;

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

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public Long getQty() {
		return qty;
	}

	public void setQty(Long qty) {
		this.qty = qty;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	
	
	
}
