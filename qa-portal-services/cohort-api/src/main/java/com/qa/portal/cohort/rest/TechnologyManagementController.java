package com.qa.portal.cohort.rest;

import com.qa.portal.cohort.services.technology.TechnologyManagementService;
import com.qa.portal.common.dto.TechnologyCategoryDto;
import com.qa.portal.common.dto.TechnologyDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/manage")
public class TechnologyManagementController {

    private TechnologyManagementService technologyManagementService;

    public TechnologyManagementController(TechnologyManagementService technologyManagementService) {
        this.technologyManagementService = technologyManagementService;
    }

    @PostMapping("technology/category")
    public ResponseEntity<TechnologyCategoryDto> createTechnologyCategory(@RequestBody TechnologyCategoryDto technologyCategoryDto) {
        return ResponseEntity.ok(technologyManagementService.createTechnologyCategory(technologyCategoryDto));
    }

    @PostMapping("technology")
    public ResponseEntity<TechnologyDto> createTechnology(@RequestBody TechnologyDto technologyDto) {
        return ResponseEntity.ok(technologyManagementService.createTechnology(technologyDto));
    }

    @PutMapping("technology")
    public ResponseEntity<TechnologyDto> updateTechnology(@RequestBody TechnologyDto technologyDto) {
        return ResponseEntity.ok(technologyManagementService.updateTechnology(technologyDto));
    }
}
