package com.mybookingsservice.entity;



import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table( name = "CUST_ADDRESS", schema = "CUSTOMER")
public class CustAddress {

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	private long c_address_id;
	
	private String c_address1;
	
	private String c_city;
	
	private String c_state;
	
	private String c_zipcode;
	
	@OneToOne
	@JoinColumn(name = "custId")
	@JsonBackReference
    private CustomerDetails customerDetails;

	public CustAddress() {
		
	}

	

	public long getC_address_id() {
		return c_address_id;
	}



	public void setC_address_id(long c_address_id) {
		this.c_address_id = c_address_id;
	}



	public String getC_address1() {
		return c_address1;
	}

	public void setC_address1(String c_address1) {
		this.c_address1 = c_address1;
	}

	public String getC_city() {
		return c_city;
	}

	public void setC_city(String c_city) {
		this.c_city = c_city;
	}

	public String getC_state() {
		return c_state;
	}

	public void setC_state(String c_state) {
		this.c_state = c_state;
	}

	public String getC_zipcode() {
		return c_zipcode;
	}

	public void setC_zipcode(String c_zipcode) {
		this.c_zipcode = c_zipcode;
	}



	public CustomerDetails getCustomerDetails() {
		return customerDetails;
	}



	public void setCustomerDetails(CustomerDetails customerDetails) {
		this.customerDetails = customerDetails;
	}

	
	
}
