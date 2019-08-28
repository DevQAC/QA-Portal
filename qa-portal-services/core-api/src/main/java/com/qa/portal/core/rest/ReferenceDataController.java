package com.qa.portal.core.rest;

import com.qa.portal.core.service.ReferenceDataService;

import com.qa.portal.common.security.QaSecurityContext;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.logging.Level;


@RestController
public class ReferenceDataController {

    private static final Logger LOGGER = LoggerFactory.getLogger(com.qa.portal.core.rest.ReferenceDataController.class);

    private ReferenceDataService service;

    private QaSecurityContext context;

    @Autowired
    public ReferenceDataController(ReferenceDataService service, QaSecurityContext context) {
        this.service = service;
        this.context = context;
    }

    @GetMapping("/referencedata")
    public ResponseEntity<Map<String, List<String>>> getReferenceDataForCategories() {
        List<String> list = new ArrayList<>();
        list.add("cohort");
        list.add("cvStatus");
        list.add("technology");
        return ResponseEntity.ok(this.service.getReferenceDataForCategories(list));

    }
}





	
