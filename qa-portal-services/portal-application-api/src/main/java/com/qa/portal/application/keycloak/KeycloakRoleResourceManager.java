package com.qa.portal.application.keycloak;

import com.qa.portal.common.exception.QaPortalBusinessException;
import com.qa.portal.common.keycloak.KeycloakAdminClient;
import org.keycloak.representations.idm.RoleRepresentation;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.qa.portal.common.keycloak.KeycloakUserConstants.*;

@Component
public class KeycloakRoleResourceManager {

    private KeycloakRoleValidator keycloakRoleValidator;

    private KeycloakRoleFactory keycloakRoleFactory;

    private KeycloakAdminClient keycloakAdminClient;

    public KeycloakRoleResourceManager(KeycloakRoleValidator keycloakRoleValidator,
                                       KeycloakRoleFactory keycloakRoleFactory,
                                       KeycloakAdminClient keycloakAdminClient) {
        this.keycloakRoleValidator = keycloakRoleValidator;
        this.keycloakRoleFactory = keycloakRoleFactory;
        this.keycloakAdminClient = keycloakAdminClient;
    }

    public RoleRepresentation createRole(String roleName) {
        keycloakRoleValidator.validateRole(roleName);
        RoleRepresentation roleRepresentation = keycloakRoleFactory.createKeycloakRole(roleName);
        keycloakAdminClient.getRealm().roles().create(roleRepresentation);
        return getRoleRepresentation(roleName).orElseThrow(() -> new QaPortalBusinessException("Error creating role in keycloak"));
    }

    public Optional<RoleRepresentation> getRoleRepresentation(String roleName) {
        return keycloakAdminClient.getRealm().roles().list().stream()
                .filter(r -> r.getName().equals(roleName))
                .findFirst();
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
