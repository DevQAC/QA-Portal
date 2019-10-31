package com.qa.portal.form.rest;

import com.qa.portal.common.dto.QuestionCategoryDto;
import com.qa.portal.form.services.category.QuestionCategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class QuestionCategoryController {

    private QuestionCategoryService questionCategoryService;

    public QuestionCategoryController(QuestionCategoryService questionCategoryService) {
        this.questionCategoryService = questionCategoryService;
    }

    @GetMapping("/form/{formName}/categories")
    public ResponseEntity<List<QuestionCategoryDto>> getQuestionCategoriesForFormType(@PathVariable("formName") String formName) {
        return ResponseEntity.ok(this.questionCategoryService.getQuestionCategoriesForFormType(formName));
    }
}
