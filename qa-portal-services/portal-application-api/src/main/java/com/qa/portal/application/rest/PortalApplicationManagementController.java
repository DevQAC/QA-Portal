package com.qa.portal.application.rest;

import com.qa.portal.application.dto.ApplicationProjectsDto;
import com.qa.portal.application.dto.PortalApplicationDto;
import com.qa.portal.application.service.PortalApplicationManagementService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/manage")
public class PortalApplicationManagementController {

    private PortalApplicationManagementService portalApplicationManagementService;

    public PortalApplicationManagementController(PortalApplicationManagementService portalApplicationManagementService) {
        this.portalApplicationManagementService = portalApplicationManagementService;
    }

    @GetMapping("portal/applications")
    public ResponseEntity<List<PortalApplicationDto>> getAllPortalApplications() {
        return ResponseEntity.ok(portalApplicationManagementService.getAllPortalApplications());
    }

    @GetMapping("portal/application/{id}")
    public ResponseEntity<ApplicationProjectsDto> getPortalApplicationById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(portalApplicationManagementService.getPortalApplicationById(id));
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
