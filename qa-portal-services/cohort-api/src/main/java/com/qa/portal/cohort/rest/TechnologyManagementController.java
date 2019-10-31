package com.qa.portal.cohort.rest;

import com.qa.portal.cohort.services.technology.TechnologyCategoryManagementService;
import com.qa.portal.common.dto.TechnologyCategoryDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/manage")
public class TechnologyManagementController {

    private TechnologyCategoryManagementService technologyCategoryManagementService;

    public TechnologyManagementController(TechnologyCategoryManagementService technologyCategoryManagementService) {
        this.technologyCategoryManagementService = technologyCategoryManagementService;
    }

    @PostMapping("technology/category")
    public ResponseEntity<TechnologyCategoryDto> createTechnologyCategory(@RequestBody TechnologyCategoryDto technologyCategoryDto) {
        return ResponseEntity.ok(technologyCategoryManagementService.createTechnologyCategory(technologyCategoryDto));
    }

    @PutMapping("technology/category")
    public ResponseEntity<TechnologyCategoryDto> updateTechnologyCategory(@RequestBody TechnologyCategoryDto technologyCategoryDto) {
        return ResponseEntity.ok(technologyCategoryManagementService.updateTechnologyCategory(technologyCategoryDto));
    }
}
