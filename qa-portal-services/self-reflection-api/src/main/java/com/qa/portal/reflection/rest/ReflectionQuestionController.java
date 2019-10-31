package com.qa.portal.reflection.rest;

import com.qa.portal.common.security.QaSecurityContext;
import com.qa.portal.reflection.dto.ReflectionQuestionDto;
import com.qa.portal.reflection.service.ReflectionQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

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

    @PutMapping
    public List<ReflectionQuestionDto> updateReflectionQuestions(@RequestBody Set<ReflectionQuestionDto> reflectionQuestions) {
        return this.service.updateReflectionQuestions(reflectionQuestions);
    }
}
