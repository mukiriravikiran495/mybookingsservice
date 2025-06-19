package com.mybookingsservice.entity;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;


@Entity
@Table( name = "CUSTOMER_DETAILS", schema = "CUSTOMER")
public class CustomerDetails implements Serializable{


    @Serial
    private static final long serialVersionUID = 4366299903003325817L;

	@Id
	@Column( name = "custId")
	private Long custId;
	
	
	private String cFirstname;
	
	private String cLastname;
	
	private String cMobile;
	
	private String cEmail;
	
	
//	@OneToMany(mappedBy = "customerDetails", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//	@JsonManagedReference
	
	@OneToOne
	@JoinColumn( name = "custId")
    private CustAddress custAddress;
	
	
//	@OneToMany(mappedBy = "customerDetails")
//	@JsonIgnore
//    private Set<MyBookings> bookings;
	
	

	public void setcFirstname(String cFirstname) {
		this.cFirstname = cFirstname;
	}

	public Long getCustId() {
		return custId;
	}

	public void setCustId(Long custId) {
		this.custId = custId;
	}

	public String getcFirstname() {
		return cFirstname;
	}

	public String getcLastname() {
		return cLastname;
	}

	public void setcLastname(String cLastname) {
		this.cLastname = cLastname;
	}

	public String getcMobile() {
		return cMobile;
	}

	public void setcMobile(String cMobile) {
		this.cMobile = cMobile;
	}

	public String getcEmail() {
		return cEmail;
	}

	public void setcEmail(String cEmail) {
		this.cEmail = cEmail;
	}

	public CustAddress getCustAddress() {
		return custAddress;
	}

	public void setCustAddress(CustAddress custAddress) {
		this.custAddress = custAddress;
	}

	public void setCustId(long custId) {
		this.custId = custId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	
}
