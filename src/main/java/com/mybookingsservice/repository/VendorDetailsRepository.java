package com.mybookingsservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mybookingsservice.entity.VendorDetails;

@Repository
public interface VendorDetailsRepository extends JpaRepository<VendorDetails, Long> {

}
