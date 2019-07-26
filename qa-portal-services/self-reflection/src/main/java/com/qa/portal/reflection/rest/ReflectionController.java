package com.qa.portal.reflection.rest;

import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.portal.common.security.QaSecurityContext;
import com.qa.portal.reflection.dto.ReflectionDto;
import com.qa.portal.reflection.service.ReflectionService;

@RestController
@RequestMapping("/reflection")
public class ReflectionController {

	private ReflectionService service;

	private QaSecurityContext context;

	public ReflectionController(ReflectionService service, QaSecurityContext context) {
		this.service = service;
		this.context = context;
	}

	@GetMapping("/current")
	public ResponseEntity<Set<ReflectionDto>> getSelfReflectionsForTrainee() {
		return ResponseEntity.ok(this.service.getSelfReflectionsForTrainee(context.getUserName()));
	}

	@GetMapping("{id}")
	public ResponseEntity<ReflectionDto> getSelfReflection(@PathVariable Integer id) {
		return ResponseEntity.ok(this.service.getSelfReflection(id));
	}

//	public ResponseEntity<ReflectionDto> getSelfReflection(Integer userId, LocalDate date) {
//		return ResponseEntity.ok(this.service.getSelfReflection(userId, date));
//	}
	@PostMapping
	public ResponseEntity<ReflectionDto> createSelfReflection(@RequestBody ReflectionDto reflection) {
		return ResponseEntity.ok(this.service.createSelfReflection(reflection, context.getUserName()));
	}

	@PutMapping()
	public ResponseEntity<ReflectionDto> updateSelfReflection(ReflectionDto reflection) {
		return ResponseEntity.ok(this.service.updateSelfReflection(reflection));
	}

}
