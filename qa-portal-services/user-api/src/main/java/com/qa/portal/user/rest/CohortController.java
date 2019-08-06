package com.qa.portal.user.rest;

import java.util.List;

import com.qa.portal.common.exception.QaResourceNotFoundException;
import com.qa.portal.common.persistence.entity.QaCohortEntity;
import com.qa.portal.common.security.QaSecurityContext;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.qa.portal.common.dto.QaUserDto;
import com.qa.portal.common.services.CohortService;

@RestController
public class CohortController {

    private CohortService service;

    private QaSecurityContext qaSecurityContext;

    public CohortController(CohortService service, QaSecurityContext qaSecurityContext) {
        this.service = service;
    }

    @GetMapping("/cohort/trainees/{id}")
    public ResponseEntity<List<QaUserDto>> getTraineesForCohort(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(this.service.getTraineesForCohort(id));
    }

}
