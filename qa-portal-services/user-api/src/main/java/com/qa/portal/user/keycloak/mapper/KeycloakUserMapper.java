package com.qa.portal.user.keycloak.mapper;

import com.qa.portal.common.dto.QaUserDetailsDto;
import com.qa.portal.common.dto.QaUserDto;
import com.qa.portal.common.exception.QaPortalBusinessException;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.qa.portal.user.keycloak.KeycloakUserConstants.*;

@Component
public class KeycloakUserMapper {

    public QaUserDetailsDto mapToUserDetailsDto(UserRepresentation userRepresentation) {
        QaUserDetailsDto userDetailsDto = new QaUserDetailsDto();
        userDetailsDto.setUser(createUserDto(userRepresentation));
        userDetailsDto.setRoleName(getPortalRole(userRepresentation.getRealmRoles()));
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

    private String getPortalRole(List<String> realmRoles) {
        return realmRoles.stream()
                .filter(r -> isPortalRole(r))
                .findFirst()
                .orElseThrow(() -> new QaPortalBusinessException("No role set up for user"));
    }

    private boolean isPortalRole(String roleName) {
        return !roleName.equals(UMA_AUTH_ROLE) &&
                !roleName.equals(OFFLINE_ACCESS_ROLE) &&
                !roleName.startsWith((COHORT_ROLE_PREFIX));
    }
}
