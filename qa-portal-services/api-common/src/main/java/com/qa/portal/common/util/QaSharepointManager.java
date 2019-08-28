package com.qa.portal.common.util;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

public class QaSharepointManager implements QaFileManager {

	private RestTemplate restTemplate;
	
	@Override
	public void storeFile(String filePath, byte[] data) {
		if(data != null) postCV(filePath, data);
	}
	
	private void postCV(String fileName, byte[] data) {
		try { 
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			
//			Object objToPost = data;
//			ResponseEntity<File> response = restTemplate.postForEntity(createURLWithPort("/sharepoint/path/to/file"), objToPost, File.class);
			restTemplate.postForEntity(createURL("/path/to/file"), data, Object.class); //Object.class should be the object you expect to be returned
	  	} catch (Exception e) {
	  		e.printStackTrace();
	  	} 
	}

	//{0} = serverUrl, {1} = relativeUrlToFolder, {2} = fileName
	//API call path: "{0}/_api/web/getfolderbyserverrelativeurl('{1}')/files" + "/add(overwrite=true, url='{2}')"
	private String createURL(String uri) { 
		return "URL/for/sharepoint:<PORT?>" + uri; 
	}
}

