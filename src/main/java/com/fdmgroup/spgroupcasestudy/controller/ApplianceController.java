package com.fdmgroup.spgroupcasestudy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fdmgroup.spgroupcasestudy.model.Appliance;
import com.fdmgroup.spgroupcasestudy.service.ApplianceService;

@RestController
public class ApplianceController {

	@Autowired
	ApplianceService applianceService;
	
	@GetMapping("/appliances")
	public List<Appliance> getAllAppliances() {
		return applianceService.getAllAppliances();
	}
	
	@GetMapping("/appliances/create")
	public String createAppliance() {
		return "";
	}
	
}
