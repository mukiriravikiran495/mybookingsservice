package com.mybookingsservice.domain;

import java.util.List;

public class CustomerDetailsDTO {

	private Long custId;
	private String cFirstname;
	private String cLastname;
	private String cMobile;
	private String cEmail;
	private CustAddressDTO custAddress;
//	private Set<CustAddressDTO> custAddress;
	public Long getCustId() {
		return custId;
	}
	public void setCustId(Long custId) {
		this.custId = custId;
	}
	public String getcFirstname() {
		return cFirstname;
	}
	public void setcFirstname(String cFirstname) {
		this.cFirstname = cFirstname;
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
	public CustAddressDTO getCustAddress() {
		return custAddress;
	}
	public void setCustAddress(CustAddressDTO custAddress) {
		this.custAddress = custAddress;
	}
	
	
}
