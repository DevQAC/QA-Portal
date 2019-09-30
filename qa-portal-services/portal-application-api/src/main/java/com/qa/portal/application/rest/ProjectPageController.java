package com.qa.portal.application.rest;

import com.qa.portal.application.dto.ProjectPageDto;
import com.qa.portal.application.service.page.ProjectPageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProjectPageController {

    private ProjectPageService projectPageService;

    public ProjectPageController(ProjectPageService projectPageService) {
        this.projectPageService = projectPageService;
    }

    @GetMapping("/project/pages")
    public ResponseEntity<List<ProjectPageDto>> getAllProjectPages() {
        return ResponseEntity.ok(projectPageService.getAllProjectPages());
    }

    @GetMapping("/project/page/{id}")
    public ResponseEntity<ProjectPageDto> getProjectPageForId(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(projectPageService.getProjectPageForId(id));
    }
}
