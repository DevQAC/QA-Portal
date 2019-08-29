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
		cvFolderId = getItemId("CVs");
	}
	
	@Override
	public void storeFile(CvVersion cvVersion, byte[] cvByteArray) {
		String username = cvVersion.getUserName();
		String userFolderId = getItemId(username);
		String currentCvId = null;
		String archiveId = null;

		if (userFolderId == null) {
			userFolderId = createFolder(cvFolderId, username);
		} else {
			currentCvId = getItemId(username + "/" + username + ".pdf");
			if (currentCvId != null) {
				archiveId = getItemId(username + "/archive");
				if (archiveId == null) {
					archiveId = createFolder(userFolderId, "archive");
				}
				moveItem(username + "-version" + getNextCvVersion(archiveId) + ".pdf", archiveId, currentCvId);
			}
		}
		uploadFile(username + ".pdf", archiveId, cvByteArray);
	}

	@Override
	public String createFolder(String locationId, String folderName) {
		HttpURLConnection connection = null;
		URL url;
		try {
			url = new URL("https://graph.microsoft.com/v1.0/me/drive/items/" + locationId + "/children");
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setRequestProperty("Accept", "application/json");
			connection.setRequestProperty("Authorization", authToken);
			
			connection.setUseCaches(false);
			connection.setDoOutput(true);
			String jsonBody = "{\"name\": \"" + folderName + "\",\"folder\": {}}";
			OutputStream outputStream = connection.getOutputStream();
			
			byte[] jsonBodyAsArray = jsonBody.getBytes("utf-8");
			outputStream.write(jsonBodyAsArray, 0, jsonBodyAsArray.length);
			BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
			String response = br.readLine();
			
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

	@Override
	public String getItemId(String pathToItem) {
		HttpURLConnection connection = null;
		try {
			// send request
			URL url = new URL("https://graph.microsoft.com/v1.0/me/drive/root:/" + pathToItem);
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Accept", "application/json");
			connection.setRequestProperty("Authorization", "Bearer " + authToken);

			connection.connect();

			// get response
			int responseCode = connection.getResponseCode();
			InputStream is = null;
			if (responseCode >= 400) {
				is = connection.getErrorStream();
			} else {
				is = connection.getInputStream();
			}

			ByteArrayOutputStream buffer = new ByteArrayOutputStream();
			int c = -1;
			while ((c = is.read()) != -1) {
				buffer.write((byte) c);
			}
			is.close();

			String response = buffer.toString();
			System.out.println("Code: " + responseCode);
			System.out.println(response);

			buffer.close();
			connection.disconnect();

			JSONObject jsonObject = new JSONObject(response);
			return jsonObject.getString("id");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int getNextCvVersion(String archiveId) {
		HttpURLConnection connection = null;
		try {
			// send request
			URL url = new URL("https://graph.microsoft.com/v1.0/me/drive/items/" + archiveId + "/children");
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Accept", "application/json");
			connection.setRequestProperty("Authorization", "Bearer " + authToken);

			connection.connect();

			// get response
			int responseCode = connection.getResponseCode();
			InputStream is = null;
			if (responseCode >= 400) {
				is = connection.getErrorStream();
			} else {
				is = connection.getInputStream();
			}

			ByteArrayOutputStream buffer = new ByteArrayOutputStream();
			int c = -1;
			while ((c = is.read()) != -1) {
				buffer.write((byte) c);
			}
			is.close();

			String response = buffer.toString();
			System.out.println("Code: " + responseCode);
			System.out.println(response);

			buffer.close();
			connection.disconnect();
			
			JSONObject jsonObject = new JSONObject(response);
			JSONArray files = jsonObject.getJSONArray("value");
			
			int maxVersion = 0;
			for(int i = 0; i < files.length(); i++) {
				JSONObject file = files.getJSONObject(i);
				String filename = file.getString("name");
				int currentVersion = Integer.parseInt(filename.substring(filename.indexOf("version") + 7, filename.indexOf('.')));
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

	@Override
	public void moveItem(String newName, String destinationFolderId, String itemId) {
		byte[] fileData = downloadFile(itemId);
		deleteFile(itemId);
		uploadFile(newName, destinationFolderId, fileData);
	}

	@Override
	public void uploadFile(String fileName, String destinationFolderId, byte[] fileData) {
		HttpURLConnection connection = null;
		try {
			//send request
			URL url = new URL("https://graph.microsoft.com/v1.0/me/drive/items/" + destinationFolderId + ":/" + fileName + ":/content");
			connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setRequestMethod("PUT");
			connection.setRequestProperty("Accept", "application/json");
			connection.setRequestProperty("Authorization", "Bearer " + authToken);

			OutputStream os = connection.getOutputStream();
			os.write(fileData);
			os.flush();
			os.close();
			connection.connect();
			
			//get response
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
			is.close();
			
			String response = buffer.toString();
			System.out.println("Code: " + responseCode);
			System.out.println(response);
			
			buffer.close();
			connection.disconnect();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void deleteFile(String itemId) {
		HttpURLConnection connection = null;
		try {
			//send request
			URL url = new URL("https://graph.microsoft.com/v1.0/me/drive/items/" + itemId);
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("DELETE");
//			connection.setRequestProperty("Content-Type", "application/json");
			connection.setRequestProperty("Accept", "application/json");
			connection.setRequestProperty("Authorization", "Bearer " + authToken);
			
			connection.connect();
			
			//get response
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
			is.close();
			
			String response = buffer.toString();
			System.out.println("Code: " + responseCode);
			System.out.println(response);
			
			buffer.close();
			connection.disconnect();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private byte[] downloadFile(String itemId) {
		HttpURLConnection connection = null;
		try {
			//send request
			URL url = new URL("https://graph.microsoft.com/v1.0/me/drive/items/" + itemId + "/content");
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
//			connection.setRequestProperty("Content-Type", "application/json");
//			connection.setRequestProperty("Accept", "application/json");
			connection.setRequestProperty("Authorization", "Bearer " + authToken);

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
}
