package com.qa.portal.cv.rest;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.portal.cv.domain.CvVersion;
import com.qa.portal.cv.services.CvManagementService;

@RestController
@RequestMapping("/cv")
public class CvManagementController {
	
	private CvManagementService service;
	
	public CvManagementController(CvManagementService service) {
		super();
		this.service = service;
	}
	
	@PostMapping("/create")
	public ResponseEntity<String> createCv(@RequestBody CvVersion newCv) {
		
		return ResponseEntity.ok(service.createCv(newCv));
	}

	@GetMapping("/abc")
	public ResponseEntity<String> Boom() {
		return ResponseEntity.ok("hello");
	}
}
