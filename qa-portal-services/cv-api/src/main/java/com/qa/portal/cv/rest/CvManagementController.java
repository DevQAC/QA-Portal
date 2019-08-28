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

import com.qa.portal.common.security.QaSecurityContext;
import com.qa.portal.cv.domain.CvVersion;
import com.qa.portal.cv.services.CvManagementService;

@RestController
public class CvManagementController {

	private CvManagementService service;
	private QaSecurityContext qaSecurityContext;
	
	public CvManagementController(CvManagementService service, QaSecurityContext qaSecurityContext) {
		super();
		this.service = service;
		this.qaSecurityContext = qaSecurityContext;
	}
	
	@PostMapping("/cv")
	public ResponseEntity<CvVersion> createCv(@RequestBody CvVersion newCv) {
		return ResponseEntity.ok(this.service.createCv(newCv));
	}
	
	@PutMapping("/cv")
	public ResponseEntity<CvVersion> updateCv(@RequestBody CvVersion updatedCv) {
		return ResponseEntity.ok(this.service.updateCv(updatedCv));
	}
	
	@GetMapping("/cvs")
	public ResponseEntity<List<CvVersion>> getAll() {
		return ResponseEntity.ok(this.service.getAll());
	}
	
	@GetMapping("/cv/version")
	public CvVersion findByVersionNumber(Integer versionNumber) {
		return service.findByVersionNumber(versionNumber);
	}
	
//	@GetMapping("/cv/trainee/current")
//	public ResponseEntity<List<CvVersion>> findByFullNameIgnoreCase(){
//		String fullName = qaSecurityContext.getFirstName() + " " + qaSecurityContext.getSurname();
//		return ResponseEntity.ok(this.service.findByFullNameIgnoreCase(fullName));
//	}
	
	@GetMapping("/cv/trainee/current/{userName}")
	public ResponseEntity<List<CvVersion>> findByuserNameIgnoreCase(@PathVariable("userName") String userName){
		return ResponseEntity.ok(this.service.findByUserNameIgnoreCase(userName));
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
