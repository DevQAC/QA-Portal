package com.qa.portal.common.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.stereotype.Component;

@Component
public class QaOneDriveManger implements QaFileManager {

	@Override
	public void storeFile(String filePath, byte[] cvByteArray) {
		try {
			//move existing CV to archive
			archiveFile(filePath);

			//create folder(s) to user folder if they are a new user
			File directory = new File(filePath).getParentFile();
			directory.mkdirs();
			
			//save new CV
			FileOutputStream fos = new FileOutputStream(filePath);
			fos.write(cvByteArray);
			fos.flush();
			fos.close();
			
			//is.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//errors if files in archive aren't like "<ANYTHING>version<VERSION-NUMBER>.<EXTENSION>"
	private void archiveFile(String filePath) {
		File file = new File(filePath);
		if(file.exists()) {
			//make archive folder if it doesn't already exist
			File userDir = new File(file.getParent() + "/archive");
			userDir.mkdirs(); 
			
			int newVersion = getCurrentVersion(userDir) + 1;
			
			//move old CV to archive
			String fileName = file.getName().substring(0, file.getName().indexOf('.'));
			file.renameTo(new File(userDir.getAbsolutePath() + "/" + fileName + "-version" + (newVersion) + ".pdf"));
		}
	}

	private int getCurrentVersion(File userDir) {
		int version = 0;
		if(userDir.isDirectory()) {
			for(String fileName : userDir.list()) {
				//get number after "version" in file name
				int fileVersion = Integer.parseInt(fileName.substring(fileName.indexOf("version") + 7, fileName.indexOf('.')));
				if(fileVersion > version) {
					version = fileVersion;
				}
			}
		}
		return version;
	}
}
