package com.qa.portal.user.rest;

import com.qa.portal.common.dto.QaCohortDto;
import com.qa.portal.common.security.QaSecurityContext;
import com.qa.portal.user.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
	
	private UserService service;

	private QaSecurityContext securityContext;

	public UserController(UserService service, QaSecurityContext securityContext) {
		this.service = service;
		this.securityContext = securityContext;
	}

	@GetMapping("/user/cohorts")
	public ResponseEntity<List<QaCohortDto>> getCohortsForTrainer() {
		return ResponseEntity.ok(this.service.getCohortsForTrainer(securityContext.getUserName()));
	}

}
