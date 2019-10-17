package com.qa.portal.application.rest;

import com.qa.portal.application.dto.PortalProjectDto;
import com.qa.portal.application.service.project.PortalProjectManagementService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/manage")
public class PortalProjectManagementController {

    private PortalProjectManagementService portalProjectManagementService;

    public PortalProjectManagementController(PortalProjectManagementService portalProjectManagementService) {
        this.portalProjectManagementService = portalProjectManagementService;
    }

    @PostMapping("portal/project")
    public ResponseEntity<PortalProjectDto> createPortalProject(@RequestBody PortalProjectDto portalProjectDto) {
        return ResponseEntity.ok(portalProjectManagementService.createPortalProject(portalProjectDto));
    }

    @PutMapping("portal/project")
    public ResponseEntity<PortalProjectDto> updatePortalProject(@RequestBody PortalProjectDto portalProjectDto) {
        return ResponseEntity.ok(portalProjectManagementService.updatePortalProject(portalProjectDto));
    }
}
