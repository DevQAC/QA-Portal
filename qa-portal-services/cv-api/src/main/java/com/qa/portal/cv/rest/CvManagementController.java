package com.qa.portal.cv.rest;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.qa.portal.cv.domain.CvVersion;
import com.qa.portal.cv.services.CvManagementService;

@RestController
public class CvManagementController {
	
	private CvManagementService service;
	
	public CvManagementController(CvManagementService service) {
		super();
		this.service = service;
	}
	
	@PostMapping("/cv")
	public ResponseEntity<String> createCv(@RequestBody CvVersion newCv) {
		return ResponseEntity.ok(this.service.createCv(newCv));
	}
	
	@GetMapping("/getAll")
	public List<CvVersion> getAll() {
		return service.getAll();
	}

}
