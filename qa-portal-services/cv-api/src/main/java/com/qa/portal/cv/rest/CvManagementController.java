package com.qa.portal.cv.rest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.qa.portal.cv.domain.CvVersion;
import com.qa.portal.cv.services.CvManagementService;

@RestController
public class CvManagementController {

	private CvManagementService service;
	
	public CvManagementController(CvManagementService service) {
		this.service = service;
	}
	
	@PostMapping("cv")
	public void saveGeneratedCV(@RequestBody CvVersion cvVersion) {
		service.saveGeneratedCv(cvVersion);
	}
}
