package com.qa.portal.cv.services;

import org.springframework.beans.factory.annotation.Value;
<<<<<<< HEAD
import org.springframework.stereotype.Service;
=======
import org.springframework.stereotype.Component;
>>>>>>> 427365dd593a610261a27a1b75271a37f8d8baad

import com.qa.portal.common.util.QaFileManager;
import com.qa.portal.cv.domain.CvVersion;
import com.qa.portal.cv.util.CvPdfGenerator;

<<<<<<< HEAD
@Service
=======
@Component
>>>>>>> 427365dd593a610261a27a1b75271a37f8d8baad
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
		//add folder name & CV filename to fileLocation parameter.
<<<<<<< HEAD
		fileManager.storeFile(fileLocation + "/test/test.pdf", is);
=======
		//fileManager.storeFile(fileLocation, cvByteArray);
>>>>>>> 427365dd593a610261a27a1b75271a37f8d8baad
	}
}
