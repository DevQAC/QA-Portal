package com.qa.portal.cohort.rest;

import com.qa.portal.cohort.services.CohortService;
import com.qa.portal.common.dto.QaCohortDto;
import com.qa.portal.common.dto.QaUserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.Response;
import java.util.List;

@RestController
public class CohortController {

    private CohortService cohortService;

    public CohortController(CohortService cohortService) {
        this.cohortService = cohortService;
    }

    @GetMapping("cohort/{cohortId}")
    public ResponseEntity<QaCohortDto> getCohortForId(@PathVariable("cohortId") Integer cohortId) {
        return ResponseEntity.ok(cohortService.getCohortForId(cohortId));
    }

    @GetMapping("cohorts")
    public ResponseEntity<List<QaCohortDto>> getAllCohorts() {
        return ResponseEntity.ok(cohortService.getAllCohorts());
    }

    @GetMapping("trainees/{id}")
    public ResponseEntity<List<QaUserDto>> getTraineesForCohort(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(cohortService.getTraineesForCohort(id));
    }
}
