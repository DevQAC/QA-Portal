package com.qa.portal.common.config;

import org.keycloak.adapters.KeycloakConfigResolver;
import org.keycloak.adapters.KeycloakDeployment;
import org.keycloak.adapters.KeycloakDeploymentBuilder;
import org.keycloak.adapters.OIDCHttpFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import static com.qa.portal.common.CommonConstants.KEYCLOAK_CONFIG_FILE_PROP;

@Component
public class QaServicesKeycloakConfigResolver implements KeycloakConfigResolver {

    private Environment env;

    @Autowired
    public QaServicesKeycloakConfigResolver(Environment env) {
        this.env = env;
    }

    @Override
    public KeycloakDeployment resolve(OIDCHttpFacade.Request request) {
        return KeycloakDeploymentBuilder.build(getClass().getClassLoader().getResourceAsStream(env.getProperty(KEYCLOAK_CONFIG_FILE_PROP)));
    }
}
