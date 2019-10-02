package com.qa.portal.cohort.rest;

import com.qa.portal.cohort.services.technology.TechnologyService;
import com.qa.portal.common.dto.TechnologyCategoryDto;
import com.qa.portal.common.persistence.entity.TechnologyCategoryEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TechnologyController {

    private TechnologyService technologyService;

    public TechnologyController(TechnologyService technologyService) {
        this.technologyService = technologyService;
    }

    @GetMapping("technology/categories")
    public ResponseEntity<List<TechnologyCategoryDto>> getTechnologyCategories() {
        return ResponseEntity.ok(technologyService.getTechnologyCategories());
    }

    @GetMapping("technology/category/{id}")
    public ResponseEntity<TechnologyCategoryDto> getTechnologyCategory(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(technologyService.getTechnologyCategory(id));
    }
}
