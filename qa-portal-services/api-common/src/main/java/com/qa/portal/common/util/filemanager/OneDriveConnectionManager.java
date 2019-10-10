package com.qa.portal.common.util.filemanager;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

import java.util.Objects;

import static com.qa.portal.common.util.filemanager.OneDriveConstants.*;

@Component
public class OneDriveConnectionManager {

    private Environment environment;

    private CloseableHttpClient httpClient;

    public OneDriveConnectionManager(Environment environment) {
        this.environment = environment;
    }

    @PostConstruct
    public void init() {
        if (ONE_DRIVE_ACTIVE_TRUE_VALUE.equals(environment.getProperty(ONE_DRIVE_ACTIVE_FLAG))) {
            PoolingHttpClientConnectionManager poolingConnManager = new PoolingHttpClientConnectionManager();
            poolingConnManager.setMaxTotal(Integer.parseInt(Objects.requireNonNull(environment.getProperty(MAX_CONNECTIONS_TOTAL))));
            poolingConnManager.setDefaultMaxPerRoute(Integer.parseInt(environment.getProperty(MAX_CONNECTIONS_PER_ROUTE)));
            httpClient = HttpClients.custom().setConnectionManager(poolingConnManager).build();
        }
    }

    public CloseableHttpClient getHttpClient() {
        return httpClient;
    }
}
