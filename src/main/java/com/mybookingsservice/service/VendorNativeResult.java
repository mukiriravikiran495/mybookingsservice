package com.mybookingsservice.service;

public interface VendorNativeResult {
    Long getVendorId();
    String getVfirstname();
    String getVlastname();
    String getVmobile();
    String getVemail();

    Long getVserviceId();
    String getVzipcode();
    int getBasePricePerKm();
    int getPricePerKg();
    int getAvgDeliveryTimeInDays();
}