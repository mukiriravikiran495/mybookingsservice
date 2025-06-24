package com.mybookingsservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mybookingsservice.entity.CustomerDetails;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerDetails, Long>{

	CustomerDetails findByCustId(Long custId);

}
