package com.qa.portal.cohort.keycloak.mapper;

import com.qa.portal.common.dto.QaUserDetailsDto;
import com.qa.portal.common.dto.QaUserDto;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static com.qa.portal.common.keycloak.KeycloakUserConstants.*;

@Component
public class KeycloakUserMapper {

    private final Logger LOGGER = LoggerFactory.getLogger(KeycloakUserMapper.class);

    public QaUserDetailsDto mapToUserDetailsDto(UserResource userResource) {
        QaUserDetailsDto userDetailsDto = new QaUserDetailsDto();
        UserRepresentation userRepresentation = userResource.toRepresentation();
        userDetailsDto.setUser(createUserDto(userRepresentation));
        userDetailsDto.setRoleNames(getPortalRoles(userResource.roles().realmLevel().listAll()));
        userDetailsDto.setCohortNames(getCohortNames(userResource.roles().realmLevel().listAll()));
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

    private List<String> getPortalRoles(List<RoleRepresentation> realmRoles) {
        return realmRoles.stream()
                .filter(r -> isPortalRole(r.getName()))
                .map(r -> r.getName())
                .collect(Collectors.toList());
    }

    private List<String> getCohortNames(List<RoleRepresentation> realmRoles) {
        return realmRoles.stream()
                .filter(r -> r.getName().startsWith(COHORT_ROLE_PREFIX))
                .map(r -> r.getName().replace('_', ' ').substring(COHORT_ROLE_PREFIX.length()))
                .collect(Collectors.toList());
    }

    private boolean isPortalRole(String roleName) {
        return !roleName.equals(UMA_AUTH_ROLE) &&
                !roleName.equals(OFFLINE_ACCESS_ROLE) &&
                !roleName.startsWith((COHORT_ROLE_PREFIX));
    }
}
