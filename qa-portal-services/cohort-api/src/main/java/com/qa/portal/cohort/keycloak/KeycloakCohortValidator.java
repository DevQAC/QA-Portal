package com.qa.portal.cohort.keycloak;

import com.qa.portal.common.keycloak.KeycloakAdminClient;
import org.springframework.stereotype.Component;

@Component
public class KeycloakCohortValidator {
    private KeycloakAdminClient keycloakAdminClient;

    public KeycloakCohortValidator(KeycloakAdminClient keycloakAdminClient) {
        this.keycloakAdminClient = keycloakAdminClient;
    }

    public void validateCohort(String cohortName) {
    }
}
