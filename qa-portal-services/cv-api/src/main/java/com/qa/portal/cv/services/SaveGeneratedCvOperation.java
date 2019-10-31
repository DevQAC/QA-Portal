package com.qa.portal.cv.services;

import com.qa.portal.common.exception.QaPortalBusinessException;
import org.springframework.stereotype.Component;

import com.qa.portal.common.util.filemanager.QaFileManager;
import com.qa.portal.cv.domain.CvVersion;
import com.qa.portal.cv.util.pdf.CvPdfGenerator;

@Component
public class SaveGeneratedCvOperation {

	private CvPdfGenerator pdfGenerator;

	private QaFileManager fileManager;
	
	public SaveGeneratedCvOperation(CvPdfGenerator pdfGenerator, QaFileManager fileManager) {
		super();
		this.pdfGenerator = pdfGenerator;
		this.fileManager = fileManager;
	}

	public void saveGeneratedCv(CvVersion cvVersion) {
		try {
			fileManager.storeFile(cvVersion.getUserName(),
					cvVersion.getUserName(),
					cvVersion.getVersionNumber().toString(),
					pdfGenerator.generateCv(cvVersion));
		}
		catch (Exception e) {
			throw new QaPortalBusinessException("Error generating PDF for CV");
		}
	}
}
