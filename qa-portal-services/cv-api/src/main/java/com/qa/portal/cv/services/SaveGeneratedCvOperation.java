package com.qa.portal.cv.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.qa.portal.common.util.QaFileManager;
import com.qa.portal.cv.domain.CvVersion;
import com.qa.portal.cv.util.CvPdfGenerator;

@Component
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
		byte[] cvByteArray = pdfGenerator.generateCv(cvVersion);
		
		fileManager.storeFile(cvVersion, cvByteArray);
	}
}
