package com.fdmgroup.spgroupcasestudy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.fdmgroup.spgroupcasestudy.service.ApplianceService;

@RestController
public class ApplianceController {

	@Autowired
	ApplianceService applianceService;
	
	
}
