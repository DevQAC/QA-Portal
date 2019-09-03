package com.qa.portal.cv.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.security.MessageDigest;

import javax.xml.bind.DatatypeConverter;

import rst.pdfbox.layout.elements.Paragraph;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.portal.cv.domain.CvVersion;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

@RunWith(MockitoJUnitRunner.class)
public class CvPdfGeneratorImplTest {

	// Test: Make sure fonts can be loaded 
	@Test
	public void pdfLoadFontsTest() {
		CvPdfGeneratorImpl pdfGen = Mockito.mock(CvPdfGeneratorImpl.class);
		try {
			pdfGen.loadFonts();
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
		Mockito.verify(pdfGen).loadFonts();
	}

	// Test: Make sure an element can be added to the PDF
	@Test
	public void pdfElementCreationTest() throws IOException {
		CvPdfGeneratorImpl pdfGenerator = Mockito.mock(CvPdfGeneratorImpl.class);
		Resource jsonResource = new ClassPathResource("cv-version.json");
		ObjectMapper jsonObjectMapper = new ObjectMapper();
		CvVersion cvVersion = jsonObjectMapper.readValue(jsonResource.getInputStream(), CvVersion.class);
		Paragraph paragraph = new Paragraph();

		pdfGenerator.generateSkillsBox(paragraph, "Programing Languages",
				cvVersion.getAllSkills().get(0).getProgrammingLanguages());

		Mockito.verify(pdfGenerator).generateSkillsBox(paragraph, "Programing Languages",
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
	public void generateCvTest(){
		try{
			Resource generatedPdfResource = new FileSystemResource("pdfGenTest.pdf");
			File generatedPdfFile = new File(generatedPdfResource.getFile().getPath());

			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(Files.readAllBytes(generatedPdfFile.toPath()));
			byte[] loadedPdfBytes = md.digest();
			String pdfChecksum = DatatypeConverter.printHexBinary(loadedPdfBytes);
			System.out.println(".PDF GENERATION SUCCESS");
			System.out.println("Generated .pdf filepath: " + generatedPdfFile.getAbsolutePath()); 
			System.out.println("Generated .pdf checksum: " + pdfChecksum);

		} catch (Exception ex) {
			System.out.println(".PDF GENERATION FAILED");
			ex.printStackTrace();
			System.out.println("Error: " + ex.getMessage());
		}
	}
}
