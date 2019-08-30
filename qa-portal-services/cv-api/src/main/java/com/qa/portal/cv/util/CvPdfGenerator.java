package com.qa.portal.cv.util;

import java.io.IOException;

import com.qa.portal.cv.domain.CvVersion;

public interface CvPdfGenerator {
	byte[] generateCv(CvVersion cvVersion) throws IOException;
}
