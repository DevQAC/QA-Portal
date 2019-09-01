package com.qa.portal.common.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.portal.common.exception.QaPortalBusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Optional;

@Component
public class QaOneDriveManager implements QaFileManager {

    private final Logger LOGGER = LoggerFactory.getLogger(QaOneDriveManager.class);

    private String clientId;

    private String clientSecret;

    private String baseFolderPath;

    private String oneDriveUrl;

    private String oneDriveActive;

    private ObjectMapper objectMapper;

    private String authToken;

    private AuthenticationManager authenticationManager;

    private JsonPropertyUtil jsonPropertyUtil;

    private Environment environment;

    public QaOneDriveManager(AuthenticationManager authenticationManager,
                             JsonPropertyUtil jsonPropertyUtil,
                             Environment environment) {
        this.authenticationManager = authenticationManager;
        this.jsonPropertyUtil = jsonPropertyUtil;
        this.environment = environment;
    }

    @PostConstruct
    public void init() {
        oneDriveActive = environment.getProperty("onedrive.isactive");
        if (oneDriveActive != null && oneDriveActive.equals("true")) {
            setOneDriveProperties();
            authToken = authenticationManager.getAuthentication(clientId, clientSecret);
            objectMapper = new ObjectMapper();
        }
    }

    public void storeFile(String folderName, String fileName, String fileVersion, byte[] cvByteArray) {
        // Delete current File
        deleteFile(folderName, fileName);

        // Store new File (store it as filename)
        saveFile(getFolderId(folderName), fileName, cvByteArray);

        // Store archive file
        saveFile(getArchiveFolderId(getFolderId(folderName), folderName, "archive"), fileVersion + "-" + fileName, cvByteArray);
    }

    private void deleteFile(String folderName, String fileName) {
        Optional<String> currentCvId = Optional.ofNullable(getItemId(folderName + "/" + fileName));
        currentCvId.ifPresent(id -> deleteFile(id));
    }

    private void saveFile(String folderId, String fileName, byte[] fileContents) {
        uploadFile(fileName, folderId, fileContents);
    }

    private String getArchiveFolderId(String userFolderId, String parentFolderName, String folderName) {
        String archiveFolderId = getItemId(parentFolderName + "/" + folderName);
        if (archiveFolderId == null) {
            archiveFolderId = createFolder(userFolderId, folderName);
        }
        return archiveFolderId;
    }

    public String createFolder(String locationId, String folderName) {
        HttpURLConnection connection = null;
        try {
            URL url = new URL(oneDriveUrl + "items/" + locationId + "/children");
            connection = createConnection(url, "POST");
            String jsonBody = "{\"name\": \"" + folderName + "\",\"folder\": { } }";
            byte[] jsonBodyAsArray = jsonBody.getBytes("utf-8");
            postData(connection, jsonBodyAsArray);
            String response = getResponse(connection);
            return jsonPropertyUtil.getJsonContentForProperty("id", response);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getItemId(String pathToItem) {
        try {
            URL url = new URL(oneDriveUrl + "root:/" + pathToItem);
            HttpURLConnection connection = createConnection(url, "GET");
            String response = getResponse(connection);
            if (connection.getResponseCode() != 200) {
                throw new QaPortalBusinessException("Item not found on onedrive");
            }
            return jsonPropertyUtil.getJsonContentForProperty("id", response);
        } catch (Exception e) {
            throw new QaPortalBusinessException("Item not found on onedrive");
        }
    }

    public void uploadFile(String fileName, String destinationFolderId, byte[] fileData) {
        try {
            //send request
            URL url = new URL(oneDriveUrl + "items/" + destinationFolderId + ":/" + fileName + ":/content");
            HttpURLConnection connection = createConnection(url, "PUT");
            postData(connection, fileData);
            String response = getResponse(connection);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getFolderId(String folderPath) {
        String folderId = getItemId(folderPath);
        if (folderId == null) {
            folderId = createFolder(baseFolderPath, folderPath);
        }
        return folderId;
    }

    private void deleteFile(String itemId) {
        try {
            URL url = new URL(oneDriveUrl + "/items/" + itemId);
            HttpURLConnection connection = createConnection(url, "DELETE");
            String response = getResponse(connection);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getResponse(HttpURLConnection connection) throws IOException {
        int responseCode = connection.getResponseCode();
        InputStream is = null;
        if (responseCode < 200 || responseCode >= 300) {
            is = connection.getErrorStream();
            throw new QaPortalBusinessException("Failed to save file to onedrive");
        } else {
            is = connection.getInputStream();
        }

        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        int c = -1;
        while ((c = is.read()) != -1) {
            buffer.write((byte) c);
        }

        is.close();
        buffer.close();
        connection.disconnect();
        return buffer.toString();
    }

    private HttpURLConnection createConnection(URL url, String requestMethod) throws IOException {
        HttpURLConnection connection;
        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod(requestMethod);
        connection.setRequestProperty("Accept", "application/json");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("Authorization", "Bearer " + authToken);

        if (requestMethod.equals("POST") || requestMethod.equals("PUT"))
            connection.setDoOutput(true);
        connection.connect();
        return connection;
    }

    private void postData(HttpURLConnection connection, byte[] data) throws IOException {
        OutputStream os = connection.getOutputStream();
        os.write(data);
        os.flush();
        os.close();
    }

    private void setOneDriveProperties() {
        clientId = environment.getProperty("onedrive.clientId");
        clientSecret = environment.getProperty("onedrive.clientSecret");
        oneDriveUrl = environment.getProperty("onedrive.url");
        baseFolderPath = environment.getProperty("onedrive.baseFolder");
    }
}
