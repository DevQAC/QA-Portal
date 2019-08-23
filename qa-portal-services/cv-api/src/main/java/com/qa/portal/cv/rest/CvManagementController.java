package com.qa.portal.cv.rest;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.qa.portal.cv.domain.CvVersion;
import com.qa.portal.cv.services.CvManagementService;
import com.sun.mail.iap.ByteArray;

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

	@PostMapping(value="cv/pdf", consumes=MediaType.APPLICATION_JSON_VALUE, produces={MediaType.APPLICATION_PDF_VALUE})
	public ResponseEntity<byte[]> getCvAsPdf(@RequestBody CvVersion cvVersion) {
		return ResponseEntity.ok(service.getGeneratedCv(cvVersion));
	}
}
