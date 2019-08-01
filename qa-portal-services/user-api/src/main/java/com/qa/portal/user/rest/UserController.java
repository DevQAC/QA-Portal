package com.qa.portal.user.rest;

import java.util.List;

import com.qa.portal.common.security.QaSecurityContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.qa.portal.common.dto.QaCohortDto;
import com.qa.portal.common.dto.TraineeDto;
import com.qa.portal.user.services.UserService;

@RestController
public class UserController {

    private UserService service;

    private QaSecurityContext securityContext;

    public UserController(UserService service, QaSecurityContext securityContext) {
        this.service = service;
        this.securityContext = securityContext;
    }

    @GetMapping("/user/trainee/{id}")
	public ResponseEntity<TraineeDto> getTraineeById(@PathVariable Integer id) {
		return ResponseEntity.ok(this.service.getTraineeById(id));
	}

    @GetMapping("/user/cohorts")
    public ResponseEntity<List<QaCohortDto>> getCohortsForTrainer() {
        return ResponseEntity.ok(this.service.getCohortsForTrainer(securityContext.getUserName()));
    }

}
