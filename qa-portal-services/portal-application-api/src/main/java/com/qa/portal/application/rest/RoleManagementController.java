package com.qa.portal.application.rest;

import com.qa.portal.application.dto.RoleDto;
import com.qa.portal.application.service.role.RoleManagementService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/manage")
public class RoleManagementController {

    private final Logger LOGGER = LoggerFactory.getLogger(RoleManagementController.class);

    private RoleManagementService roleManagementService;

    public RoleManagementController(RoleManagementService roleManagementService) {
        this.roleManagementService = roleManagementService;
    }

    @GetMapping("roles/names")
    public ResponseEntity<List<String>> getPortalRolesNamesFromKeycloak() {
        return ResponseEntity.ok(roleManagementService.getPortalRolesNames());
    }

    @GetMapping("roles")
    public ResponseEntity<List<RoleDto>> getPortalRolesFromKeycloak() {
        return ResponseEntity.ok(roleManagementService.getPortalRoles());
    }

    @PostMapping("role")
    public ResponseEntity<RoleDto> createRole(@RequestBody RoleDto roleDto) {
        return ResponseEntity.ok(roleManagementService.createRole(roleDto));
    }

    @PutMapping("role")
    public ResponseEntity<RoleDto> updateRole(@RequestBody RoleDto roleDto) {
        return ResponseEntity.ok(roleManagementService.updateRole(roleDto));
    }
 }
