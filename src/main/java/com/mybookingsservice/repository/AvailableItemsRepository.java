package com.mybookingsservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.mybookingsservice.entity.AvailableItems;

@Repository
public interface AvailableItemsRepository extends JpaRepository<AvailableItems, Long>{

}
