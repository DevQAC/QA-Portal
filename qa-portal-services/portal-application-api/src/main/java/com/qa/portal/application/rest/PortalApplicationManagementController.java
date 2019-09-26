package com.qa.portal.application.rest;

import com.qa.portal.application.dto.PortalApplicationDto;
import com.qa.portal.application.service.PortalApplicationManagementService;
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
