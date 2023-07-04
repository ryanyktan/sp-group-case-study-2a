package com.fdmgroup.spgroupcasestudy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fdmgroup.spgroupcasestudy.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

}
