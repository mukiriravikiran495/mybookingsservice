package com.mybookingsservice.domain;

import java.util.List;
import java.util.Set;

public class CustomerDetailsDTO {

	private String custid;
	private String c_firstName;
	private String c_lastName;
	private String c_mobile;
	private String c_email;
	private CustAddressDTO custAddress;
//	private Set<CustAddressDTO> custAddress;
	
	public String getC_firstName() {
		return c_firstName;
	}
	public String getCustid() {
		return custid;
	}
	public void setCustid(String custid) {
		this.custid = custid;
	}
	public void setC_firstName(String c_firstName) {
		this.c_firstName = c_firstName;
	}
	public String getC_lastName() {
		return c_lastName;
	}
	public void setC_lastName(String c_lastName) {
		this.c_lastName = c_lastName;
	}
	public String getC_mobile() {
		return c_mobile;
	}
	public void setC_mobile(String c_mobile) {
		this.c_mobile = c_mobile;
	}
	public String getC_email() {
		return c_email;
	}
	public CustAddressDTO getCustAddress() {
		return custAddress;
	}
	public void setCustAddress(CustAddressDTO custAddress) {
		this.custAddress = custAddress;
	}
	public void setC_email(String c_email) {
		this.c_email = c_email;
	}
	@Override
	public String toString() {
		return "CustomerDetailsDTO [custid=" + custid + ", c_firstName=" + c_firstName + ", c_lastName=" + c_lastName
				+ ", c_mobile=" + c_mobile + ", c_email=" + c_email + ", custAddress=" + custAddress + "]";
	}
	
	
}
