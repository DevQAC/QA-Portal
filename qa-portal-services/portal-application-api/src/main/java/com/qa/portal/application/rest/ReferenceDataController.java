package com.qa.portal.application.rest;

import com.qa.portal.application.service.refdata.ReferenceDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;


@RestController
public class ReferenceDataController {

    private static final Logger LOGGER = LoggerFactory.getLogger(com.qa.portal.application.rest.ReferenceDataController.class);

    private ReferenceDataService service;

    @Autowired
    public ReferenceDataController(ReferenceDataService service) {
        this.service = service;
    }

    @GetMapping("/referencedata")
    public ResponseEntity<Map<String, List<String>>> getReferenceDataForCategories() {
        return ResponseEntity.ok(this.service.getReferenceDataForCategories());
    }
}





	
