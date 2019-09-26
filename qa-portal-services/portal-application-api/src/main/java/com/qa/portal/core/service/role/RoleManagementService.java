package com.qa.portal.core.service.role;

import com.qa.portal.core.dto.RoleDto;
import com.qa.portal.core.keycloak.KeycloakRoleResourceManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RoleManagementService {

    private KeycloakRoleResourceManager keycloakRoleResourceManager;

    private CreateRoleOperation createRoleOperation;

    private UpdateRoleOperation updateRoleOperation;

    public RoleManagementService(KeycloakRoleResourceManager keycloakRoleResourceManager,
                                 CreateRoleOperation createRoleOperation,
                                 UpdateRoleOperation updateRoleOperation) {
        this.keycloakRoleResourceManager = keycloakRoleResourceManager;
        this.createRoleOperation = createRoleOperation;
        this.updateRoleOperation = updateRoleOperation;
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
    public List<String> getPortalRoles() {
        return keycloakRoleResourceManager.getPortalRoles();
    }
}
