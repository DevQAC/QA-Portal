package com.qa.portal.cohort.keycloak;

import com.qa.portal.common.dto.QaUserDto;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Component;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class KeycloakUserFactory {

    public static final String UPDATE_PASSWORD_ACTION = "UPDATE_PASSWORD";

    public UserRepresentation createKeycloakUser(QaUserDto userDetails) {
        CredentialRepresentation credential = new CredentialRepresentation();
        credential.setType(CredentialRepresentation.PASSWORD);
        credential.setTemporary(Boolean.TRUE);
        credential.setValue(generatePassword());
        UserRepresentation user = new UserRepresentation();
        user.setUsername(userDetails.getUserName());
        user.setFirstName(userDetails.getFirstName());
        user.setLastName(userDetails.getLastName());
        user.setEmail(userDetails.getEmail());
        user.setCredentials(Arrays.asList(credential));
        user.setEmailVerified(Boolean.FALSE);
        user.setEnabled(true);
        user.setRequiredActions(getRequiredActions());
        return user;
    }

    private String generatePassword() {
        return RandomStringUtils.randomAlphanumeric(8);
    }

    private List<String> getRequiredActions() {
        List<String> requiredActions = new ArrayList<>();
        requiredActions.add(UPDATE_PASSWORD_ACTION);
        return requiredActions;
    }
}
