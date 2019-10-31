package com.qa.portal.cohort.rest;

import com.qa.portal.cohort.services.technology.TechnologyCategoryService;
import com.qa.portal.common.dto.TechnologyCategoryDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TechnologyController {

    private TechnologyCategoryService technologyCategoryService;

    public TechnologyController(TechnologyCategoryService technologyCategoryService) {
        this.technologyCategoryService = technologyCategoryService;
    }

    @GetMapping("technology/categories")
    public ResponseEntity<List<TechnologyCategoryDto>> getTechnologyCategories() {
        return ResponseEntity.ok(technologyCategoryService.getTechnologyCategories());
    }

    @GetMapping("technology/category/{id}")
    public ResponseEntity<TechnologyCategoryDto> getTechnologyCategory(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(technologyCategoryService.getTechnologyCategory(id));
    }
}
