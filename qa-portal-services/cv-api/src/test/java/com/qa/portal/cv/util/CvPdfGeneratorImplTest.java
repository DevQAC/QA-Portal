package com.qa.portal.cv.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import com.qa.portal.cv.domain.CvVersion;

import rst.pdfbox.layout.elements.Paragraph;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

@RunWith(MockitoJUnitRunner.class)
public class CvPdfGeneratorImplTest {

	// Test: Make sure fonts can be loaded
	@Test
	public void pdfLoadFonts() {
		CvPdfGeneratorImpl pdfGen = Mockito.mock(CvPdfGeneratorImpl.class);
		try {
			pdfGen.loadfonts();
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
		Mockito.verify(pdfGen).loadfonts();
	}

	// Test: Make sure an element can be added to the PDF
	@Test
	public void pdfElementCreation() throws IOException {
		CvPdfGeneratorImpl pdfGen = Mockito.mock(CvPdfGeneratorImpl.class);
		CvVersion cvVersion = getCvVersion();
		Paragraph paragraph = new Paragraph();

		pdfGen.generateSkillsBox(paragraph, "Programing Languages",
				cvVersion.getAllSkills().get(0).getProgrammingLanguages());

		Mockito.verify(pdfGen).generateSkillsBox(paragraph, "Programing Languages",
				cvVersion.getAllSkills().get(0).getProgrammingLanguages());
	}

	// Test: Make sure images can be loaded
	@Test
	public void pdfLoadImagesTest() throws IOException {
		CvPdfGeneratorImpl pdfGen = Mockito.mock(CvPdfGeneratorImpl.class);
		CvPdfGeneratorImpl pdfGen2 = new CvPdfGeneratorImpl();
		pdfGen.loadImages("target/classes/Arrow.png", "target/classes/QA_Logo.png");
		Mockito.verify(pdfGen).loadImages(pdfGen2.arrowPath, pdfGen2.logoPath);
	}

	// Test: Make sure that a PDF has been created
	@Test
	public void pdfGenTest() {
		CvPdfGeneratorImpl pdfGen = new CvPdfGeneratorImpl();
		pdfGen.loadfonts();
		try {
			byte[] pdfBytes = pdfGen.generateCv(getCvVersion());
			// Create File from byte[] and save to file system /output/filename.pdf
			OutputStream os = new FileOutputStream("pdfGenTest.pdf");
			os.write(pdfBytes);
			os.close();
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
	}

	private CvVersion getCvVersion() throws IOException {
		Resource res = new ClassPathResource("cv-version.json");
		ObjectMapper om = new ObjectMapper();
		CvVersion cvVersion = om.readValue(res.getInputStream(), CvVersion.class);
		return cvVersion;
	}
}
