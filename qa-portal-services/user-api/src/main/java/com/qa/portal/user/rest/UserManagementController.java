package com.qa.portal.user.rest;

import com.qa.portal.common.dto.QaUserDetailsDto;
import com.qa.portal.common.security.QaSecurityContext;
import com.qa.portal.user.services.UserManagementService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("manage")
public class UserManagementController {

    private final Logger LOGGER = LoggerFactory.getLogger(UserManagementController.class);

    private UserManagementService userManagementService;

    private QaSecurityContext qaSecurityContext;

    public UserManagementController(UserManagementService userManagementService,
                                    QaSecurityContext qaSecurityContext) {
        this.userManagementService = userManagementService;
        this.qaSecurityContext = qaSecurityContext;
    }


    @GetMapping("users")
    public ResponseEntity<List<QaUserDetailsDto>> getUsersFromKeycloak() {
        return ResponseEntity.ok(userManagementService.getAllUsersFromKeycloak());
    }

    @GetMapping("users/trainees/no-cohort")
    public ResponseEntity<List<QaUserDetailsDto>> getTraineesWithoutCohort() {
        return ResponseEntity.ok(userManagementService.getTraineesWithoutCohort());
    }

    @GetMapping("users/available/trainers/cohort/{cohortName}")
    public ResponseEntity<List<QaUserDetailsDto>> getAvailableTrainersForCohort(@PathVariable("cohortName") String cohortName) {
        return ResponseEntity.ok(userManagementService.getAvailableTrainersForCohort(cohortName));
    }

    @GetMapping("users/trainees")
    public ResponseEntity<List<QaUserDetailsDto>> getTrainees() {
        return ResponseEntity.ok(userManagementService.getTrainees());
    }

    @GetMapping("users/trainers")
    public ResponseEntity<List<QaUserDetailsDto>> getTrainers() {
        return ResponseEntity.ok(userManagementService.getTrainers());
    }

    @PostMapping("user")
    public ResponseEntity<QaUserDetailsDto> createUser(@RequestBody QaUserDetailsDto userDetails) {
        return ResponseEntity.ok(userManagementService.createUserDetails(userDetails));
    }

    @PutMapping("user")
    public ResponseEntity<QaUserDetailsDto> updateUser(@RequestBody QaUserDetailsDto userDetails) {
        return ResponseEntity.ok(userManagementService.updateUserDetails(userDetails));
    }

    @PutMapping("users")
    public ResponseEntity<List<QaUserDetailsDto>> updateUsers(@RequestBody List<QaUserDetailsDto> userDetails) {
        return ResponseEntity.ok(userManagementService.updateUsers(userDetails));
    }

    @PutMapping("users/delete")
    public ResponseEntity<?> deleteUsers(@RequestBody List<QaUserDetailsDto> users) {
        userManagementService.deleteUsers(users);
        return ResponseEntity.ok().build();
    }
}
