package com.qa.portal.form.rest;

import com.qa.portal.common.dto.QuestionDto;
import com.qa.portal.form.services.question.QuestionManagementService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/manage")
public class QuestionManagementController {

    private QuestionManagementService questionManagementService;

    public QuestionManagementController(QuestionManagementService questionManagementService) {
        this.questionManagementService = questionManagementService;
    }

    @PostMapping("question")
    public ResponseEntity<QuestionDto> createQuestion(@RequestBody QuestionDto questionDto) {
        return ResponseEntity.ok(questionManagementService.createQuestion(questionDto));
    }

    @PutMapping("question")
    public ResponseEntity<QuestionDto> updateQuestion(@RequestBody QuestionDto questionDto) {
        return ResponseEntity.ok(questionManagementService.updateQuestion(questionDto));
    }
}
