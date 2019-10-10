package com.qa.portal.common.util.filemanager;

import com.qa.portal.common.exception.QaPortalBusinessException;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpRequestBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLEncoder;

import static com.qa.portal.common.util.filemanager.HttpMethodRequestFactory.HttpRequestTypeEnum.AUTH;
import static com.qa.portal.common.util.filemanager.OneDriveConstants.*;

// TODO - Need to handle refresh of the auth token - currently created when component is created then always used.
@Component
public class OneDriveAuthenticationManager implements AuthenticationManager {

    private final Logger LOGGER = LoggerFactory.getLogger(OneDriveAuthenticationManager.class);

    private Environment environment;

    private JsonPropertyUtil jsonPropertyUtil;

    private HttpMethodRequestFactory httpMethodRequestFactory;

    private OneDriveConnectionManager oneDriveConnectionManager;

    public OneDriveAuthenticationManager(OneDriveConnectionManager oneDriveConnectionManager,
                                         HttpMethodRequestFactory httpMethodRequestFactory,
                                         Environment environment,
                                         JsonPropertyUtil jsonPropertyUtil) {
        this.oneDriveConnectionManager = oneDriveConnectionManager;
        this.httpMethodRequestFactory = httpMethodRequestFactory;
        this.environment = environment;
        this.jsonPropertyUtil = jsonPropertyUtil;
    }

    @Override
    public String authenticate() {
        try {
            HttpResponse response = oneDriveConnectionManager.getHttpClient().execute(getAuthenticationHttpPostRequest());
            return getAuthTokenFromResponse(response.getEntity().getContent());
        } catch (Exception e) {
            throw new QaPortalBusinessException("Failed to connect to one drive");
        }
    }

    private HttpRequestBase getAuthenticationHttpPostRequest() throws Exception {
        return httpMethodRequestFactory.getHttpMethod(environment.getProperty(ONEDRIVE_AUTH_URL), null,  getRequestContent(), AUTH);
    }

    private String getRequestContent() throws Exception {
        return CLIENT_ID_URL_QUERY_PARAM + environment.getProperty(ONEDRIVE_CLIENT_ID_ENV_PROPERTY) +
                SCOPE_URL_QUERY_PARAM + environment.getProperty(ONEDRIVE_SCOPE_URL_ENV_PROPERTY) +
                GRANT_TYPE_URL_QUERY_PARAM + CLIENT_CREDENTIALS_PARM_VALUE +
                CLIENT_SECRET_URL_QUERY_PARAM  + URLEncoder.encode(environment.getProperty(ONEDRIVE_CLIENT_SECRET_ENV_PROPERTY), UTF_8_STRING);
    }

    private String getAuthTokenFromResponse(InputStream is) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(is, UTF_8_STRING));
        String response = br.readLine();
        LOGGER.info("Pooled Auth response " + response);
        return jsonPropertyUtil.getJsonContentForProperty(ACCESS_TOKEN_JSON_RESPONSE_PROPERTY, response);
    }
}
