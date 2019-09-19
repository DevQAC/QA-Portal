package com.qa.portal.core.keycloak;

import org.keycloak.representations.idm.RoleRepresentation;
import org.springframework.stereotype.Component;

@Component
public class KeycloakRoleFactory {

    public RoleRepresentation createKeycloakRole(String roleName) {
        RoleRepresentation roleRepresentation = new RoleRepresentation();
        roleRepresentation.setName(roleName);
        return roleRepresentation;
    }
}
