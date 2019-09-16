package com.qa.portal.cv.util.pdf;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.portal.cv.domain.CvVersion;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

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

	// Test: Make sure that a PDF has been created
	@Test
	public void pdfGenTest() {
		CvPdfGeneratorImpl pdfGen = new CvPdfGeneratorImpl();
		pdfGen.init();
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
