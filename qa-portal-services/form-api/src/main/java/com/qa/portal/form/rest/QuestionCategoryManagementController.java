package com.qa.portal.form.rest;

import com.qa.portal.common.dto.QuestionCategoryDto;
import com.qa.portal.form.services.category.QuestionCategoryManagementService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/manage")
public class QuestionCategoryManagementController {

    private QuestionCategoryManagementService questionCategoryManagementService;

    public QuestionCategoryManagementController(QuestionCategoryManagementService questionCategoryManagementService) {
        this.questionCategoryManagementService = questionCategoryManagementService;
    }

    @PostMapping("form/category")
    public ResponseEntity<QuestionCategoryDto> createQuestionCategory(@RequestBody QuestionCategoryDto questionCategoryDto) {
        return ResponseEntity.ok(questionCategoryManagementService.createQuestionCategory(questionCategoryDto));
    }

    @PutMapping("form/category")
    public ResponseEntity<QuestionCategoryDto> updateQuestionCategory(@RequestBody QuestionCategoryDto questionCategoryDto) {
        return ResponseEntity.ok(questionCategoryManagementService.updateQuestionCategory(questionCategoryDto));
    }
}
