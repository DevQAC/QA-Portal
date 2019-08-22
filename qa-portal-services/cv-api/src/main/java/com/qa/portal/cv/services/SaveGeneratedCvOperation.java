package com.qa.portal.cv.services;

import java.io.InputStream;

import org.springframework.beans.factory.annotation.Value;

import com.qa.portal.common.util.QaFileManager;
import com.qa.portal.cv.domain.CvVersion;
import com.qa.portal.cv.util.CvPdfGenerator;

public class SaveGeneratedCvOperation {

	private CvPdfGenerator pdfGenerator;
	private QaFileManager fileManager;
	@Value("${onedrive.filelocation}") String fileLocation;
	
	public SaveGeneratedCvOperation(CvPdfGenerator pdfGenerator, QaFileManager fileManager) {
		super();
		this.pdfGenerator = pdfGenerator;
		this.fileManager = fileManager;
	}

	public void saveGeneratedCv(CvVersion cvVersion) {
		InputStream is = pdfGenerator.generateCv(cvVersion);
		//add folder name & CV filename to fileLocation parameter.
		fileManager.storeFile(fileLocation, is);
	}
}
