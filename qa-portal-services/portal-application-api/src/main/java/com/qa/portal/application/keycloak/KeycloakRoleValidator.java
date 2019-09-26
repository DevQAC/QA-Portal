package com.qa.portal.application.keycloak;

import com.qa.portal.common.keycloak.KeycloakAdminClient;
import org.springframework.stereotype.Component;

@Component
public class KeycloakRoleValidator {

    private KeycloakAdminClient keycloakAdminClient;

    public KeycloakRoleValidator(KeycloakAdminClient keycloakAdminClient) {
        this.keycloakAdminClient = keycloakAdminClient;
    }

    public void validateRole(String roleName) {
    }

    public boolean roleExists(String roleName) {
        return keycloakAdminClient.getRealm().roles().list().stream()
                .filter(r -> r.getName().equals(roleName))
                .findAny()
                .map(r -> true)
                .orElseGet(() -> false);
    }
}
