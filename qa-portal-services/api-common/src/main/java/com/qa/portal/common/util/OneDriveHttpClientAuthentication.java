package com.qa.portal.common.util;

import com.qa.portal.common.exception.QaPortalBusinessException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Component
public class OneDriveHttpClientAuthentication implements AuthenticationManager {

    private final Logger LOGGER = LoggerFactory.getLogger(OneDriveHttpClientAuthentication.class);

    public static final String ONEDRIVE_AUTH_URL = "onedrive.authUrl";

    public static final String CONTENT_TYPE_HTTP_HEADER = "Content-Type";

    public static final String APPLICATION_X_WWW_FORM_URLENCODED = "application/x-www-form-urlencoded";

    public static final String ACCESS_TOKEN_JSON_RESPONSE_PROPERTY = "access_token";

    public static final String CLIENT_ID_URL_QUERY_PARAM = "client_id=";

    public static final String SCOPE_URL_QUERY_PARAM = "&scope=";

    public static final String GRANT_TYPE_URL_QUERY_PARAM = "&grant_type=";

    public static final String CLIENT_SECRET_URL_QUERY_PARAM = "&client_secret=";

    public static final String ONEDRIVE_CLIENT_ID_ENV_PROPERTY = "onedrive.clientId";

    public static final String ONEDRIVE_SCOPE_URL_ENV_PROPERTY = "onedrive.scopeUrl";

    public static final String ONEDRIVE_CLIENT_SECRET_ENV_PROPERTY = "onedrive.clientSecret";

    public static final String CLIENT_CREDENTIALS_PARM_VALUE = "client_credentials";

    public static final String UTF_8_STRING = "utf-8";

    private Environment environment;

    private JsonPropertyUtil jsonPropertyUtil;

    private CloseableHttpClient client;

    public OneDriveHttpClientAuthentication(Environment environment,
                                            JsonPropertyUtil jsonPropertyUtil) {
        this.environment = environment;
        this.jsonPropertyUtil = jsonPropertyUtil;
    }

    @PostConstruct
    public void init() {
        HttpClientConnectionManager poolingConnManager = new PoolingHttpClientConnectionManager();
        client = HttpClients.custom().setConnectionManager(poolingConnManager).build();
    }

    @Override
    public String authenticate() {
        try {
            HttpResponse response = client.execute(getPostRequest());
            return getAuthResponse(response.getEntity().getContent());
        } catch (Exception e) {
            throw new QaPortalBusinessException("Failed to connect to one drive");
        }
    }

    private HttpPost getPostRequest() throws Exception {
        HttpPost postRequest = getAuthenticationPost();
        setRequestHeaders(postRequest);
        setRequestBody(postRequest);
        return postRequest;
    }

    private HttpPost getAuthenticationPost() throws Exception {
        URIBuilder uriBuilder = new URIBuilder(environment.getProperty(ONEDRIVE_AUTH_URL));
        return new HttpPost(uriBuilder.build());
    }

    private void setRequestBody(HttpPost postRequest) throws Exception {
        byte[] postData = getRequestContent().getBytes(StandardCharsets.UTF_8);
        HttpEntity body = new ByteArrayEntity(postData);
        postRequest.setEntity(body);
    }

    private void setRequestHeaders(HttpPost postRequest) {
        postRequest.setHeader(CONTENT_TYPE_HTTP_HEADER, APPLICATION_X_WWW_FORM_URLENCODED);
    }

    private String getRequestContent() throws Exception {
        return CLIENT_ID_URL_QUERY_PARAM + environment.getProperty(ONEDRIVE_CLIENT_ID_ENV_PROPERTY) +
                SCOPE_URL_QUERY_PARAM + environment.getProperty(ONEDRIVE_SCOPE_URL_ENV_PROPERTY) +
                GRANT_TYPE_URL_QUERY_PARAM + CLIENT_CREDENTIALS_PARM_VALUE +
                CLIENT_SECRET_URL_QUERY_PARAM  + URLEncoder.encode(environment.getProperty(ONEDRIVE_CLIENT_SECRET_ENV_PROPERTY), UTF_8_STRING);
    }

    private String getAuthResponse(InputStream is) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(is, UTF_8_STRING));
        String response = br.readLine();
        LOGGER.info("Pooled Auth response " + response);
        return jsonPropertyUtil.getJsonContentForProperty(ACCESS_TOKEN_JSON_RESPONSE_PROPERTY, response);
    }
}
