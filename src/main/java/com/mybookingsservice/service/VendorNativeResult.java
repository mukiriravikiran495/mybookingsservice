package com.mybookingsservice.service;

public interface VendorNativeResult {
    long getVendorId();
    String getV_firstname();
    String getV_lastname();
    String getV_mobile();
    String getV_email();

    long getV_service_id();
    String getV_zipcode();
    long getBasePricePerKm();
    long getPricePerKg();
    long getAvgDeliveryTimeInDays();
}