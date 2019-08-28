package com.qa.portal.common.util;

import com.qa.portal.common.exception.QaPortalBusinessException;
import com.qa.portal.cv.domain.CvVersion;

public class QaOneDriveManager implements QaFileManager {

	@Override
	public void storeFile(CvVersion cvVersion, byte[] cvByteArray) {
		
	}

	@Override
	public void createFolder(String locationId, String folderName) {
		
	}

	@Override
	public boolean checkItemExists(String pathToItem) {
		return false;
	}

	@Override
	public int getNextCvVersion(String archiveId) {
		return 0;
	}

	@Override
	public void moveItem(String newName, String destinationFolder, String itemId) {
		throw new QaPortalBusinessException("Move Failed");
	}

}
