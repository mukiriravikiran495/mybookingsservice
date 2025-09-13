package com.mybookingsservice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mybookingsservice.entity.Applications;
import com.mybookingsservice.entity.MyBookings;

import jakarta.transaction.Transactional;

@Repository
public interface MyBookingsRepository extends JpaRepository<MyBookings, Long>{

	Optional<MyBookings> findByBookingIdAndCustIdAndVendorId(Long bookingId, Long custId, Long vendorId);

	MyBookings findByBookingId(Long bookingId);

//	MyBookings findAllByBookingId(Long bookingId);
//
//	@Query("SELECT m FROM MyBookings m where m.bookingId = :bookingId AND m.custId = :custId AND m.vendorId = :vendorId ")
//	MyBookings findByBookingIdAndCustIdAndVendorId(Long bookingId, Long custId, Long vendorId);
//
//	@Query("SELECT m FROM MyBookings m LEFT JOIN FETCH m.selectedItems WHERE m.custId = :custId")
//	List<MyBookings> findByCustIdWithItems(Long custId);
//
//	@Query("SELECT m FROM MyBookings m where m.bookingId = :bookingId AND m.custId = :custId ")
//	MyBookings findByBookingIdAndCustId(Long custId, Long bookingId);
//
//	@Query("SELECT m FROM MyBookings m LEFT JOIN FETCH m.selectedItems WHERE m.custId = :custId AND LOWER(m.status) = LOWER(:status)")
//	List<MyBookings> findByCustIdAndStatusWithItems(Long custId, String status);
//
//	@Query("SELECT m FROM MyBookings m LEFT JOIN FETCH m.selectedItems WHERE m.vendorId = :vendorId")
//	List<MyBookings> findByVendorIdWithItems(Long vendorId);
//
//	@Query("SELECT m FROM MyBookings m where m.bookingId = :bookingId AND m.vendorId = :vendorId ")
//	MyBookings findByBookingIdAndVendorId(Long vendorId, Long bookingId);
//	
//	@Query("SELECT m FROM MyBookings m LEFT JOIN FETCH m.selectedItems WHERE m.vendorId = :vendorId AND LOWER(m.status) = LOWER(:status)")
//	List<MyBookings> findByVendorIdAndStatusWithItems(Long vendorId, String status);


}




