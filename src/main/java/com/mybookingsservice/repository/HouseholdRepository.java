package com.mybookingsservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.mybookingsservice.entity.HouseholdItems;

public interface HouseholdRepository extends JpaRepository<HouseholdItems, Long>{

	
//	@Query("SELECT h FROM HouseholdItems h WHERE h.estCategory IN :categories")
//	public List<HouseholdItems> findByEstCategoryIn(@Param("categories") List<String> categories);

//	@Query(value = "SELECT * FROM householditems WHERE estCategory IN :categories", nativeQuery = true)
//    List<HouseholdItems> findByEstCategoryIn(@Param("categories") List<String> categories);
//	
}
