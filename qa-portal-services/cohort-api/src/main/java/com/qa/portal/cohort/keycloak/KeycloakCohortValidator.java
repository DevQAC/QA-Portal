package com.qa.portal.cohort.keycloak;

import com.qa.portal.common.keycloak.KeycloakAdminClient;
import com.qa.portal.common.keycloak.KeycloakUserConstants;

public class KeycloakCohortValidator {
    private KeycloakAdminClient keycloakAdminClient;

    public KeycloakCohortValidator(KeycloakAdminClient keycloakAdminClient) {
        this.keycloakAdminClient = keycloakAdminClient;
    }

    public void validateCohort(String cohortName) {
    }

    public boolean cohortExists(String cohortName) {
        return keycloakAdminClient.getRealm().roles().list().stream()
                .filter(r -> r.getName().equals(KeycloakUserConstants.COHORT_ROLE_PREFIX + cohortName))
                .findAny()
                .map(r -> true)
                .orElseGet(() -> false);
    }
}
