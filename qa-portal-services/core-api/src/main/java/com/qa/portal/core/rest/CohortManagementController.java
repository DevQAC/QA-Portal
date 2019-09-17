package com.qa.portal.core.rest;

import com.qa.portal.core.service.CohortManagementService;
import com.qa.portal.common.dto.QaCohortDto;
import com.qa.portal.common.security.QaSecurityContext;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cohort")
public class CohortManagementController {

    private CohortManagementService cohortManagementService;

    private QaSecurityContext qaSecurityContext;

    public CohortManagementController(CohortManagementService cohortManagementService,
                                      QaSecurityContext qaSecurityContext) {
        this.cohortManagementService = cohortManagementService;
        this.qaSecurityContext = qaSecurityContext;
    }

    @PostMapping
    public ResponseEntity<QaCohortDto> createCohort(@RequestBody QaCohortDto cohortDetails) {
        return ResponseEntity.ok(cohortManagementService.createCohort(cohortDetails));
    }

    @PutMapping
    public ResponseEntity<QaCohortDto> updateCohort(@RequestBody QaCohortDto cohortDetails) {
        return ResponseEntity.ok(cohortManagementService.updateCohort(cohortDetails));
    }

    @DeleteMapping("{cohortName}")
    public ResponseEntity<?> deleteCohort(@PathVariable("cohortName") String cohortName) {
        cohortManagementService.deleteCohort(cohortName);
        return ResponseEntity.ok().build();
    }
}
