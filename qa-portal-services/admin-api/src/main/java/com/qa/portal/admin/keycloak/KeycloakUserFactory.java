package com.qa.portal.admin.keycloak;

import com.qa.portal.common.dto.QaUserDto;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class KeycloakUserFactory {

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
        user.setEnabled(true);
        return user;
    }

    private String generatePassword() {
        return "password";
    }
}
