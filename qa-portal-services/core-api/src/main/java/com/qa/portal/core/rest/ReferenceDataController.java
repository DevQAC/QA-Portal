package com.qa.portal.core.rest;

import com.qa.portal.common.security.QaSecurityContext;
import com.qa.portal.core.dto.DepartmentApplicationsDto;
import com.qa.portal.core.service.ApplicationService;
import com.qa.portal.core.service.ReferenceDataService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = REFERENCEDATA_API)
public class ReferenceDataController {

	    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationsController.class);

	    private ReferenceDataService referenceDataService;

	    @GetMapping()
	    public ResponseEntity<List<Technology>> getReferenceDataForCategories() {
	        return ResponseEntity.ok(referenceDataService.getApplicationsByDepartment(securityContext.getRoles()));
    }
}

	
