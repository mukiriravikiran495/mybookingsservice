package com.mybookingsservice.domain;

import java.time.LocalDateTime;

public class VendorTranLogRequest {

	private String tokenUuid;
    private Long vendorId;
    private String mobile;
    private String accessToken;
    private LocalDateTime issuedAt;
    private LocalDateTime expiresAt;
    private String isActive;
    private String deviceId;
    private String deviceName;
    private String appId;
    private String correlationId;
    private String errorMessage;
    private String action;
    private String status;
	public String getTokenUuid() {
		return tokenUuid;
	}
	public void setTokenUuid(String tokenUuid) {
		this.tokenUuid = tokenUuid;
	}
	public Long getVendorId() {
		return vendorId;
	}
	public void setVendorId(Long vendorId) {
		this.vendorId = vendorId;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public LocalDateTime getIssuedAt() {
		return issuedAt;
	}
	public void setIssuedAt(LocalDateTime issuedAt) {
		this.issuedAt = issuedAt;
	}
	public LocalDateTime getExpiresAt() {
		return expiresAt;
	}
	public void setExpiresAt(LocalDateTime expiresAt) {
		this.expiresAt = expiresAt;
	}
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	public String getDeviceName() {
		return deviceName;
	}
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getCorrelationId() {
		return correlationId;
	}
	public void setCorrelationId(String correlationId) {
		this.correlationId = correlationId;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "VendorTranLogRequest [tokenUuid=" + tokenUuid + ", vendorId=" + vendorId + ", mobile=" + mobile
				+ ", accessToken=" + accessToken + ", issuedAt=" + issuedAt + ", expiresAt=" + expiresAt + ", isActive="
				+ isActive + ", deviceId=" + deviceId + ", deviceName=" + deviceName + ", appId=" + appId
				+ ", correlationId=" + correlationId + ", errorMessage=" + errorMessage + ", action=" + action
				+ ", status=" + status + "]";
	}
    
}
