package com.qa.portal.common.util;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import org.json.JSONObject;

public class OneDriveAuthentication implements AuthenticationManager {

	@Override
	public String getAuthentication(String clientId, String clientSecret) {
		try {
			String urlParameters = "client_id=" + clientId 
					+ "&scope=https%3A%2F%2Fgraph.microsoft.com%2F.default"
					+ "&grant_type=client_credentials" 
					+ "&client_secret=" + URLEncoder.encode(clientSecret, "utf-8");
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
				BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
				String response = br.readLine();
				JSONObject jsonObject = new JSONObject(response);
				return jsonObject.getString("access_token");
			} catch (Exception e) {
				ByteArrayOutputStream bOut = new ByteArrayOutputStream();
				InputStream is = connection.getErrorStream();
				int charByte;
				while ((charByte = is.read()) != -1) {
					bOut.write((byte) charByte);
				}
				System.out.println(bOut.toString());
				e.printStackTrace();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
}
