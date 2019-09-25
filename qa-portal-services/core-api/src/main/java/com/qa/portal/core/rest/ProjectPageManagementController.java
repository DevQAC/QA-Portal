package com.qa.portal.core.rest;

import com.qa.portal.core.dto.ProjectPageDto;
import com.qa.portal.core.service.page.ProjectPageManagementService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/manage")
public class ProjectPageManagementController {

    private ProjectPageManagementService projectPageManagementService;

    public ProjectPageManagementController(ProjectPageManagementService projectPageManagementService) {
        this.projectPageManagementService = projectPageManagementService;
    }

    @PostMapping("project/page")
    public ResponseEntity<ProjectPageDto> createProjectPage(@RequestBody ProjectPageDto projectPageDto) {
        return ResponseEntity.ok(projectPageManagementService.createProjectPage(projectPageDto));
    }

    @PutMapping("project/page")
    public ResponseEntity<ProjectPageDto> updateProjectPage(@RequestBody ProjectPageDto projectPageDto) {
        return ResponseEntity.ok(projectPageManagementService.updateProjectPage(projectPageDto));
    }
}
