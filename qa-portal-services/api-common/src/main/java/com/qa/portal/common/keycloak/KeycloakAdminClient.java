package com.qa.portal.common.keycloak;

import com.qa.portal.common.security.QaSecurityContext;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.RealmResource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Optional;

@Component
public class KeycloakAdminClient {

    public static final String QA_KEYCLOAK_ADMIN_ENABLED = "qa.keycloak.admin.enabled";

    public static final String QA_KEYCLOAK_ADMIN_URL_PROP = "qa.keycloak.admin.url";

    public static final String QA_KEYCLOAK_ADMIN_REALM_PROP = "qa.keycloak.admin.realm";

    public static final String QA_KEYCLOAK_ADMIN_USER_PROP = "qa.keycloak.admin.user";

    public static final String QA_KEYCLOAK_AUTH_PASSWORD_PROP = "qa.keycloak.admin.password";

    public static final String QA_KEYCLOAK_AUTH_CLIENT_ID_PROP = "qa.keycloak.admin.clientId";

    private Keycloak keycloak;

    private Environment environment;

    private QaSecurityContext qaSecurityContext;

    public KeycloakAdminClient(Environment environment,
                               QaSecurityContext qaSecurityContext) {
        this.environment = environment;
        this.qaSecurityContext = qaSecurityContext;
    }

    @PostConstruct
    public void init() {
        Optional.ofNullable(environment.getProperty(QA_KEYCLOAK_ADMIN_ENABLED))
                .ifPresent(e -> initializeKeycloakAdminClient(e));
    }

    public Keycloak getKeycloakAdminClient() {
        return keycloak;
    }

    public RealmResource getRealm() {
        return getKeycloakAdminClient().realm(environment.getProperty(QA_KEYCLOAK_ADMIN_REALM_PROP));
    }

    private void initializeKeycloakAdminClient(String enabledFlag) {
        if (("true").equals(enabledFlag)) {
            keycloak = KeycloakBuilder.builder()
                    .serverUrl(environment.getProperty(QA_KEYCLOAK_ADMIN_URL_PROP))
                    .realm(environment.getProperty(QA_KEYCLOAK_ADMIN_REALM_PROP))
                    .username(environment.getProperty(QA_KEYCLOAK_ADMIN_USER_PROP))
                    .password(environment.getProperty(QA_KEYCLOAK_AUTH_PASSWORD_PROP))
                    .clientId(environment.getProperty(QA_KEYCLOAK_AUTH_CLIENT_ID_PROP))
                    .resteasyClient(new ResteasyClientBuilder().build())
                    .build();
        }
    }
}
