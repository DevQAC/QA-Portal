package com.qa.portal.common.util;

import com.qa.portal.cv.domain.CvVersion;

public interface QaFileManager {
	
	public void storeFile(CvVersion cvVersion, byte[] cvByteArray);
	
	void createFolder(String locationId, String folderName);
	
	boolean checkItemExists(String pathToItem);

	int getNextCvVersion(String archiveId);
	
	void moveItem(String newName, String destinationFolder, String itemId);
}
