package com.qa.portal.common.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.portal.common.dto.QaCohortDto;
import com.qa.portal.common.services.UserService;

@RestController
public class UserController {
	
	private UserService service;

	@Autowired
	public UserController(UserService service) {
		this.service = service;
	}

	@GetMapping("/user/getCohorts/{id}")
	public ResponseEntity<List<QaCohortDto>> getCohortsForTrainer(Integer id) {
		return ResponseEntity.ok(this.service.getCohortsForTrainer(id));
	}

}
