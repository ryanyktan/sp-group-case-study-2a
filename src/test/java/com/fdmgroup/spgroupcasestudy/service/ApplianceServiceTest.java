package com.fdmgroup.spgroupcasestudy.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fdmgroup.spgroupcasestudy.repository.ApplianceRepository;
import com.fdmgroup.spgroupcasestudy.model.Appliance;

@ExtendWith(MockitoExtension.class)
class ApplianceServiceTest {

	@Mock
	ApplianceRepository mockAppRepo;
	
	@Test
	void getAllAppliances_methodCallsFindAllMethodInApplianceRepository() {
		ApplianceService appService = new ApplianceService();
		appService.setApplianceRepository(mockAppRepo);
		appService.getAllAppliances();
		verify(mockAppRepo, times(1)).findAll();
	}

	@Test
	void removeAppliance_methodReturnsFalseWhenApplianceDoesNotExist() {
		Optional<Appliance> optional = Optional.empty();
		when(mockAppRepo.findById((long) 1)).thenReturn(optional);
		
		ApplianceService appService = new ApplianceService();
		appService.setApplianceRepository(mockAppRepo);
		assertEquals(false, appService.removeAppliance(1));
	}
	
	@Test
	void removeAppliance_methodReturnsTrueWhenApplianceExists() {
		Appliance app = new Appliance();
		Optional<Appliance> optional = Optional.of(app);
		when(mockAppRepo.findById((long) 1)).thenReturn(optional);
		
		ApplianceService appService = new ApplianceService();
		appService.setApplianceRepository(mockAppRepo);
		assertEquals(true, appService.removeAppliance(1));
	}
}
