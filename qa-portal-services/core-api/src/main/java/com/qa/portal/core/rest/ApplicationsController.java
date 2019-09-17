package com.qa.portal.core.rest;

import com.qa.portal.common.security.QaSecurityContext;
import com.qa.portal.core.dto.ApplicationProjectsDto;
import com.qa.portal.core.service.ApplicationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.qa.portal.core.CoreConstants.APPLICATIONS_API_URL;

@RestController
@RequestMapping(value = APPLICATIONS_API_URL)
public class ApplicationsController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationsController.class);

    private ApplicationService applicationService;

    private QaSecurityContext securityContext;

    @Autowired
    public ApplicationsController(ApplicationService applicationService, QaSecurityContext securityContext) {
        this.applicationService = applicationService;
        this.securityContext = securityContext;
    }

    @GetMapping()
    public ResponseEntity<List<ApplicationProjectsDto>> getPortalApplications() {
        return ResponseEntity.ok(applicationService.getPortalApplications(securityContext.getRoles()));
    }
}
