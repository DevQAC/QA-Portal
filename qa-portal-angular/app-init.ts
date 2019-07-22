import { KeycloakService, KeycloakOptions } from 'keycloak-angular';

// TODO - values to be sourced from environment-{env}.ts files
export function initializer(keycloak: KeycloakService): () => Promise<any> {

  const options: KeycloakOptions = {
    config: {
      url: 'http://localhost:8080/auth',
      realm: 'qa-portal',
      clientId: 'qa-portal-ui'
    },
    initOptions: {
      onLoad: 'login-required',
      checkLoginIframe: false
    },
    enableBearerInterceptor: true,
    bearerExcludedUrls: ['/assets', '/clients/public']
  };

  return (): Promise<any> => keycloak.init(options);
}
