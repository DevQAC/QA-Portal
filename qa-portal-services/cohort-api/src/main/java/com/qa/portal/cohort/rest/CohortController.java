package com.qa.portal.cohort.rest;

import com.qa.portal.cohort.services.CohortService;
import com.qa.portal.common.dto.QaCohortDto;
import org.springframework.boot.actuate.endpoint.annotation.DeleteOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CohortController {

    private CohortService cohortService;

    public CohortController(CohortService cohortService) {
        this.cohortService = cohortService;
    }

    @GetMapping("cohorts")
    public ResponseEntity<List<QaCohortDto>> getAllCohorts() {
        return ResponseEntity.ok(cohortService.getAllCohorts());
    }

    @DeleteMapping("cohorts")
    private ResponseEntity<List<QaCohortDto>> deleteCohorts(@RequestBody List<QaCohortDto> cohortDtos) {
        cohortService.deleteCohorts(cohortDtos);
        return ResponseEntity.ok(cohortDtos);
    }
}
