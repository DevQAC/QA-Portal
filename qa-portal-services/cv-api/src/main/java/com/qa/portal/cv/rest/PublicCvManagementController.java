package com.qa.portal.cv.rest;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.qa.portal.cv.domain.CvVersion;
import com.qa.portal.cv.services.CvManagementService;

@RestController
@ConditionalOnProperty("cv.enable_public_endpoints")
public class PublicCvManagementController {
	
    private CvManagementService service;

    private static final Logger LOGGER = LoggerFactory.getLogger(PublicCvManagementController.class);

    public PublicCvManagementController(CvManagementService service) {
        super();
        this.service = service;
    }
   
    @PostMapping(value = "public/cv/generated", produces = {MediaType.APPLICATION_PDF_VALUE})
    public ResponseEntity<byte[]> getCvAsPdfPublic(@RequestBody CvVersion cvVersion) throws IOException {
    		return ResponseEntity.ok(service.getGeneratedCv(cvVersion));    		
    }
}
