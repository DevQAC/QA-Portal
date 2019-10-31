package com.qa.portal.application.rest;

import com.qa.portal.application.dto.RoleDto;
import com.qa.portal.application.service.role.RoleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoleController {

    private RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("role/{id}")
    public ResponseEntity<RoleDto> getRoleById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(roleService.getRole(id));
    }
}
