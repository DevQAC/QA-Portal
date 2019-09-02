package com.qa.portal.cv.util;

import java.io.File;
import java.nio.file.Files;
import java.security.MessageDigest;

import javax.xml.bind.DatatypeConverter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

@RunWith(MockitoJUnitRunner.class)
public class CvPdfGeneratorImplTest {

	@Test
	public void generateCvTest(){
		CvPdfGeneratorImpl pdfGenerator = new CvPdfGeneratorImpl();
		pdfGenerator.loadFonts();
		try{
			Resource generatedPdfResource = new FileSystemResource("test.pdf");
			File generatedPdfFile = new File(generatedPdfResource.getFile().getPath());

			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(Files.readAllBytes(generatedPdfFile.toPath()));
			byte[] loadedPdfBytes = md.digest();
			String pdfChecksum = DatatypeConverter.printHexBinary(loadedPdfBytes);
			System.out.println(pdfChecksum);

		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("Error: " + ex.getMessage());
		}
	}
}