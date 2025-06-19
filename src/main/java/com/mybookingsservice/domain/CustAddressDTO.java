package com.mybookingsservice.domain;


public class CustAddressDTO {

	private Long cAddressId;
    private String cAddress1;
    private String cCity;
    private String cState;
    private String cZipcode;
	
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

	public CustAddressDTO() {
		
	}
	
	
	
}
