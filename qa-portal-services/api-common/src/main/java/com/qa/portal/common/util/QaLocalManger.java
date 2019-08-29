package com.qa.portal.common.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.stereotype.Component;

import com.qa.portal.cv.domain.CvVersion;

public class QaLocalManger implements QaFileManager {

	public void storeFile(String filePath, byte[] cvByteArray) {
		FileOutputStream fos = null;
		try {
			//move existing CV to archive
			archiveFile(filePath);

			//create folder(s) to user folder if they are a new user
			File directory = new File(filePath).getParentFile();
			if(!directory.exists()) directory.mkdirs();
			
			//save new CV
			fos = new FileOutputStream(filePath);
			fos.write(cvByteArray);
			fos.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	//errors if files in archive aren't like "<ANYTHING>version<VERSION-NUMBER>.<EXTENSION>"
	private void archiveFile(String filePath) {
		File file = new File(filePath);
		if(file.exists()) {
			//make archive folder if it doesn't already exist
			File userDir = new File(file.getParent() + "/archive");
			if(!userDir.exists()) userDir.mkdirs(); 
			
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

	@Override
	public void storeFile(CvVersion cvVersion, byte[] cvByteArray) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String createFolder(String locationId, String folderName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getItemId(String pathToItem) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getNextCvVersion(String archiveId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void moveItem(String newName, String destinationFolder, String itemId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void uploadFile(String fileName, String destinationFolder, byte[] fileData) {
		// TODO Auto-generated method stub
		
	}
}
