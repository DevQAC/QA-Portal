package com.qa.portal.core.rest;

import com.qa.portal.core.dto.PortalApplicationDto;
import com.qa.portal.core.service.application.PortalApplicationManagementService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/manage")
public class PortalApplicationManagementController {

    private PortalApplicationManagementService portalApplicationManagementService;

    public PortalApplicationManagementController(PortalApplicationManagementService portalApplicationManagementService) {
        this.portalApplicationManagementService = portalApplicationManagementService;
    }

    @PostMapping("portal/application")
    public ResponseEntity<PortalApplicationDto> createPortalApplication(@RequestBody PortalApplicationDto portalApplicationDto) {
        return ResponseEntity.ok(portalApplicationManagementService.createPortalApplication(portalApplicationDto));
    }

    @PutMapping("portal/application")
    public ResponseEntity<PortalApplicationDto> updatePortalApplication(@RequestBody PortalApplicationDto portalApplicationDto) {
        return ResponseEntity.ok(portalApplicationManagementService.updatePortalApplication(portalApplicationDto));
    }
}
