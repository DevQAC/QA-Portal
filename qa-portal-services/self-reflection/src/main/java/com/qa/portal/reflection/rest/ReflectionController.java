package com.qa.portal.reflection.rest;

import java.time.LocalDate;
import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.qa.portal.reflection.dto.ReflectionDto;
import com.qa.portal.reflection.service.ReflectionService;

@RestController
public class ReflectionController {

	private ReflectionService service;

	public ReflectionController(ReflectionService service) {
		this.service = service;
	}

	public ResponseEntity<Set<ReflectionDto>> getSelfReflectionsForTrainee(Integer traineeId) {
		return ResponseEntity.ok(this.service.getSelfReflectionsForTrainee(traineeId));
	}

	public ResponseEntity<ReflectionDto> getSelfReflection(Integer id) {
		return ResponseEntity.ok(this.service.getSelfReflection(id));
	}

	public ResponseEntity<ReflectionDto> getSelfReflection(Integer userId, LocalDate date) {
		return ResponseEntity.ok(this.service.getSelfReflection(userId, date));
	}

	public ResponseEntity<ReflectionDto> createSelfReflection(ReflectionDto reflection) {
		return ResponseEntity.ok(this.service.createSelfReflection(reflection));
	}

	public ResponseEntity<ReflectionDto> updateSelfReflection(ReflectionDto reflection, Integer id) {
		return ResponseEntity.ok(this.service.updateSelfReflection(reflection, id));
	}

	public void getQuestionsForCohort(Integer cohortId) {

	}

}
