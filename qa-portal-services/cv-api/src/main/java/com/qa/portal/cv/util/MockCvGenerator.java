package com.qa.portal.cv.util;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import com.qa.portal.common.exception.QaPortalSevereException;
import com.qa.portal.cv.domain.CvVersion;

@Component
public class MockCvGenerator implements CvPdfGenerator{

	@Override
	public InputStream generateCv(CvVersion cvVersion) {
		Resource res = new ClassPathResource("Dan.pdf");
		try {
			return res.getInputStream();
		} catch (IOException e) {
			e.printStackTrace();
			throw new QaPortalSevereException("Cant load file");
		}
	}

}


