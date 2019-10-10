package com.qa.portal.application.rest;

import com.qa.portal.common.security.QaSecurityContext;
import com.qa.portal.application.dto.ApplicationProjectsDto;
import com.qa.portal.application.service.PortalApplicationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.qa.portal.application.PortalApplicationConstants.APPLICATIONS_API_URL;

@RestController
@RequestMapping(value = APPLICATIONS_API_URL)
public class PortalApplicationController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PortalApplicationController.class);

    private PortalApplicationService portalApplicationService;

    private QaSecurityContext securityContext;

    @Autowired
    public PortalApplicationController(PortalApplicationService portalApplicationService, QaSecurityContext securityContext) {
        this.portalApplicationService = portalApplicationService;
        this.securityContext = securityContext;
    }

    @GetMapping()
    public ResponseEntity<List<ApplicationProjectsDto>> getPortalApplications() {
        return ResponseEntity.ok(portalApplicationService.getPortalApplications(securityContext.getRoles()));
    }
}
