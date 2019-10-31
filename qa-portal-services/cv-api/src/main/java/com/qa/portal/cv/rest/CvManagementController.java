package com.qa.portal.cv.rest;

import java.io.IOException;
import java.util.List;

import com.qa.portal.cv.domain.CvSearchCriteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.qa.portal.common.security.QaSecurityContext;
import com.qa.portal.cv.domain.CvVersion;
import com.qa.portal.cv.services.CvManagementService;

@RestController
public class CvManagementController {

    private CvManagementService service;

    private QaSecurityContext qaSecurityContext;

    private static final Logger LOGGER = LoggerFactory.getLogger(CvManagementController.class);

    public CvManagementController(CvManagementService service, QaSecurityContext qaSecurityContext) {
        super();
        this.service = service;
        this.qaSecurityContext = qaSecurityContext;
    }

    //	Create
    @PostMapping("/cv")
    public ResponseEntity<CvVersion> createCv(@RequestBody CvVersion newCv) {
        return ResponseEntity.ok(this.service.createCv(newCv, qaSecurityContext));
    }

    //  Update
    @PutMapping("/cv")
    public ResponseEntity<CvVersion> updateCv(@RequestBody CvVersion updatedCv) {
        return ResponseEntity.ok(this.service.updateCv(updatedCv));
    }


    //	Get
    @GetMapping("cv/{id}")
    public ResponseEntity<CvVersion> getCvById(@PathVariable("id") String id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping("/cvs")
    public ResponseEntity<List<CvVersion>> getAll() {
        return ResponseEntity.ok(this.service.getAll());
    }

    @GetMapping("/cv/trainee/search/{fullName}")
    public ResponseEntity<List<CvVersion>> findByFullNameIgnoreCase(@PathVariable("fullName") String fullName) {
        return ResponseEntity.ok(this.service.findByFullNameIgnoreCase(fullName));
    }

    @GetMapping("/cv/trainee")
    public ResponseEntity<List<CvVersion>> findCvVersionsForTrainee() {
        return ResponseEntity.ok(this.service.findByUserNameIgnoreCase(qaSecurityContext.getUserName()));
    }

    @GetMapping("/cv/trainee/current")
    public ResponseEntity<CvVersion> findCurrentCvVersionForTrainee() {
        return ResponseEntity.ok(this.service.getCurrentCvVersionForUser(qaSecurityContext.getUserName()));
    }

    //Admin search by criteria endpoint
    @GetMapping("/cv/search")
    public ResponseEntity<List<CvVersion>> cvSearch(@RequestParam(required = false) String cohort,
                                                    @RequestParam(required = false) String tech,
                                                    @RequestParam(required = false) String status,
                                                    @RequestParam(required = false) String name) {
    CvSearchCriteria c = new CvSearchCriteria("","","","");
        // decode the query string
        if (cohort != null && !cohort.isEmpty()) {
            c.setCohort(cohort);
        }
        if (status != null && !status.isEmpty()) {
            c.setCvStatus(status);
        }
        if (tech != null && !tech.isEmpty()) {
            c.setTechnology(tech);
        }
        if (name != null && !name.isEmpty()) {
            c.setFullName(name);
        }
        return ResponseEntity.ok(this.service.cvSearch(c));
    }


    //	PDF
    @PostMapping("cv/file")
    public void saveGeneratedCV(@RequestBody CvVersion cvVersion) throws IOException {
        service.saveGeneratedCv(cvVersion);
    }

    @PostMapping(value = "cv/generated", produces = {MediaType.APPLICATION_PDF_VALUE})
    public ResponseEntity<byte[]> getCvAsPdf(@RequestBody CvVersion cvVersion) throws IOException {
        return ResponseEntity.ok(service.getGeneratedCv(cvVersion));
    }
}
