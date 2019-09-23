package com.qa.portal.core.rest;

import com.qa.portal.core.service.RoleManagementService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/manage")
public class RoleManagementController {

    private final Logger LOGGER = LoggerFactory.getLogger(RoleManagementController.class);

    private RoleManagementService roleManagementService;

    public RoleManagementController(RoleManagementService roleManagementService) {
        this.roleManagementService = roleManagementService;
    }

    @GetMapping("roles")
    public ResponseEntity<List<String>> getPortalRolesFromKeycloak() {
        return ResponseEntity.ok(roleManagementService.getPortalRoles());
    }
}
