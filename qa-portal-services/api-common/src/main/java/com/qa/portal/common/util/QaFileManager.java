package com.qa.portal.common.util;

import com.qa.portal.cv.domain.CvVersion;

public interface QaFileManager {
	
	public void storeFile(CvVersion cvVersion, byte[] cvByteArray);
	
	String createFolder(String locationId, String folderName);
	
	String getItemId(String pathToItem);

	int getNextCvVersion(String archiveId);
	
	void moveItem(String newName, String destinationFolderId, String itemId);
	
	void uploadFile(String fileName, String destinationFolderId, byte[] fileData);
}
