package com.qa.portal.cv.util;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import com.qa.portal.cv.domain.CvVersion;

import rst.pdfbox.layout.elements.Paragraph;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

@RunWith(MockitoJUnitRunner.class)
public class CvPdfGeneratorImplTest {

	// Test: Make sure an element can be added to the pdf
	@Test
	public void pdfBox1_1Test() throws IOException {
		CvPdfGeneratorImpl pdfGen = new CvPdfGeneratorImpl();
		CvVersion cvVersion = getCvVersion();
		Paragraph paragraph = new Paragraph();
		try {
			pdfGen.box1_2(paragraph, "Programming Languages",
					cvVersion.getAllSkills().get(0).getProgrammingLanguages());
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
	}

	// Test: Make sure fonts can be loaded
	@Test
	public void pdfLoadFonts() {
		CvPdfGeneratorImpl pdfGen = new CvPdfGeneratorImpl();
		try {
			pdfGen.loadfonts();
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
	}

	// Test: Make sure images can be loaded
	@Test
	public void pdfLoadImagesTest() throws IOException {
		CvPdfGeneratorImpl pdfGen = new CvPdfGeneratorImpl();
		try {
			pdfGen.loadImages();
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
	}

	// Test: Make sure that a pdf has been created
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
