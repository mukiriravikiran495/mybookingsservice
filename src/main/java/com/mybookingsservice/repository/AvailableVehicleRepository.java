package com.mybookingsservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mybookingsservice.entity.AvailableVehicles;

@Repository
public interface AvailableVehicleRepository extends JpaRepository<AvailableVehicles, Long>{

}
