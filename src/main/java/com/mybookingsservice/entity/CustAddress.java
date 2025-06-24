package com.mybookingsservice.entity;



import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table( name = "CUST_ADDRESS", schema = "CUSTOMER")
public class CustAddress {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cust_address_seq_gen")
	@SequenceGenerator(name = "cust_address_seq_gen", sequenceName = "CUSTOMER.CUST_ADDRESS_SEQ", allocationSize = 1)
	private Long cAddressId;
	
	private String cAddress1;
	
	private String cCity;
	
	private String cState;
	
	private String cZipcode;
	
//	@OneToOne(mappedBy = "custAddress", cascade = CascadeType.ALL)
//    @JoinColumn(name = "custId")
//    private CustomerDetails customerDetails;

	public CustAddress() {
		
	}

	
	public Long getcAddressId() {
		return cAddressId;
	}


	public void setcAddressId(Long cAddressId) {
		this.cAddressId = cAddressId;
	}


	public String getcAddress1() {
		return cAddress1;
	}

	public void setcAddress1(String cAddress1) {
		this.cAddress1 = cAddress1;
	}

	public String getcCity() {
		return cCity;
	}

	public void setcCity(String cCity) {
		this.cCity = cCity;
	}

	public String getcState() {
		return cState;
	}

	public void setcState(String cState) {
		this.cState = cState;
	}

	public String getcZipcode() {
		return cZipcode;
	}

	public void setcZipcode(String cZipcode) {
		this.cZipcode = cZipcode;
	}

	
}
