package com.qa.portal.cv.util;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.portal.cv.domain.CvVersion;

@RunWith(MockitoJUnitRunner.class)
public class CvPdfGeneratorImplTest {

	@Test
	public void test() {
		CvPdfGeneratorImpl pdfGen = new CvPdfGeneratorImpl();
		pdfGen.loadfonts();
		try {
		byte[] pdfBytes = pdfGen.generateCv(getCvVersion()); 
		// Create File from byte[] and save to file system /output/filename.pdf
		}
		catch(Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
	}
	
	private CvVersion getCvVersion() throws IOException {
		Resource res =  new ClassPathResource("cv-version.json");
		ObjectMapper om = new ObjectMapper();
		CvVersion cvVersion = om.readValue(res.getInputStream(), CvVersion.class);
		return cvVersion;
	}

}
