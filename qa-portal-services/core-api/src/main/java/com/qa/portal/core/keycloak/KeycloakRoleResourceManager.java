package com.qa.portal.core.keycloak;

import com.qa.portal.common.exception.QaPortalBusinessException;
import com.qa.portal.common.keycloak.KeycloakAdminClient;
import org.keycloak.representations.idm.RoleRepresentation;
import org.springframework.stereotype.Component;

import java.util.Optional;

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
}
