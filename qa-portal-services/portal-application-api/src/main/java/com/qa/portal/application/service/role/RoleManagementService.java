package com.qa.portal.application.service.role;

import com.qa.portal.application.dto.RoleDto;
import com.qa.portal.application.keycloak.KeycloakRoleResourceManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleManagementService {

    private KeycloakRoleResourceManager keycloakRoleResourceManager;

    private CreateRoleOperation createRoleOperation;

    private UpdateRoleOperation updateRoleOperation;

    private GetRoleByNameOperation getRoleByNameOperation;

    public RoleManagementService(KeycloakRoleResourceManager keycloakRoleResourceManager,
                                 CreateRoleOperation createRoleOperation,
                                 UpdateRoleOperation updateRoleOperation,
                                 GetRoleByNameOperation getRoleByNameOperation) {
        this.keycloakRoleResourceManager = keycloakRoleResourceManager;
        this.createRoleOperation = createRoleOperation;
        this.updateRoleOperation = updateRoleOperation;
        this.getRoleByNameOperation = getRoleByNameOperation;
    }

    public RoleDto createRole(RoleDto roleDto) {
        keycloakRoleResourceManager.createRole(roleDto.getName());
        createRoleOperation.createRole(roleDto);
        return roleDto;
    }

    public RoleDto updateRole(RoleDto roleDto) {
        updateRoleOperation.updateRole(roleDto);
        return roleDto;
    }

    @Transactional
    public List<RoleDto> getPortalRoles() {
        return keycloakRoleResourceManager.getPortalRoles().stream()
                .map(r -> getRoleByNameOperation.getRoleByName(r))
                .collect(Collectors.toList());
    }
}
