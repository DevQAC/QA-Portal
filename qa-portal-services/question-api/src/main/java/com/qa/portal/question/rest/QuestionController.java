package com.qa.portal.question.rest;

import com.qa.portal.common.dto.QuestionCategoryDto;
import com.qa.portal.common.security.QaSecurityContext;
import com.qa.portal.question.services.QuestionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class QuestionController {

    private QuestionService service;

    private QaSecurityContext qaSecurityContext;

    public QuestionController(QuestionService service, QaSecurityContext qaSecurityContext) {
        this.service = service;
        this.qaSecurityContext = qaSecurityContext;
    }

    @GetMapping("/question/formtype/{formName}")
    public ResponseEntity<List<QuestionCategoryDto>> getQuestionsForFormType(@PathVariable("formName") String formName) {
        return ResponseEntity.ok(this.service.getQuestionsForFormType(formName));
    }
}
