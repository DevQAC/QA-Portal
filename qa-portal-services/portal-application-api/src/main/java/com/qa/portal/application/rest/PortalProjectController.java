package com.qa.portal.application.rest;

import com.qa.portal.application.dto.PortalProjectDto;
import com.qa.portal.application.service.project.PortalProjectService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PortalProjectController {

    private PortalProjectService portalProjectService;

    public PortalProjectController(PortalProjectService portalProjectService) {
        this.portalProjectService = portalProjectService;
    }

    @GetMapping("portal/projects")
    public ResponseEntity<List<PortalProjectDto>> getPortalProjects() {
        return ResponseEntity.ok(portalProjectService.getPortalProjects());
    }

    @GetMapping("portal/project/{id}")
    public ResponseEntity<PortalProjectDto> getPortalProject(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(portalProjectService.getPortalProject(id));
    }
}
