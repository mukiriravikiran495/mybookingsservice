package com.mybookingsservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mybookingsservice.entity.MyBookings;

@Repository
public interface MyBookingsRepository extends JpaRepository<MyBookings, Long>{

	@Query("SELECT b FROM MyBookings b " +
		       "LEFT JOIN FETCH b.customerDetails cd " +
		       "LEFT JOIN FETCH cd.custAddress ca " +
		       "LEFT JOIN FETCH b.vendorDetails vd " +
		       "LEFT JOIN FETCH vd.vendorAddress va")
		List<MyBookings> findAllWithCustomerAndVendor();
}
