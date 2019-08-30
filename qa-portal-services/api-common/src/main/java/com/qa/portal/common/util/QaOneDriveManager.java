package com.qa.portal.common.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import com.qa.portal.cv.domain.CvVersion;

@Component
public class QaOneDriveManager implements QaFileManager {
	
	private String authToken;
	private String cvFolderId;

	public QaOneDriveManager(String authToken) {
		this.authToken = authToken;
		this.cvFolderId = getItemId("CVs");
		if(cvFolderId == null)
			this.cvFolderId = createFolder("root", "CVs");
	}
	
	public void storeFile(CvVersion cvVersion, byte[] cvByteArray) {
		String username = cvVersion.getUserName();
		String userFolderId = getItemId("CVs/" + username);
		String currentCvId = null;
		String archiveId = null;

		if (userFolderId == null) {
			userFolderId = createFolder(cvFolderId, username);
		} else {
			currentCvId = getItemId("CVs/" + username + "/" + username + ".pdf");
			if (currentCvId != null) {
				archiveId = getItemId("CVs/" + username + "/archive");
				if (archiveId == null) {
					archiveId = createFolder(userFolderId, "archive");
				}
				moveItem(username + "-version" + getNextCvVersion(archiveId) + ".pdf", archiveId, currentCvId);
			}
		}
		uploadFile(username + ".pdf", userFolderId, cvByteArray);
	}

	public String createFolder(String locationId, String folderName) {
		HttpURLConnection connection = null;
		try {
			URL url = new URL("https://graph.microsoft.com/v1.0/me/drive/items/" + locationId + "/children");
			connection = createConnection(url, "POST");
			
			String jsonBody = "{\"name\": \"" + folderName + "\",\"folder\": { } }";
			byte[] jsonBodyAsArray = jsonBody.getBytes("utf-8");
			postData(connection, jsonBodyAsArray);
			
			String response = getResponse(connection);
			
			JSONObject jsonObject = new JSONObject(response);
			return jsonObject.getString("id");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String getItemId(String pathToItem) {
		try {
			URL url = new URL("https://graph.microsoft.com/v1.0/me/drive/root:/" + pathToItem);
			HttpURLConnection connection = createConnection(url, "GET");

			String response = getResponse(connection);
			if(connection.getResponseCode() != 200) return null;

			JSONObject jsonObject = new JSONObject(response);
			return jsonObject.getString("id");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public int getNextCvVersion(String archiveId) {
		try {
			URL url = new URL("https://graph.microsoft.com/v1.0/me/drive/items/" + archiveId + "/children");
			HttpURLConnection connection = createConnection(url, "GET");

			String response = getResponse(connection);
			
			JSONObject jsonObject = new JSONObject(response);
			JSONArray files = jsonObject.getJSONArray("value");
			
			int maxVersion = 0;
			for(int i = 0; i < files.length(); i++) {
				JSONObject file = files.getJSONObject(i);
				String filename = file.getString("name");
				int currentVersion = Integer.parseInt(filename.substring(filename.lastIndexOf("version") + 7, filename.indexOf('.')));
				if(currentVersion > maxVersion) maxVersion = currentVersion;
			}
			return maxVersion + 1;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public void moveItem(String newName, String destinationFolderId, String itemId) {
		byte[] fileData = downloadFile(itemId);
		deleteFile(itemId);
		uploadFile(newName, destinationFolderId, fileData);
	}

	public void uploadFile(String fileName, String destinationFolderId, byte[] fileData) {
		try {
			//send request
			URL url = new URL("https://graph.microsoft.com/v1.0/me/drive/items/" + destinationFolderId + ":/" + fileName + ":/content");
			HttpURLConnection connection = createConnection(url, "PUT");

			postData(connection, fileData);
			
			String response = getResponse(connection);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void deleteFile(String itemId) {
		try {
			URL url = new URL("https://graph.microsoft.com/v1.0/me/drive/items/" + itemId);
			HttpURLConnection connection = createConnection(url, "DELETE");
			
			String response = getResponse(connection);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private byte[] downloadFile(String itemId) {
		try {
			URL url = new URL("https://graph.microsoft.com/v1.0/me/drive/items/" + itemId + "/content");
			HttpURLConnection connection = createConnection(url, "GET");
			
			String response = getResponse(connection);
			int responseCode = connection.getResponseCode();
			if(responseCode == 200) {
				return response.getBytes();
			} else {
				return null;
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private String getResponse(HttpURLConnection connection) throws IOException {
		int responseCode = connection.getResponseCode();
		InputStream is = null;
		if(responseCode < 200 || responseCode >= 300) {
			is = connection.getErrorStream();
		} else {
//			if(responseCode == 401) {
//				refreshToken();
//				HttpURLConnection newConnection = createConnection(connection.getURL(), connection.getRequestMethod());
//				return getResponse(newConnection);
//			}
			is = connection.getInputStream();
		}
	
		ByteArrayOutputStream buffer = new ByteArrayOutputStream();
		int c = -1;
		while((c = is.read()) != -1) {
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
		
		if(requestMethod.equals("POST") || requestMethod.equals("PUT")) 
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
}
