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
public interface CustomerBookingsRepository extends JpaRepository<MyBookings, Long>{
	List<MyBookings> findByCustomerDetails_CustId(Long custId);
	
	@Query("SELECT m FROM MyBookings m WHERE m.bookingId = :bookingId AND m.customerDetails.custId = :custId")
	MyBookings findByBookingAndCustomer(@Param("custId") Long custId, @Param("bookingId") Long bookingId);
	
	
    @Modifying
    @Transactional
    @Query("UPDATE MyBookings m SET m.status = :status WHERE m.bookingId = :bookingId AND m.customerDetails.custId = :custId")
    int updateBookingStatus(
                            @Param("custId") Long custId,
                            @Param("bookingId") Long bookingId,
                            @Param("status") String status);

    @Query("SELECT m FROM MyBookings m WHERE  m.customerDetails.custId = :custId AND LOWER(m.status) = LOWER(:status)")
	List<MyBookings> findByBookingAndCustomerAndStatus(@Param("custId") Long custId, @Param("status") String status);
}
