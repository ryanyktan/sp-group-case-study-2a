package com.fdmgroup.spgroupcasestudy.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fdmgroup.spgroupcasestudy.model.Appliance;
import com.fdmgroup.spgroupcasestudy.service.ApplianceService;

@RestController
@RequestMapping("/api/appliances")
public class ApplianceController {

	@Autowired
	ApplianceService applianceService;
	
	@GetMapping
	public List<Appliance> getAllAppliances() {
		return applianceService.getAllAppliances();
	}
	
	@PostMapping("/add-edit")
	public ResponseEntity<Appliance> addEditAppliance(@RequestBody Appliance appliance) {
		
		
		// TODO: figure out what happens when id field is blank. For now assume id will be 0.
		if (appliance.getId() == 0) {
			Appliance createdAppliance = applianceService.createAppliance(appliance);
			
			URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/{id}")
					.build(createdAppliance.getId());
			
			// 201 Created
			return ResponseEntity.created(location).body(applianceService.createAppliance(appliance));
		} else {
			if (applianceService.updateAppliance(appliance)) {

				URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/{id}")
						.build(appliance.getId());
				
				// 201 Created
				return ResponseEntity.created(location).body(appliance);
			} else {
				
				// 404 Not Found when Appliance has ID but not in database
				return ResponseEntity.notFound().build();
			}
		}
		
	}
	
	@PostMapping("/delete")
	public ResponseEntity<Appliance> deleteAppliance(@RequestBody Appliance appliance) {
		
		
		if (applianceService.deleteAppliance(appliance.getId())) {
			
			// 200 OK
			return ResponseEntity.ok(appliance);
		} else {
			
			// Fails when appliance is not found in database, so 404 Not Found
			return ResponseEntity.notFound().build();
		}
	}
}
