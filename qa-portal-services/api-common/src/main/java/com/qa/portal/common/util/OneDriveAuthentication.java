package com.qa.portal.common.util;

import com.qa.portal.common.exception.QaPortalBusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

//@Component
public class OneDriveAuthentication {

    private final Logger LOGGER = LoggerFactory.getLogger(OneDriveAuthentication.class);

    public static final String HTTP_POST_METHOD = "POST";

    public static final String ONEDRIVE_AUTH_URL = "onedrive.authUrl";

    public static final String CONTENT_TYPE_HTTP_HEADER = "Content-Type";

    public static final String APPLICATION_X_WWW_FORM_URLENCODED = "application/x-www-form-urlencoded";

    public static final String ACCESS_TOKEN_JSON_RESPONSE_PROPERTY = "access_token";

    public static final String CLIENT_ID_URL_QUERY_PARAM = "client_id";

    public static final String SCOPE_URL_QUERY_PARAM = "&scope=";

    public static final String GRANT_TYPE_URL_QUERY_PARAM = "&grant_type";

    public static final String CLIENT_SECRET_URL_QUERY_PARAM = "&client_secret=";

    public static final String ONEDRIVE_CLIENT_ID_ENV_PROPERTY = "onedrive.clientId";

    public static final String ONEDRIVE_SCOPE_URL_ENV_PROPERTY = "onedrive.scopeUrl";

    public static final String ONEDRIVE_CLIENT_SECRET_ENV_PROPERTY = "onedrive.clientSecret";

    public static final String UTF_8_STRING = "utf-8";

    private JsonPropertyUtil jsonPropertyUtil;

    private Environment environment;

    public OneDriveAuthentication(JsonPropertyUtil jsonPropertyUtil,
                                  Environment environment) {
        this.jsonPropertyUtil = jsonPropertyUtil;
        this.environment = environment;
    }

//    @Override
    public String authenticate() {
        HttpURLConnection connection = null;
        try {
            connection = createConnection();
            sendAuthRequest(connection);
            String authToken = getAuthResponse(connection);
            return authToken;
        } catch (Exception e) {
            LOGGER.error("Error connecting to one drive " + e.getMessage(), e);
            throw new QaPortalBusinessException("Failed to connect to one drive");
        }
    }

    private HttpURLConnection createConnection() throws Exception {
        URL url = new URL(environment.getProperty(ONEDRIVE_AUTH_URL));
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setDoOutput(true);
        connection.setInstanceFollowRedirects(false);
        connection.setRequestMethod(HTTP_POST_METHOD);
        connection.setRequestProperty(CONTENT_TYPE_HTTP_HEADER, APPLICATION_X_WWW_FORM_URLENCODED);
        connection.setUseCaches(false);
        return connection;
    }

    private void sendAuthRequest(HttpURLConnection connection) throws Exception {
        DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
        String queryParameters = getQueryParameters();
        byte[] postData = queryParameters.getBytes(StandardCharsets.UTF_8);
        wr.write(postData);
    }

    private String getAuthResponse(HttpURLConnection connection) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), UTF_8_STRING));
        String response = br.readLine();
        LOGGER.info("Auth response " + response);
        return jsonPropertyUtil.getJsonContentForProperty(ACCESS_TOKEN_JSON_RESPONSE_PROPERTY, response);
    }

    private String getQueryParameters() throws Exception {
        return CLIENT_ID_URL_QUERY_PARAM + "=" + environment.getProperty(ONEDRIVE_CLIENT_ID_ENV_PROPERTY) +
                SCOPE_URL_QUERY_PARAM + environment.getProperty(ONEDRIVE_SCOPE_URL_ENV_PROPERTY) +
                GRANT_TYPE_URL_QUERY_PARAM + "=client_credentials" +
                CLIENT_SECRET_URL_QUERY_PARAM + URLEncoder.encode(environment.getProperty(ONEDRIVE_CLIENT_SECRET_ENV_PROPERTY), UTF_8_STRING);
    }
}
