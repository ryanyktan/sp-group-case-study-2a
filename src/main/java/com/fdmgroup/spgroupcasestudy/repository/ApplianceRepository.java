package com.fdmgroup.spgroupcasestudy.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fdmgroup.spgroupcasestudy.model.Appliance;

@Repository
public interface ApplianceRepository extends JpaRepository<Appliance, Long> {

	@Query(value = "SELECT * FROM appliance a WHERE a.serial_number = :serialNumber "
			+ "AND a.brand = :brand AND a.model = :model", nativeQuery = true)
	public List<Appliance> findBySerialNumber_Brand_andModel(@Param(value = "serialNumber") String serialNumber, 
			@Param(value = "brand") String brand, @Param(value = "model") String model);
	
}
