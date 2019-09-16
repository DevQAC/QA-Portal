package com.qa.portal.admin.rest;

import com.qa.portal.admin.dto.QaUserAndRoleDto;
import com.qa.portal.admin.services.UserManagementService;
import com.qa.portal.common.security.QaSecurityContext;
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
    public ResponseEntity<QaUserAndRoleDto> createUser(@RequestBody QaUserAndRoleDto userDetails) {
        LOGGER.info("In create user in controller");
        return ResponseEntity.ok(userManagementService.createUser(userDetails));
    }

    @PutMapping
    public ResponseEntity<QaUserAndRoleDto> updateUser(@RequestBody QaUserAndRoleDto userDetails) {
        LOGGER.info("In update user in controller");
        return ResponseEntity.ok(userManagementService.updateUser(userDetails));
    }

    @DeleteMapping("{userName}")
    public ResponseEntity<?> deleteUser(@PathVariable("userName") String userName) {
        userManagementService.deleteUser(userName);
        return ResponseEntity.ok().build();
    }
}
