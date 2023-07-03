package com.fdmgroup.spgroupcasestudy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fdmgroup.spgroupcasestudy.model.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
