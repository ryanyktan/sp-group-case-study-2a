package com.fdmgroup.spgroupcasestudy.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fdmgroup.spgroupcasestudy.model.Appliance;
import com.fdmgroup.spgroupcasestudy.service.ApplianceService;

@RestController
@RequestMapping("/api/appliances")
@CrossOrigin(origins = "http://localhost:3000")
public class ApplianceController {

	@Autowired
	ApplianceService applianceService;
	
	// obsolete
	@GetMapping
	public List<Appliance> getAllAppliances() {
		return applianceService.getAllAppliances();
	}
	
	@GetMapping("/get/{appId}")
	public ResponseEntity<Object> getThisAppliance(@PathVariable long appId) {
		Optional<Appliance> optionalAppliance = applianceService.findApplianceById(appId);
		if (optionalAppliance.isEmpty()) {
			
			// This should really never happen if the application is used correctly
			// 400 Bad Request
			return ResponseEntity.badRequest().body("No such appliance with this id exists.");
		} else {
			return ResponseEntity.ok(optionalAppliance.get());
		}
	}
	
	@GetMapping("/{userId}")
	public List<Appliance> getAllAppliancesForThisUser(@PathVariable long userId) {
		return applianceService.getAllAppliancesForThisUser(userId);
	}
	
	@PostMapping("/{userId}")
	public ResponseEntity<Object> addEditAppliance(@RequestBody Appliance appliance, @PathVariable long userId) {
		
		// This method maps the logged in user to the appliance to be added/edited.
		Appliance preparedAppliance = applianceService.prepareApplianceForProcessing(appliance, userId);
		
		// If the id field in the form is blank, we treat the appliance as a new object
		if (appliance.getId() == 0) {
			
			// If the appliance already exists, display an error message.
			if (applianceService.checkIfApplianceExists(preparedAppliance)) {
				return ResponseEntity.badRequest().body("The appliance you are trying to add already exists.");
			}
			
			Appliance createdAppliance = applianceService.createAppliance(preparedAppliance);
			
			URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/{id}")
					.build(createdAppliance.getId());
			
			// 201 Created
			return ResponseEntity.created(location).body(applianceService.createAppliance(preparedAppliance));
			
		//If the id field is filled (automatically) then this is an update request.
		} else {
			if (applianceService.updateAppliance(preparedAppliance)) {

				URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/{id}")
						.build(preparedAppliance.getId());
				
				// 201 Created
				return ResponseEntity.created(location).body(preparedAppliance);
			} else {
				
				// 404 Not Found when Appliance has ID but not in database
				return ResponseEntity.notFound().build();
			}
		}
		
	}
	
	@DeleteMapping("/{appId}")
	public ResponseEntity<Object> deleteAppliance(@PathVariable long appId) {
		
		if (applianceService.deleteAppliance(appId)) {
			
			// 204 No Content
			return ResponseEntity.status(204).build();
		} else {
			
			// Fails when appliance is not found in database, so 400 Bad Request
			return ResponseEntity.badRequest().body("Appliance either does not exist or is still active.");
		}
	}
}
