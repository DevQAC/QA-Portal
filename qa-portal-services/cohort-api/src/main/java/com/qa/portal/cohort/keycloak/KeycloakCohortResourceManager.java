package com.qa.portal.cohort.keycloak;

import com.qa.portal.common.exception.QaPortalBusinessException;
import com.qa.portal.common.keycloak.KeycloakAdminClient;
import com.qa.portal.common.keycloak.KeycloakUserConstants;
import org.keycloak.representations.idm.RoleRepresentation;
import org.springframework.stereotype.Component;

@Component
public class KeycloakCohortResourceManager {

    private KeycloakCohortFactory keycloakCohortFactory;

    private KeycloakCohortValidator keycloakCohortValidator;

    private KeycloakAdminClient keycloakAdminClient;

    public KeycloakCohortResourceManager(KeycloakCohortFactory keycloakCohortFactory,
                                         KeycloakCohortValidator keycloakCohortValidator,
                                         KeycloakAdminClient keycloakAdminClient) {
        this.keycloakCohortFactory = keycloakCohortFactory;
        this.keycloakCohortValidator = keycloakCohortValidator;
        this.keycloakAdminClient = keycloakAdminClient;
    }

    public RoleRepresentation createCohort(String cohortName) {
        String cohortRoleName = KeycloakUserConstants.COHORT_ROLE_PREFIX + cohortName.replace(' ', '_');
        if (!keycloakCohortValidator.cohortExists(cohortRoleName)) {
            RoleRepresentation roleRepresentation = keycloakCohortFactory.createCohort(cohortName);
            keycloakAdminClient.getRealm().roles().create(roleRepresentation);
            return roleRepresentation;
        } else {
            throw new QaPortalBusinessException("Cohort already exists with the supplied name");
        }
    }
}
