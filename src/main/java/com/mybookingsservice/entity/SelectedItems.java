package com.mybookingsservice.entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table( name = "selecteditems")
public class SelectedItems {

	@Id
    @Column(name = "ITEMID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "selecteditems_seq_gen")
	@SequenceGenerator(name = "selecteditems_seq_gen", sequenceName = "selecteditems_seq", allocationSize = 1)
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
    private int weight; // DB shows '3kg' → must be String

    @Column(name = "QTY")
    private Long qty;

    @Column(name = "CREATEDBY")
    private Long createdBy;

    @Column(name = "UPDATEDBY")
    private Long updatedBy;
    
    @Column(name = "CREATEDAT")
    private LocalDateTime createdAt;

    @Column(name = "UPDATEDAT")
    private LocalDateTime updatedAt;

    @Column(name = "ISACTIVE")
    private String isActive;
	
    @Column(name = "BOOKINGID")
    private Long bookingId;

	public Long getBookingId() {
		return bookingId;
	}

	public void setBookingId(Long bookingId) {
		this.bookingId = bookingId;
	}

	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public Long getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Long updatedBy) {
		this.updatedBy = updatedBy;
	}


	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

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

	public Long getQty() {
		return qty;
	}

	public void setQty(Long qty) {
		this.qty = qty;
	}

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	
}
