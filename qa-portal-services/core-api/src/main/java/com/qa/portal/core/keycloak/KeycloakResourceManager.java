package com.qa.portal.core.keycloak;

import com.qa.portal.common.exception.QaMultiStepCommitContext;
import com.qa.portal.common.exception.QaPortalMultiStepCommitException;
import com.qa.portal.core.dto.QaUserDetailsDto;
import com.qa.portal.common.exception.QaPortalBusinessException;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Optional;

@Component
public class KeycloakResourceManager {

    private final Logger LOGGER = LoggerFactory.getLogger(KeycloakResourceManager.class);

    private KeycloakUserValidator keycloakUserValidator;

    private KeycloakRoleValidator keycloakRoleValidator;

    private KeycloakUserFactory keycloakUserFactory;

    private KeycloakRoleFactory keycloakRoleFactory;

    private KeycloakAdminClient keycloakAdminClient;

    public KeycloakResourceManager(KeycloakUserValidator keycloakUserValidator,
                                   KeycloakRoleValidator keycloakRoleValidator,
                                   KeycloakUserFactory keycloakUserFactory,
                                   KeycloakRoleFactory keycloakRoleFactory,
                                   KeycloakAdminClient keycloakAdminClient) {
        this.keycloakUserValidator = keycloakUserValidator;
        this.keycloakRoleValidator = keycloakRoleValidator;
        this.keycloakUserFactory = keycloakUserFactory;
        this.keycloakRoleFactory = keycloakRoleFactory;
        this.keycloakAdminClient = keycloakAdminClient;
    }

    public void createUserAndRole(QaUserDetailsDto qaUserDetailsDto) {
        try {
            UserRepresentation userRepresentation = createUser(qaUserDetailsDto);
            RoleRepresentation roleRepresentation = createRole(qaUserDetailsDto.getRoleName());
            assignRoleToUser(userRepresentation, roleRepresentation);
        } catch (Exception e) {
            throw new QaPortalMultiStepCommitException(new QaMultiStepCommitContext(this.getClass().getName(),
                    qaUserDetailsDto,
                    QaUserDetailsDto.class,
                    2), e.getMessage());
        }
    }

    public UserRepresentation createUser(QaUserDetailsDto userDetails) {
        keycloakUserValidator.validateUser(userDetails);
        UserRepresentation userRepresentation = keycloakUserFactory.createKeycloakUser(userDetails.getUser());
        keycloakAdminClient.getRealm().users().create(userRepresentation);
        return refreshUserRepresentation(userRepresentation).orElseGet(() -> userRepresentation);
    }

    public RoleRepresentation createRole(String roleName) {
        keycloakRoleValidator.validateRole(roleName);
        RoleRepresentation roleRepresentation = null;
        if (!keycloakRoleValidator.roleExists(roleName)) {
            roleRepresentation = keycloakRoleFactory.createKeycloakRole(roleName);
            keycloakAdminClient.getRealm().roles().create(roleRepresentation);
        }
        return refreshRoleRepresentation(roleName).orElseThrow(() -> new QaPortalBusinessException("Error creating role in keycloak"));
    }

    public void assignRoleToUser(UserRepresentation userRepresentation, RoleRepresentation roleRepresentation) {
        UserResource userResource = keycloakAdminClient.getRealm().users().get(userRepresentation.getId());
        userResource.roles().realmLevel().add(Arrays.asList(roleRepresentation));
    }

    private Optional<UserRepresentation> refreshUserRepresentation(UserRepresentation userRepresentation) {
        return keycloakAdminClient.getRealm().users().list().stream()
                .filter(u -> u.getUsername().equals(userRepresentation.getUsername()))
                .findFirst();
    }

    private Optional<RoleRepresentation> refreshRoleRepresentation(String roleName) {
        return keycloakAdminClient.getRealm().roles().list().stream()
                .filter(r -> r.getName().equals(roleName))
                .findFirst();
    }
}
