package com.qa.portal.common.security;

import java.util.Set;
import java.util.stream.Collectors;

import org.keycloak.KeycloakSecurityContext;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.keycloak.representations.AccessToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class QaSecurityContext {

    private final Logger LOGGER = LoggerFactory.getLogger(QaSecurityContext.class);

    private static final String COHORT_ROLE_PREFIX = "cohort_";

    public String getUserName() {
        return getAccessToken().getPreferredUsername();
    }

    public String getName() {
        return getAccessToken().getName();
    }

    public Set<String> getRoles() {
        return getAccessToken().getRealmAccess().getRoles().stream()
                .filter(r -> !r.startsWith(COHORT_ROLE_PREFIX))
                .collect(Collectors.toSet());
    }

    public Set<String> getCohorts() {
        return getAccessToken().getRealmAccess().getRoles().stream()
                .filter(r -> r.startsWith(COHORT_ROLE_PREFIX))
                .map(r -> r.substring(COHORT_ROLE_PREFIX.length()).replace('_', ' '))
                .collect(Collectors.toSet());
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
