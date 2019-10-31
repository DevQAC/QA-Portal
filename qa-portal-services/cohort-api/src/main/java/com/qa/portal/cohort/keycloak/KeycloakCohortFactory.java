package com.qa.portal.cohort.keycloak;

import com.qa.portal.common.keycloak.KeycloakUserConstants;
import org.keycloak.representations.idm.RoleRepresentation;
import org.springframework.stereotype.Component;

@Component
public class KeycloakCohortFactory {

    public RoleRepresentation createCohort(String cohortName) {
        RoleRepresentation roleRepresentation = new RoleRepresentation();
        roleRepresentation.setName(KeycloakUserConstants.COHORT_ROLE_PREFIX + cohortName);
        return roleRepresentation;
    }
}
