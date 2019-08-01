package com.qa.portal.reflection.rest;

import java.util.Set;

import com.qa.portal.common.exception.QaResourceNotFoundException;
import com.qa.portal.common.security.QaSecurityContext;
import com.qa.portal.reflection.dto.QuestionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.portal.reflection.dto.ReflectionQuestionDto;
import com.qa.portal.reflection.service.ReflectionQuestionService;

@RestController
@RequestMapping("/reflection-question")
public class ReflectionQuestionController {
	
	private ReflectionQuestionService service;

	private QaSecurityContext securityContext;

	@Autowired
	public ReflectionQuestionController(ReflectionQuestionService service, QaSecurityContext securityContext) {
		this.service = service;
		this.securityContext = securityContext;
	}

	@GetMapping("/reflection-id/{id}")
	public Set<ReflectionQuestionDto> getReflectionQuestionsByReflectionId(@PathVariable Integer id) {
		return this.service.getReflectionQuestionsByReflectionId(id);
	}

    @GetMapping("/questions")
    public Set<QuestionDto> getReflectionQuestionsByCohort() {
        return this.service.getReflectionQuestionsByCohort(securityContext.getCohorts()
                .stream()
                .findFirst()
                .orElseThrow(() -> new QaResourceNotFoundException("No cohorts for user")));
    }

    @PutMapping
    public Set<ReflectionQuestionDto> updateReflectionQuestions(@RequestBody Set<ReflectionQuestionDto> reflectionQuestions) {
        return this.service.updateReflectionQuestions(reflectionQuestions);
    }

}
