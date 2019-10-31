package com.qa.portal.cohort.rest;

import com.qa.portal.cohort.services.CohortManagementService;
import com.qa.portal.common.dto.QaCohortDto;
import com.qa.portal.common.dto.QaUserDetailsDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/manage")
public class CohortManagementController {

    private CohortManagementService cohortManagementService;

    public CohortManagementController(CohortManagementService cohortManagementService) {
        this.cohortManagementService = cohortManagementService;
    }

    @PostMapping("cohort")
    public ResponseEntity<QaCohortDto> createCohort(@RequestBody QaCohortDto cohortDto) {
        return ResponseEntity.ok(this.cohortManagementService.createCohort(cohortDto));
    }

    @PutMapping("cohort")
    public ResponseEntity<QaCohortDto> updateCohort(@RequestBody QaCohortDto cohortDto) {
        return ResponseEntity.ok(this.cohortManagementService.updateCohort(cohortDto));
    }
}
