package com.mybookingsservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mybookingsservice.entity.Vendor;
import com.mybookingsservice.service.VendorNativeResult;

@Repository
public interface VendorRepository extends JpaRepository<Vendor, Long>{

	Vendor findByVendorId(long vendorId);

	@Query(value = """
	        SELECT v.vendorId AS vendorId, v.v_firstname, v.v_lastname, v.v_mobile, v.v_email,
	               vs.v_service_id, vs.v_zipcode, vs.basepriceperkm AS basePricePerKm,
	               vs.priceperkg AS pricePerKg, vs.avgdeliverytimeindays AS avgDeliveryTimeInDays
	        FROM VENDOR.VENDOR v
	        JOIN VENDOR.vendor_service_area vs ON v.vendorId = vs.vendorId
	        WHERE vs.v_zipcode = :zipcode
	        """, nativeQuery = true)
	    List<VendorNativeResult> findVendorsByZipcodeNative(@Param("zipcode") String zipcode);
	
}
