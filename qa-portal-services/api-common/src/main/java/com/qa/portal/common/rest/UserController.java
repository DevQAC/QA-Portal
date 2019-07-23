package com.qa.portal.common.rest;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.portal.common.dto.QaCohortDto;
import com.qa.portal.common.services.UserService;

@RestController
public class UserController {
	
	private UserService service;
	
	@GetMapping("/user/getCohorts/{id}")
	public ResponseEntity<List<QaCohortDto>> getCohortsForTrainer(Integer id) {
		return ResponseEntity.ok(this.service.getCohortsForTrainer(id));
	}

}
