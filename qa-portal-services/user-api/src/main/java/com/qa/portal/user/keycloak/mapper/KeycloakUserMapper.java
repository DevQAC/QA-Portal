package com.qa.portal.user.keycloak.mapper;

import com.qa.portal.common.dto.QaUserDetailsDto;
import com.qa.portal.common.dto.QaUserDto;
import com.qa.portal.common.exception.QaPortalBusinessException;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.qa.portal.common.keycloak.KeycloakUserConstants.*;

@Component
public class KeycloakUserMapper {

    private final Logger LOGGER = LoggerFactory.getLogger(KeycloakUserMapper.class);

    public QaUserDetailsDto mapToUserDetailsDto(UserResource userResource) {
        QaUserDetailsDto userDetailsDto = new QaUserDetailsDto();
        UserRepresentation userRepresentation = userResource.toRepresentation();
        userDetailsDto.setUser(createUserDto(userRepresentation));
        userDetailsDto.setRoleName(getPortalRole(userResource.roles().realmLevel().listAll()));
        userDetailsDto.setCohortName(getCohortName(userResource.roles().realmLevel().listAll()));
        return userDetailsDto;
    }

    private QaUserDto createUserDto(UserRepresentation userRepresentation) {
        QaUserDto userDto = new QaUserDto();
        userDto.setUserName(userRepresentation.getUsername());
        userDto.setEmail(userRepresentation.getEmail());
        userDto.setFirstName(userRepresentation.getFirstName());
        userDto.setLastName(userRepresentation.getLastName());
        return userDto;
    }

    private String getPortalRole(List<RoleRepresentation> realmRoles) {
        return realmRoles.stream()
                .filter(r -> isPortalRole(r.getName()))
                .findFirst()
                .map(r -> r.getName())
                .orElseThrow(() -> new QaPortalBusinessException("No role set up for user"));
    }

    private String getCohortName(List<RoleRepresentation> realmRoles) {
        return realmRoles.stream()
                .filter(r -> r.getName().startsWith(COHORT_ROLE_PREFIX))
                .map(r -> r.getName())
                .findFirst()
                .orElseGet(() -> null);
    }

    private boolean isPortalRole(String roleName) {
        return !roleName.equals(UMA_AUTH_ROLE) &&
                !roleName.equals(OFFLINE_ACCESS_ROLE) &&
                !roleName.startsWith((COHORT_ROLE_PREFIX));
    }
}
