package com.mybookingsservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mybookingsservice.entity.MyBookings;

import jakarta.transaction.Transactional;

@Repository
public interface MyBookingsRepository extends JpaRepository<MyBookings, Long>{

//	@Query("SELECT b FROM MyBookings b " +
//	           "JOIN FETCH b.customerDetails " +
//	           "JOIN FETCH b.vendorDetails")
//		List<MyBookings> findAllWithCustomerAndVendor();
//	
//	List<MyBookings> findByCustId(Long custId);
//	
//	@Query("SELECT m FROM MyBookings m WHERE m.bookingId = :bookingId AND m.custId = :custId")
//	MyBookings findByBookingIdAndCustId(@Param("custId") Long custId, @Param("bookingId") Long bookingId);
//	
//	
//    @Modifying
//    @Transactional
//    @Query("UPDATE MyBookings m SET m.status = :status WHERE m.bookingId = :bookingId AND m.customerDetails.custId = :custId")
//    int updateBookingStatus(
//                            @Param("custId") Long custId,
//                            @Param("bookingId") Long bookingId,
//                            @Param("status") String status);
//
//    @Query("SELECT m FROM MyBookings m WHERE  m.customerDetails.custId = :custId AND LOWER(m.status) = LOWER(:status)")
//	List<MyBookings> findByCustIdAndStatus(@Param("custId") Long custId, @Param("status") String status);
//    
//    List<MyBookings> findByVendorId(Long vendorId);
//
//    @Query("SELECT m FROM MyBookings m WHERE m.bookingId = :bookingId AND m.vendorDetails.vendorId = :vendorId")
//    MyBookings findByBookingIdAndVendorId(Long vendorId, Long bookingId);
//
//    @Modifying
//    @Transactional
//    @Query("UPDATE MyBookings m SET m.status = :status WHERE m.bookingId = :bookingId AND m.vendorDetails.vendorId = :vendorId")
//	int updateCancelStatus(Long vendorId, Long bookingId, String status);
//
//    @Query("SELECT m FROM MyBookings m WHERE m.vendorDetails.vendorId = :vendorId AND LOWER(m.status) = LOWER(:status)")
//	List<MyBookings> findByVendorIdAndStatus(Long vendorId, String status);
//    
//    @Modifying
//    @Transactional
//    @Query("UPDATE MyBookings m SET m.status = :status WHERE m.bookingId = :bookingId AND m.vendorDetails.vendorId = :vendorId")
//	int updateAcceptStatus(Long vendorId, Long bookingId, String status);
    
    
}




