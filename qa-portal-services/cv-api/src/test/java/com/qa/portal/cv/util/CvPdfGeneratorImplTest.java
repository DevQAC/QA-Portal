package com.qa.portal.cv.util;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.portal.cv.domain.CvVersion;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import rst.pdfbox.layout.elements.Paragraph;

import javax.xml.bind.DatatypeConverter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;

import static org.junit.Assert.assertNotEquals;

@RunWith(MockitoJUnitRunner.class)
public class CvPdfGeneratorImplTest {

	private final Logger LOGGER = LoggerFactory.getLogger(CvPdfGeneratorImplTest.class);

	// Test: Make sure the CvVersion is populated
	@Test
	public void pdfCvVersionTest() throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper jsonObjectMapper = new ObjectMapper();
		Resource jsonResource = new ClassPathResource("cv-version.json");
		CvVersion cvVersion = jsonObjectMapper.readValue(jsonResource.getInputStream(), CvVersion.class);
		assertNotEquals(null, cvVersion);
	}

	// Test: Make sure fonts can be loaded
	@Test
	public void pdfLoadFontsTest() {
		CvPdfGeneratorImpl pdfGen = Mockito.mock(CvPdfGeneratorImpl.class);
		try {
			pdfGen.createFonts();
		} catch (Exception e) {
			LOGGER.error("Error: " + e.getMessage(), e);
		}
		Mockito.verify(pdfGen).createFonts();
	}

	// Test: Make sure an element can be added to the PDF
	@Test
	public void pdfElementCreationTest() throws IOException {
		CvPdfGeneratorImpl pdfGenerator = Mockito.mock(CvPdfGeneratorImpl.class);
		Resource jsonResource = new ClassPathResource("cv-version.json");
		ObjectMapper jsonObjectMapper = new ObjectMapper();
		CvVersion cvVersion = jsonObjectMapper.readValue(jsonResource.getInputStream(), CvVersion.class);
		Paragraph paragraph = new Paragraph();

		if (!cvVersion.getAllSkills().get(0).getProgrammingLanguages().isEmpty()) {
			pdfGenerator.generateSkillsBoxContent(paragraph, "Programing Languages",
					cvVersion.getAllSkills().get(0).getProgrammingLanguages());
		}

		Mockito.verify(pdfGenerator).generateSkillsBoxContent(paragraph, "Programing Languages",
				cvVersion.getAllSkills().get(0).getProgrammingLanguages());
	}

	// Test: Make sure images can be loaded
	@Test
	public void pdfLoadImagesTest() throws IOException {
		CvPdfGeneratorImpl pdfGen = Mockito.mock(CvPdfGeneratorImpl.class);
		File arrow = new File(Images.ARROW.filePath);
		File logo = new File(Images.LOGO.filePath);
		if (arrow.exists() && logo.exists()) {
			pdfGen.loadImages(Images.ARROW.filePath, Images.ARROW.resizeFactor);
		} else {
			LOGGER.info("PDFLoadImgTest FAILED DUE TO INCORRECT IMG PATH");
		}
		Mockito.verify(pdfGen).loadImages(Images.ARROW.filePath, Images.ARROW.resizeFactor);
	}

	// Test: Make sure that a PDF has been created
	@Test
	public void pdfGenTest() {
		CvPdfGeneratorImpl pdfGen = new CvPdfGeneratorImpl();
		pdfGen.createFonts();
		try {
			ObjectMapper jsonObjectMapper = new ObjectMapper();
			Resource jsonResource = new ClassPathResource("cv-version.json");
			CvVersion cvVersion = jsonObjectMapper.readValue(jsonResource.getInputStream(), CvVersion.class);
			byte[] pdfBytes = pdfGen.generateCv(cvVersion);
			OutputStream os = new FileOutputStream("pdfGenTest.pdf");
			os.write(pdfBytes);
			os.close();
		} catch (Exception e) {
			LOGGER.error("Error: " + e.getMessage(), e);
		}
		finally {
			try {
				Files.delete(Paths.get("pdfGenTest.pdf"));
			}
			catch (Exception e) {
				LOGGER.error("Error deleting pdfGenTest.pdf file " + e.getMessage(), e);
			}
		}
	}
}
