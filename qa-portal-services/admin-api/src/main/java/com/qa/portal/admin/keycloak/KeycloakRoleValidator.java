package com.qa.portal.admin.keycloak;

import com.qa.portal.common.dto.QaUserDto;
import com.qa.portal.common.exception.QaPortalBusinessException;
import org.springframework.stereotype.Component;

@Component
public class KeycloakRoleValidator {

    private KeycloakAdminClient keycloakAdminClient;

    public KeycloakRoleValidator(KeycloakAdminClient keycloakAdminClient) {
        this.keycloakAdminClient = keycloakAdminClient;
    }

    public void validateRole(String roleName) {
        if (roleExists(roleName)) {
            throw new QaPortalBusinessException("Role exists for supplied role name");
        }
    }

    private boolean roleExists(String roleName) {
        return keycloakAdminClient.getRealm().roles().list().stream()
                .filter(r -> r.getName().equals(roleName))
                .findAny()
                .map(r -> true)
                .orElseGet(() -> false);
    }
}
