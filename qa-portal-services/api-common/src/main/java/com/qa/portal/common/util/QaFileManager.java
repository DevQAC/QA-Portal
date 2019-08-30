package com.qa.portal.common.util;

public interface QaFileManager {

	void storeFile(String folderName, String fileName, String fileVersion, byte[] cvByteArray);
}
