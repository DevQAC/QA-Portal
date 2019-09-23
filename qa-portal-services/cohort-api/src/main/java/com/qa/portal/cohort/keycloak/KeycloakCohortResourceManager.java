package com.qa.portal.cohort.keycloak;

import com.qa.portal.common.exception.QaPortalBusinessException;
import com.qa.portal.common.keycloak.KeycloakAdminClient;
import com.qa.portal.common.keycloak.KeycloakUserConstants;
import org.keycloak.representations.idm.RoleRepresentation;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        String cohortRoleName = KeycloakUserConstants.COHORT_ROLE_PREFIX + cohortName;
        keycloakCohortValidator.validateCohort(cohortRoleName);
        RoleRepresentation roleRepresentation = keycloakCohortFactory.createCohort(cohortName);
        keycloakAdminClient.getRealm().roles().create(roleRepresentation);
        return getRoleRepresentation(cohortRoleName).orElseThrow(() -> new QaPortalBusinessException("Error creating cohort in keycloak"));
    }

    public List<RoleRepresentation> getAllCohorts() {
        return keycloakAdminClient.getRealm().roles().list()
                .stream()
                .filter(roleRep -> roleRep.getName().startsWith(KeycloakUserConstants.COHORT_ROLE_PREFIX))
                .collect(Collectors.toList());
    }

    public Optional<RoleRepresentation> getRoleRepresentation(String cohortName) {
        return keycloakAdminClient.getRealm().roles().list().stream()
                .filter(r -> r.getName().equals(cohortName))
                .findFirst();
    }
}
