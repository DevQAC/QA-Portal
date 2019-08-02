package com.qa.portal.user.rest;

import java.util.List;

import com.qa.portal.common.security.QaSecurityContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.qa.portal.common.dto.QaCohortDto;
import com.qa.portal.common.services.UserService;

@RestController
public class UserController {

    private UserService service;
    private QaSecurityContext qaSecurityContext;


    @Autowired
    public UserController(UserService service, QaSecurityContext qaSecurityContext) {
        this.service = service;
        this.qaSecurityContext = qaSecurityContext;
    }


    @GetMapping("/user/cohorts/{id}")
    public ResponseEntity<List<QaCohortDto>> getCohortsForTrainer(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(this.service.getCohortsForTrainer(id));
    }
    @GetMapping("/user/cohort")
    public ResponseEntity<QaCohortDto> getCohortForTrainee(){
        return ResponseEntity.ok(this.service.getCohortForTrainee(this.qaSecurityContext.getUserName()));

    }

}
