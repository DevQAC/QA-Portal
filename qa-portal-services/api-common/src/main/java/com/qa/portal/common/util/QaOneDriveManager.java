package com.qa.portal.common.util;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import com.qa.portal.cv.domain.CvVersion;

//@Component
public class QaOneDriveManager implements QaFileManager {

	private String authToken;
	//base folder (where user folders are created)
	private String cvFolderId;

	public QaOneDriveManager(String authToken) {
		this.authToken = authToken;
		System.out.println("GET FOLDER: CVs");
		cvFolderId = getItemId("CVs");
	}
	
	public void storeFile(CvVersion cvVersion, byte[] cvByteArray) {
		String username = cvVersion.getUserName();
		System.out.println("GET FOLDER: " + username);
		String userFolderId = getItemId("CVs/" + username);
		String currentCvId = null;
		String archiveId = null;

		if (userFolderId == null) {
			System.out.println("CREATE FOLDER: " + username);
			userFolderId = createFolder(cvFolderId, username);
		} else {
			System.out.println("GET FILE: " + username + "/" + username + ".pdf");
			currentCvId = getItemId("CVs/" + username + "/" + username + ".pdf");
			if (currentCvId != null) {
				System.out.println("GET FOLDER: archive");
				archiveId = getItemId("CVs/" + username + "/archive");
				if (archiveId == null) {
					System.out.println("CREATE FOLDER: archive");
					archiveId = createFolder(userFolderId, "archive");
				}
				System.out.println("MOVE FILE: " + username + ".pdf to " + "archive/" +  username + "-version" + getNextCvVersion(archiveId) + ".pdf");
				moveItem(username + "-version" + getNextCvVersion(archiveId) + ".pdf", archiveId, currentCvId);
			}
		}
		System.out.println("UPLOAD FILE:" + username + ".pdf to /" + username);
		uploadFile(username + ".pdf", userFolderId, cvByteArray);
	}

	public String createFolder(String locationId, String folderName) {
		HttpURLConnection connection = null;
		URL url;
		try {
			url = new URL("https://graph.microsoft.com/v1.0/me/drive/items/" + locationId + "/children");
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setRequestProperty("Accept", "application/json");
			connection.setRequestProperty("Authorization", "Bearer " + authToken);
			connection.setDoOutput(true);
			
			String jsonBody = "{\"name\": \"" + folderName + "\",\"folder\": { } }";

			byte[] jsonBodyAsArray = jsonBody.getBytes("utf-8");
			
			postData(connection, jsonBodyAsArray);
			
			String response = getResponse(connection);
			System.out.println("Code: " + connection.getResponseCode());
			System.out.println(response);
			
			JSONObject jsonObject = new JSONObject(response);
			return jsonObject.getString("id");
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			ByteArrayOutputStream bOut = new ByteArrayOutputStream();
			InputStream is = connection.getErrorStream();
			int charByte;
			try {
				while ((charByte = is.read()) != -1) {
					bOut.write((byte) charByte);
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			System.out.println(bOut.toString());
		}
		return null;
	}

	public String getItemId(String pathToItem) {
		HttpURLConnection connection = null;
		try {
			// send request
			URL url = new URL("https://graph.microsoft.com/v1.0/me/drive/root:/" + pathToItem);
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setRequestProperty("Accept", "application/json");
			connection.setRequestProperty("Authorization", "Bearer " + authToken);

			connection.connect();

			String response = getResponse(connection);
			if(connection.getResponseCode() != 200) return null;
			System.out.println("Code: " + connection.getResponseCode());
			System.out.println(response);

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
			// send request
			URL url = new URL("https://graph.microsoft.com/v1.0/me/drive/items/" + archiveId + "/children");
			HttpURLConnection connection = createConnection(url, "GET");
//			connection = (HttpURLConnection) url.openConnection();
//			connection.setRequestMethod("GET");
//			connection.setRequestProperty("Content-Type", "application/json");
//			connection.setRequestProperty("Accept", "application/json");
//			connection.setRequestProperty("Authorization", "Bearer " + authToken);

			connection.connect();

			String response = getResponse(connection);
			
			System.out.println("Code: " + connection.getResponseCode());
			System.out.println(response);
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
			connection.connect();

			postData(connection, fileData);
			
			
			String response = getResponse(connection);
			System.out.println("Code: " + connection.getResponseCode());
			System.out.println(response);
			
			connection.disconnect();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void deleteFile(String itemId) {
		try {
			//send request
			URL url = new URL("https://graph.microsoft.com/v1.0/me/drive/items/" + itemId);
			HttpURLConnection connection = createConnection(url, "DELETE");
			connection.connect();
			
			//get response
			String response = getResponse(connection);
			System.out.println("Code: " + connection.getResponseCode());
			System.out.println(response);
			connection.disconnect();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private byte[] downloadFile(String itemId) {
		try {
			//send request
			URL url = new URL("https://graph.microsoft.com/v1.0/me/drive/items/" + itemId + "/content");
			HttpURLConnection connection = createConnection(url, "GET");
			connection.connect();
			
			//get response
			int responseCode = connection.getResponseCode();
			InputStream is = null;
			ByteArrayOutputStream fileData = null;
			if(responseCode >= 400) {
				is = connection.getErrorStream();
				ByteArrayOutputStream buffer = new ByteArrayOutputStream();
				int c = -1;
				while((c = is.read()) != -1) {
					buffer.write((byte) c);
				}
				String response = buffer.toString();
				System.out.println("Code: " + responseCode);
				System.out.println(response);
				buffer.close();
			} else {
				is = connection.getInputStream();
				fileData = new ByteArrayOutputStream();
				int c = -1;
				while((c = is.read()) != -1) 
					fileData.write((byte) c);
			}
		
			is.close();
			connection.disconnect();
			
			return fileData.toByteArray();
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
		if(responseCode >= 400) {
			is = connection.getErrorStream();
		} else {
			is = connection.getInputStream();
		}
	
		ByteArrayOutputStream buffer = new ByteArrayOutputStream();
		int c = -1;
		while((c = is.read()) != -1) {
			buffer.write((byte) c);
		}
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
			
		
		return connection;
	}
	
	private void postData(HttpURLConnection connection, byte[] data) throws IOException {
		OutputStream os = connection.getOutputStream();
		os.write(data);
		os.flush();
		os.close();
	}
}
