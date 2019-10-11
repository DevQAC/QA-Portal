package com.qa.portal.cohort.keycloak;

import com.qa.portal.common.keycloak.KeycloakAdminClient;
import org.keycloak.representations.idm.RoleRepresentation;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class KeycloakCohortValidator {
    private KeycloakAdminClient keycloakAdminClient;

    public KeycloakCohortValidator(KeycloakAdminClient keycloakAdminClient) {
        this.keycloakAdminClient = keycloakAdminClient;
    }

    public boolean cohortExists(String cohortName) {
        return getRoleRepresentation(cohortName)
                .map(r -> true)
                .orElseGet(() -> false);
    }

    private Optional<RoleRepresentation> getRoleRepresentation(String cohortName) {
        return keycloakAdminClient.getRealm().roles().list().stream()
                .filter(r -> r.getName().equals(cohortName))
                .findFirst();
    }
}
