package com.qa.portal.common.security;

import org.keycloak.KeycloakSecurityContext;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.keycloak.representations.AccessToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import java.util.Set;

@Component
public class QaSecurityContext {

    private final Logger LOGGER = LoggerFactory.getLogger(QaSecurityContext.class);

    public String getUserName() {
        return getAccessToken().getPreferredUsername();
    }

    public String getName() {
        return getAccessToken().getName();
    }

    public Set<String> getRoles() {
        return getAccessToken().getRealmAccess().getRoles();
    }

    public String getFirstName() {
        return getAccessToken().getGivenName();
    }

    public String getSurname() {
        return getAccessToken().getFamilyName();
    }

    private AccessToken getAccessToken() {
        KeycloakAuthenticationToken token = (KeycloakAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        KeycloakSecurityContext keycloakContext = token.getAccount().getKeycloakSecurityContext();
        return keycloakContext.getToken();
    }
}
