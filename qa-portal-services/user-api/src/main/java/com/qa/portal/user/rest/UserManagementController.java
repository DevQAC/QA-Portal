package com.qa.portal.user.rest;

import com.qa.portal.common.security.QaSecurityContext;
import com.qa.portal.user.dto.QaUserDetailsDto;
import com.qa.portal.user.services.UserManagementService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserManagementController {

    private final Logger LOGGER = LoggerFactory.getLogger(UserManagementController.class);

    private UserManagementService userManagementService;

    private QaSecurityContext qaSecurityContext;

    public UserManagementController(UserManagementService userManagementService,
                                    QaSecurityContext qaSecurityContext) {
        this.userManagementService = userManagementService;
        this.qaSecurityContext = qaSecurityContext;
    }

    @PostMapping
    public ResponseEntity<QaUserDetailsDto> createUser(@RequestBody QaUserDetailsDto userDetails) {
        return ResponseEntity.ok(userManagementService.createUserDetails(userDetails));
    }

    @PutMapping
    public ResponseEntity<QaUserDetailsDto> updateUser(@RequestBody QaUserDetailsDto userDetails) {
        return ResponseEntity.ok(userManagementService.updateUserDetails(userDetails));
    }

    @DeleteMapping("{userName}")
    public ResponseEntity<?> deleteUser(@PathVariable("userName") String userName) {
        userManagementService.deleteUser(userName);
        return ResponseEntity.ok().build();
    }
}
