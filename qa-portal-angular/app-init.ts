import { KeycloakService, KeycloakOptions } from 'keycloak-angular';
import { environment } from "./projects/portal-core/src/environments/environment";

// TODO - values to be sourced from environment-{env}.ts files
export function initializer(keycloak: KeycloakService): () => Promise<any> {

  const options: KeycloakOptions = {
    config: {
      url: environment.keycloak_url,
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
