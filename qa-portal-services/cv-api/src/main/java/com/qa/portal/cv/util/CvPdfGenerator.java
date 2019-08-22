package com.qa.portal.cv.util;

import java.io.InputStream;

import com.qa.portal.cv.domain.CvVersion;

public interface CvPdfGenerator {
	public InputStream generateCv(CvVersion cvVersion);
}
