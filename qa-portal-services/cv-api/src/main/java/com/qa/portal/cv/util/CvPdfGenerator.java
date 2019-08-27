package com.qa.portal.cv.util;

import com.qa.portal.cv.domain.CvVersion;

public interface CvPdfGenerator {
	public byte[] generateCv(CvVersion cvVersion);
}
