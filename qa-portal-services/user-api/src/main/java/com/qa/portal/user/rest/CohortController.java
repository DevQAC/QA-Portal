package com.qa.portal.user.rest;

import java.util.List;

import com.qa.portal.user.services.CohortService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.qa.portal.common.dto.QaUserDto;

@RestController
public class CohortController {

    private CohortService service;

    public CohortController(CohortService service) {
        super();
        this.service = service;
    }

    @GetMapping("/cohort/getTrainees/{id}")
    public ResponseEntity<List<QaUserDto>> getTraineesForCohort(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(this.service.getTraineesForCohort(id));
    }

}
