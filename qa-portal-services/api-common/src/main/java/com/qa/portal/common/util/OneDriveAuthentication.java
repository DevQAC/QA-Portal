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

@Component
public class OneDriveAuthentication implements AuthenticationManager {

	private final Logger LOGGER = LoggerFactory.getLogger(OneDriveAuthentication.class);

    private JsonPropertyUtil jsonPropertyUtil;

    private Environment environment;

    public OneDriveAuthentication(JsonPropertyUtil jsonPropertyUtil,
                                  Environment environment) {
        this.jsonPropertyUtil = jsonPropertyUtil;
        this.environment = environment;
    }

    @Override
    public String getAuthentication() {
        try {
            String urlParameters = "client_id=" + environment.getProperty("onedrive.clientId")
                    + "&scope=https%3A%2F%2Fgraph.microsoft.com%2F.default"
                    + "&grant_type=client_credentials"
                    + "&client_secret=" + URLEncoder.encode(environment.getProperty("onedrive.clientSecret"), "utf-8");
            byte[] postData = urlParameters.getBytes(StandardCharsets.UTF_8);

            URL url = new URL("https://login.microsoftonline.com/common/oauth2/v2.0/token");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setInstanceFollowRedirects(false);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setUseCaches(false);
            DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
            wr.write(postData);

            try {
            	LOGGER.info("In try block of auth");
                BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
                String response = br.readLine();
				LOGGER.info("Auth response " + response);
                return jsonPropertyUtil.getJsonContentForProperty("access_token", response);
            } catch (Exception e) {
                ByteArrayOutputStream bOut = new ByteArrayOutputStream();
                InputStream is = connection.getErrorStream();
                int charByte;
                while ((charByte = is.read()) != -1) {
                    bOut.write((byte) charByte);
                }
                LOGGER.error("Error connecting to one drive " + e.getMessage(), e);
                throw new QaPortalBusinessException("Failed to connect to onedrive");
            }
        } catch (Exception e) {
			LOGGER.error("Error connecting to one drive " + e.getMessage(), e);
            throw new QaPortalBusinessException("Failed to connect to onedrive");
        }
    }
}
