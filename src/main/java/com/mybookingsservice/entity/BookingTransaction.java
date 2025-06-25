package com.mybookingsservice.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "BOOKING_TRANSACTIONS", schema = "MyBookings")
public class BookingTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transaction_seq_gen")
    @SequenceGenerator(name = "transaction_seq_gen", sequenceName = "TRANSACTION_SEQ", allocationSize = 1)
    private Long transactionId;

    @ManyToOne(fetch = FetchType.LAZY , optional = false)
    @JoinColumn(name = "bookingId", nullable = false)
    private MyBookings booking;

    private String transactionRef;
    private String transactionType; // PAYMENT, REFUND, etc.
    private BigDecimal amount;
    private String currency = "INR";
    private String status; // SUCCESS, FAILED, PENDING
    private String paymentMode;
    private String responseMessage;

    private LocalDateTime createdAt = LocalDateTime.now();
    private String createdBy;
    
	public Long getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
	}
	public MyBookings getBooking() {
		return booking;
	}
	public void setBooking(MyBookings booking) {
		this.booking = booking;
	}
	public String getTransactionRef() {
		return transactionRef;
	}
	public void setTransactionRef(String transactionRef) {
		this.transactionRef = transactionRef;
	}
	public String getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getPaymentMode() {
		return paymentMode;
	}
	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}
	public String getResponseMessage() {
		return responseMessage;
	}
	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	@Override
	public String toString() {
		return "BookingTransaction [transactionId=" + transactionId + ", booking=" + booking + ", transactionRef="
				+ transactionRef + ", transactionType=" + transactionType + ", amount=" + amount + ", currency="
				+ currency + ", status=" + status + ", paymentMode=" + paymentMode + ", responseMessage="
				+ responseMessage + ", createdAt=" + createdAt + ", createdBy=" + createdBy + "]";
	}
    
    
    
}