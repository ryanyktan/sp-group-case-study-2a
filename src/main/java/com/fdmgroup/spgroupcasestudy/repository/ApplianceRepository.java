package com.fdmgroup.spgroupcasestudy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fdmgroup.spgroupcasestudy.model.Appliance;

@Repository
public interface ApplianceRepository extends JpaRepository<Appliance, Long> {

}
