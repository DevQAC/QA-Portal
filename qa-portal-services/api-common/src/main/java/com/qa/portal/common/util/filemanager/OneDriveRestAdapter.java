package com.qa.portal.common.util.filemanager;

import com.qa.portal.common.exception.QaPortalBusinessException;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.CloseableHttpClient;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Optional;

import static com.qa.portal.common.util.filemanager.OneDriveConstants.*;

@Component
public class OneDriveRestAdapter {

    private String oneDriveUrl;

    private String authToken;

    private JsonPropertyUtil jsonPropertyUtil;

    private AuthenticationManager authenticationManager;

    private OneDriveConnectionManager oneDriveConnectionManager;

    private HttpMethodRequestFactory httpMethodRequestFactory;

    private Environment environment;

    public OneDriveRestAdapter(JsonPropertyUtil jsonPropertyUtil,
                               AuthenticationManager authenticationManager,
                               OneDriveConnectionManager oneDriveConnectionManager,
                               HttpMethodRequestFactory httpMethodRequestFactory,
                               Environment environment) {
        this.jsonPropertyUtil = jsonPropertyUtil;
        this.authenticationManager = authenticationManager;
        this.oneDriveConnectionManager = oneDriveConnectionManager;
        this.httpMethodRequestFactory = httpMethodRequestFactory;
        this.environment = environment;
    }


    // TODO - Auth Token OK on one node, what about multi node deployment of Cv service? Do we keep the token in a DB?
    @PostConstruct
    public void init() {
        Optional<String> oneDriveActiveFlag = Optional.ofNullable(environment.getProperty(ONE_DRIVE_ACTIVE_FLAG));
        oneDriveActiveFlag.ifPresent(f -> getOneDriveAuthToken(f));
    }

    public void deleteFile(String folderName, String fileName) {
        Optional<String> currentCvId = getItemId(folderName + "/" + fileName);
        currentCvId.ifPresent(id -> deleteFile(id));
    }

    public void saveFile(String folderPath, String fileName, byte[] fileContents) {
        uploadFile(fileName, getFolderId(folderPath), fileContents);
    }

    public void saveFile(String parentFolder, String folderPath, String fileName, byte[] fileContents) {
        uploadFile(fileName, getArchiveFolderId(parentFolder, folderPath), fileContents);
    }

    private void getOneDriveAuthToken(String oneDriveActive) {
        if (oneDriveActive.equals(ONE_DRIVE_ACTIVE_TRUE_VALUE)) {
            oneDriveUrl = environment.getProperty(ONE_DRIVE_RESOURCE_BASE_URL);
            authToken = authenticationManager.authenticate();
        }
    }

    private String getFolderId(String folderPath) {
        Optional<String> folderId = getItemId(folderPath);
        return folderId.orElseGet(
                () -> createFolder(environment.getProperty(ONE_DRIVE_BASE_FOLDER), folderPath)
        );
    }

    private String getArchiveFolderId(String parentFolderName, String folderName) {
        String userFolderId = getFolderId(parentFolderName);
        Optional<String> archiveFolderId = getItemId(parentFolderName + "/" + folderName);
        return archiveFolderId.orElseGet(() -> createFolder(userFolderId, folderName));
    }

    private String createFolder(String locationId, String folderName) {
        try {
            String url = oneDriveUrl + "items/" + locationId + "/children";
            String jsonBody = "{\"name\": \"" + folderName + "\",\"folder\": { } }";
            HttpRequestBase httpPost = httpMethodRequestFactory.getHttpMethod(url, authToken, jsonBody, HttpMethodRequestFactory.HttpRequestTypeEnum.POST);
            String response = executeRequest(httpPost);
            return jsonPropertyUtil.getJsonContentForProperty("id", response);
        } catch (Exception e) {
            throw new QaPortalBusinessException("Failed to create folder for user on one drive");
        }
    }

    private Optional<String> getItemId(String pathToItem) {
        try {
            String url = oneDriveUrl + "root:/" + pathToItem;
            HttpRequestBase httpGet = httpMethodRequestFactory.getHttpMethod(url, authToken, HttpMethodRequestFactory.HttpRequestTypeEnum.GET);
            String response = executeRequest(httpGet);
            return Optional.ofNullable(jsonPropertyUtil.getJsonContentForProperty("id", response));
        } catch (Exception e) {
            throw new QaPortalBusinessException("Error retrieving item from one drive");
        }
    }

    private void uploadFile(String fileName, String destinationFolderId, byte[] fileData) {
        try {
            String url = oneDriveUrl + "items/" + destinationFolderId + ":/" + fileName + ":/content";
            HttpRequestBase httpPut = httpMethodRequestFactory.getHttpMethod(url, authToken, fileData, HttpMethodRequestFactory.HttpRequestTypeEnum.PUT);
            executeRequest(httpPut);
        } catch (Exception e) {
            throw new QaPortalBusinessException("Error uploading file to one drive");
        }
    }

    private void deleteFile(String itemId) {
        try {
            String url = oneDriveUrl + "/items/" + itemId;
            HttpRequestBase httpDelete = httpMethodRequestFactory.getHttpMethod(url, authToken, HttpMethodRequestFactory.HttpRequestTypeEnum.DELETE);
            executeRequest(httpDelete);
        } catch (Exception e) {
            throw new QaPortalBusinessException("Error deleting file from one drive");
        }
    }

    private String executeRequest(HttpRequestBase httpRequestBase) throws Exception {
        CloseableHttpClient httpClient = oneDriveConnectionManager.getHttpClient();
        HttpResponse httpResponse = httpClient.execute(httpRequestBase);
        return processResponse(httpResponse);
    }

    private String processResponse(HttpResponse httpResponse) throws Exception {
        if (httpResponse.getStatusLine().getStatusCode() != 200) {
            throw new QaPortalBusinessException("Error accessing one drive");
        }
        InputStream is = httpResponse.getEntity().getContent();
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        int c = -1;
        while ((c = is.read()) != -1) {
            buffer.write((byte) c);
        }
        is.close();
        buffer.close();
        return buffer.toString();
    }
}
