package com.mybookingsservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mybookingsservice.entity.Applications;

@Repository
public interface ApplicationsRepository extends JpaRepository<Applications, String> {
    Optional<Applications> findByAppIdAndIsActive(String appId, String isActive);
}