package com.qa.portal.cv.rest;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	
	@PutMapping("/cv")
	public ResponseEntity<String> updateCv(@RequestBody CvVersion updatedCv) {
		return ResponseEntity.ok(this.service.updateCv(updatedCv));
	}
	
	@GetMapping("/cvs")
	public List<CvVersion> getAll() {
		return service.getAll();
	}
	
	@GetMapping("/cv/version")
	public CvVersion getCurrent(Integer versionNumber) {
		return service.getCurrent(versionNumber);
	}
	
	@PostMapping("cv/file")
	public void saveGeneratedCV(@RequestBody CvVersion cvVersion) {
		service.saveGeneratedCv(cvVersion);
	}

	@PostMapping(value="cv/generated", produces={MediaType.APPLICATION_PDF_VALUE})
	public ResponseEntity<byte[]> getCvAsPdf() {
		return ResponseEntity.ok(service.getGeneratedCv(new CvVersion()));
	}
}
