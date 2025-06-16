package com.mybookingsservice.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table( name = "selecteditems")
public class SelectedItems {

	@Id
    @Column(name = "ITEMID")
    private long itemId;

    @Column(name = "ITEMCODE")
    private long itemCode;

    @Column(name = "ITEMNAME")
    private String itemName;

    @Column(name = "CATEGORY")
    private String category;

    @Column(name = "ESTCATEGORY")
    private String estCategory;

    @Column(name = "WEIGHT")
    private String weight; // DB shows '3kg' → must be String

    @Column(name = "QTY")
    private long qty;

    @Column(name = "CREATEDBY")
    private String createdBy;

    @Column(name = "UPDATEDBY")
    private String updatedBy;

    @Column(name = "ISACTIVE")
    private String isActive;
	
    @ManyToOne
    @JoinColumn(name = "bookingId")
    @JsonBackReference
    private MyBookings booking;

	public long getItemId() {
		return itemId;
	}

	public void setItemId(long itemId) {
		this.itemId = itemId;
	}

	public long getItemCode() {
		return itemCode;
	}

	public void setItemCode(long itemCode) {
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

	public long getQty() {
		return qty;
	}

	public void setQty(long qty) {
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

	public MyBookings getBooking() {
		return booking;
	}

	public void setBooking(MyBookings booking) {
		this.booking = booking;
	}
    
    
    
}
