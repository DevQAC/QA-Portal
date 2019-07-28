package com.qa.portal.reflection.rest;

import java.util.Set;

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

	public ReflectionQuestionController(ReflectionQuestionService service) {
		this.service = service;
	}
	
	@GetMapping("/reflection-id/{id}")
	public Set<ReflectionQuestionDto> getReflectionQuestionsByReflectionId(@PathVariable Integer id) {
		return this.service.getReflectionQuestionsByReflectionId(id);
	}
	
	@PutMapping
	public Set<ReflectionQuestionDto> updateReflectionQuestions(@RequestBody Set<ReflectionQuestionDto> reflectionQuestions) {
		return this.service.updateReflectionQuestions(reflectionQuestions);
	}

}
