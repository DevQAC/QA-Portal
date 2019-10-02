package com.qa.portal.common.config;

import org.keycloak.adapters.KeycloakConfigResolver;
import org.keycloak.adapters.springsecurity.KeycloakConfiguration;
import org.keycloak.adapters.springsecurity.config.KeycloakWebSecurityConfigurerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.core.authority.mapping.SimpleAuthorityMapper;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;

@KeycloakConfiguration
@Profile("!test")
public class SecurityConfig extends KeycloakWebSecurityConfigurerAdapter {

    private final Logger LOGGER = LoggerFactory.getLogger(SecurityConfig.class);

    public static final String SWAGGER_DOCS = "/v2/api-docs";

    public static final String SWAGGER_UI = "/swagger-resources/configuration/ui";

    public static final String SWAGGER_RESOURCES = "/swagger-resources";

    public static final String SWAGGER_RESOURCES_SECURITY = "/swagger-resources/configuration/security";

    public static final String SWAGGER_UI_PAGE = "/swagger-ui.html";

    public static final String MANAGEMENT_APIS = "/manage/users";

    public static final String PORTAL_ADMIN_ROLE = "super-user";

    public static final String SERVER_SERVLET_CONTEXT_PATH = "server.servlet.context-path";

    private Environment environment;

    private KeycloakConfigResolver configResolver;

    @Autowired
    public SecurityConfig(KeycloakConfigResolver configResolver,
                          Environment environment) {
        this.configResolver = configResolver;
        this.environment = environment;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(keycloakAuthenticationProvider());
    }

    @Bean
    protected GrantedAuthoritiesMapper grantedAuthoritiesMapper() {
        return new SimpleAuthorityMapper();
    }

    @Bean
    @Override
    protected SessionAuthenticationStrategy sessionAuthenticationStrategy() {
        return new RegisterSessionAuthenticationStrategy(new SessionRegistryImpl());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
        http.authorizeRequests()
                .antMatchers(SWAGGER_DOCS,
                        SWAGGER_UI,
                        SWAGGER_RESOURCES,
                        SWAGGER_RESOURCES_SECURITY,
                        SWAGGER_UI_PAGE).permitAll()
                .antMatchers(getManagementUris()).hasAuthority(PORTAL_ADMIN_ROLE)
                .anyRequest().authenticated()
                .and()
                .csrf().disable();
    }

    private String getManagementUris() {
        String uri = environment.getProperty(SERVER_SERVLET_CONTEXT_PATH) + MANAGEMENT_APIS;
        LOGGER.info("Management url is " + uri);
        return uri;
    }
}
