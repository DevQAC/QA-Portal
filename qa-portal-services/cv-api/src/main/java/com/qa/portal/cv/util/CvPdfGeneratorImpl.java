package com.qa.portal.cv.util;

import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDateTime;

import javax.annotation.PostConstruct;

import com.qa.portal.common.exception.QaPortalBusinessException;
import com.qa.portal.common.exception.QaPortalSevereException;
import com.qa.portal.cv.domain.CvVersion;

import org.apache.pdfbox.io.IOUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDTrueTypeFont;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import rst.pdfbox.layout.elements.Document;
import rst.pdfbox.layout.elements.Frame;
import rst.pdfbox.layout.elements.ImageElement;
import rst.pdfbox.layout.elements.Paragraph;
import rst.pdfbox.layout.shape.Stroke;
import rst.pdfbox.layout.text.Alignment;
import rst.pdfbox.layout.text.Position;

@Component
public class CvPdfGeneratorImpl implements CvPdfGenerator {

	String firstname = "firstname";
	String lastname = "lastname";
	String jobTitle = "jobTitle";
	String profile = "profile";
	String[] programmingLanguages = { "a", "b", "c" };
	String[] IDEs = { "a", "b", "c" };
	String[] operatingSystems = { "a", "b", "c" };
	String[] devOpsTech = { "a", "b", "c" };
	String[] databaseTech = { "a", "b", "c" };
	String[] projectFrameWorks = { "a", "b", "c" };
	String[] other = { "a", "b", "c" };
	String[] qualifications = { "a", "b", "c" };
	String[] job = { "a", "b", "c" };
	String[] detail = { "a", "b", "c" };
	String hobbies = "hobbies";

	Document document = new Document();
	PDDocument pdDoc = new PDDocument();

	String QABlue = "#004050";
	String QAPurple = "#7F007D";
	String QARed = "#FF004C";
	String QAGrey = "#565759";

	int pd = 20;

	float heightSideBox1 = document.getPageHeight() / 6 + 4;
	float heightSideBox2 = (float) 2.5 * document.getPageHeight() / 6 + 7;
	float heightSideBox3 = document.getPageHeight() - heightSideBox1 - heightSideBox2;
	float widthCol1 = document.getPageWidth() / 3 + 4.5f;
	float widthCol2 = document.getPageWidth() - widthCol1;
	float heightHeader = document.getPageHeight() / 20 - 8;
	float heightFooter = document.getPageHeight() / 30;
	float heightBody = document.getPageHeight() - heightHeader - heightFooter;

	Frame frame;
	Paragraph paragraph;

	PDFont montserrat;
	PDFont montserratBold;
	PDFont kranaFatB;

	@SuppressWarnings("deprecation")
	@PostConstruct
	public void loadfonts() {
		try {
			this.montserrat = PDTrueTypeFont.loadTTF(document.getPDDocument(),
					new File("src/main/resources/Montserrat-Regular.ttf"));
			this.montserratBold = PDTrueTypeFont.loadTTF(document.getPDDocument(),
					new File("src/main/resources/Montserrat-SemiBold.ttf"));
			this.kranaFatB = PDTrueTypeFont.loadTTF(document.getPDDocument(),
					new File("src/main/resources/Krana-Fat-B.ttf"));
		} catch (IOException e) {
			e.printStackTrace();
			throw new QaPortalBusinessException("Cannot load in CvPdfGeneratorImpl fonts");
		}
	}

	@Override
	public byte[] generateCv(CvVersion cvVersion) {

		// Resource res = new
		// ClassPathResource("generatedCv-versiondate-"+LocalDateTime.now()+".pdf");

		Resource res = new ClassPathResource("generatedCv.pdf");
		try {
			// column 1 box 1
			paragraph = new Paragraph();
			paragraph.addMarkup("{color:#FFFFFF}*" + cvVersion.getFirstName() + "\n" + cvVersion.getSurname() + "*", 20, kranaFatB, kranaFatB,
					kranaFatB, kranaFatB);
			paragraph.addMarkup("{color:" + QAPurple + "} \n*" + cvVersion.getCohort() + "*", 20, kranaFatB, kranaFatB, kranaFatB,
					kranaFatB);
			frame = new Frame(paragraph, widthCol1, heightSideBox1);
			frame.setBackgroundColor(Color.decode(QARed));
			frame.setAbsolutePosition(new Position(0, document.getPageHeight()));
			frame.setPadding(pd, pd, pd, pd);
			document.add(frame);

			// column 1 box 1 image
			ImageElement image = new ImageElement("src/main/resources/Arrow.png");
			image.setWidth(image.getWidth() / 35);
			image.setHeight(image.getHeight() / 35);
			image.setAbsolutePosition(new Position(pd, document.getPageHeight() - heightSideBox1 + pd + 20));
			document.add(image);

			// column 1 box 2
			paragraph = new Paragraph();
			box1_2(paragraph, "Programming Languages", operatingSystems);
			box1_2(paragraph, "IDEs", IDEs);
			box1_2(paragraph, "Operating Systems", operatingSystems);
			box1_2(paragraph, "DevOps Technologies", devOpsTech);
			box1_2(paragraph, "Database Technologies", databaseTech);
			box1_2(paragraph, "Project Frameworks", projectFrameWorks);
			box1_2(paragraph, "Other", other);
			frame = new Frame(paragraph, widthCol1, heightSideBox2);
			frame.setBackgroundColor(Color.decode(QAPurple));
			frame.setAbsolutePosition(new Position(0, document.getPageHeight() - heightSideBox1));
			paragraph.setMaxWidth(frame.getWidth() - (pd * 2));
			frame.setPadding(pd, pd, pd, pd);
			document.add(frame);

			// column 1 box 3
			paragraph = new Paragraph();
			paragraph.addMarkup("{color:#FFFFFF}*Qualification*", 20, montserrat, montserratBold, montserrat,
					montserrat);
			for (String i : qualifications) {
				paragraph.addMarkup("\n\n{color:#FFFFFF}" + i, 10, montserrat, montserratBold, montserrat, montserrat);
			}
			frame = new Frame(paragraph, widthCol1, heightSideBox3);
			frame.setBackgroundColor(Color.decode(QABlue));
			frame.setAbsolutePosition(new Position(0, heightSideBox3));
			paragraph.setMaxWidth(frame.getWidth() - (pd * 2));
			frame.setPadding(pd, pd, pd, pd);
			document.add(frame);

			// column 2 Header
			paragraph = new Paragraph();
			paragraph.addMarkup("{color:#89898b}Consultant Profile", 8.8f, montserrat, montserratBold, montserrat,
					montserrat);
			paragraph.setAlignment(Alignment.Left);
			frame = new Frame(paragraph, widthCol2 - pd * 2, heightHeader);
			frame.setAbsolutePosition(new Position(widthCol1, heightFooter + heightBody + paragraph.getHeight() + 10));
			paragraph.setMaxWidth(frame.getWidth());
			frame.setMargin(pd, 0, 0, 0);
			document.add(frame);

			// column 2 header image
			ImageElement logo = new ImageElement("src/main/resources/QA_Logo.png");
			logo.setWidth(logo.getWidth() / 37f);
			logo.setHeight(logo.getHeight() / 37f);
			logo.setAbsolutePosition(new Position(widthCol1 + widthCol2 - logo.getWidth() - pd,
					heightFooter + heightBody + logo.getHeight() + 4));
			document.add(logo);

			// column 2 Body
			paragraph = new Paragraph();
			paragraph.addMarkup("{color:" + QAPurple + "}*PROFILE*\n", 12, kranaFatB, kranaFatB, kranaFatB, kranaFatB);
			paragraph.addMarkup("\n", 5, kranaFatB, kranaFatB, kranaFatB, kranaFatB);
			// Profile
			paragraph.addMarkup("{color:" + QAGrey + "}" + cvVersion.getProfile() + "\n\n\n", 9, montserrat, montserratBold,
					montserrat, montserrat);
			// Work Experience
			paragraph.addMarkup("{color:" + QAPurple + "}*WORK EXPERIANCE - QA*\n", 12, kranaFatB, kranaFatB, kranaFatB,
					kranaFatB);
			paragraph.addMarkup("\n", 5, kranaFatB, kranaFatB, kranaFatB, kranaFatB);
			for (int i = 0; i < job.length; i++) {
				paragraph.addMarkup("{color:" + QABlue + "}*" + job[i] + "*\n", 9, montserrat, montserratBold,
						montserrat, montserrat);
				paragraph.addMarkup("\n", 4, montserrat, montserratBold, montserrat, montserrat);
				paragraph.addMarkup("{color:" + QAGrey + "}" + detail[i] + "\n\n", 9, montserrat, montserratBold,
						montserrat, montserrat);
			}
			// Hobbies and Interests
			paragraph.addMarkup("{color:" + QAPurple + "}*HOBBIES/INTERESTS*\n", 12, kranaFatB, kranaFatB, kranaFatB,
					kranaFatB);
			paragraph.addMarkup("{color:" + QAGrey + "}" + cvVersion.getHobbies() + "\n\n", 9, montserrat, montserratBold, montserrat,
					montserrat);
			frame = new Frame(paragraph, widthCol2, heightBody);
			frame.setAbsolutePosition(new Position(widthCol1, document.getPageHeight() - heightHeader));
			paragraph.setMaxWidth(frame.getWidth() - (pd * 2));
			frame.setPadding(pd, pd, pd, pd);
			document.add(frame);

			// column 2 Footer
			paragraph = new Paragraph();
			paragraph.addMarkup("{color:#89898b}+44 1273 022670 / qa.com", 8, montserrat, montserratBold, montserrat,
					montserrat);
			paragraph.setAlignment(Alignment.Right);
			frame = new Frame(paragraph, widthCol2 - pd * 2, heightHeader);
			frame.setAbsolutePosition(new Position(widthCol1, heightHeader));
			paragraph.setMaxWidth(frame.getWidth());
			frame.setMargin(pd, 0, 0, 0);
			frame.setPaddingTop(5);
			document.add(frame);

			// column 2 divider 1
			paragraph = new Paragraph();
			frame = new Frame(paragraph, widthCol2 - pd * 2, 0.5f);
			divider(frame, document.getPageHeight() - heightHeader);
			document.add(frame);

			// column 2 divider 2
			paragraph = new Paragraph();
			frame = new Frame(paragraph, widthCol2 - pd * 2, 0.5f);
			divider(frame, heightHeader);
			document.add(frame);

			// returns
			
			
				final OutputStream outputStream = new FileOutputStream("target/classes/generatedCv.pdf");
			
			try {
				document.save(outputStream);
			} catch (Exception e) {
				// TODO: handle exception
				throw new QaPortalSevereException("Document not saved please see: CvPdfGenerationImpl,java");
			}
			return IOUtils.toByteArray(res.getInputStream());

		} catch (IOException e) {

			e.printStackTrace();
			throw new QaPortalSevereException("Cannot generate pdf");

		}
	}

	public void divider(Frame frame, float height) {
		frame.setBorder(Color.decode("#d9dbdb"), new Stroke(0.5f));
		frame.setAbsolutePosition(new Position(widthCol1, height));
		frame.setMargin(pd, 0, 0, 0);
		frame.setPaddingTop(5);
	}

	public void box1_2(Paragraph paragraph, String title, String[] list) throws IOException {
		paragraph.addMarkup("{color:#FFFFFF}*" + title + "*\n", 10, montserrat, montserratBold, montserrat, montserrat);
		paragraph.addMarkup("\n", 4, montserrat, montserratBold, montserrat, montserrat);
		for (int i = 0; i < list.length; i++) {
			if (i < list.length - 1) {
				paragraph.addMarkup("{color:#FFFFFF}" + list[i] + ", ", 10, montserrat, montserratBold, montserrat,
						montserrat);
			} else {
				paragraph.addMarkup("{color:#FFFFFF}" + list[i] + "\n\n", 10, montserrat, montserratBold, montserrat,
						montserrat);
			}
		}
	}
}