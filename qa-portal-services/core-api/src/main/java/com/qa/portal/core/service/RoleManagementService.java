package com.qa.portal.core.service;

import com.qa.portal.common.keycloak.KeycloakAdminClient;
import com.qa.portal.core.keycloak.KeycloakRoleResourceManager;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.qa.portal.common.keycloak.KeycloakUserConstants.*;

@Service
public class RoleManagementService {

    private KeycloakAdminClient keycloakAdminClient;

    public RoleManagementService(KeycloakAdminClient keycloakAdminClient) {
        this.keycloakAdminClient = keycloakAdminClient;
    }

    public List<String> getPortalRoles() {
        return keycloakAdminClient.getRealm().roles().list()
                .stream()
                .filter(r -> isPortalRole(r.getName()))
                .map(r -> r.getName())
                .collect(Collectors.toList());
    }

    private boolean isPortalRole(String roleName) {
        return !roleName.equals(UMA_AUTH_ROLE) &&
                !roleName.equals(OFFLINE_ACCESS_ROLE) &&
                !roleName.startsWith((COHORT_ROLE_PREFIX));
    }
}
