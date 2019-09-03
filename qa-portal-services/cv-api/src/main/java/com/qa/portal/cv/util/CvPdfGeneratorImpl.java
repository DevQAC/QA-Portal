package com.qa.portal.cv.util;

import java.awt.Color;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.annotation.PostConstruct;

import com.qa.portal.common.exception.QaPortalBusinessException;
import com.qa.portal.common.exception.QaPortalSevereException;
import com.qa.portal.cv.domain.CvVersion;
import com.qa.portal.cv.domain.Qualification;

import org.apache.fontbox.ttf.TTFParser;
import org.apache.fontbox.ttf.TrueTypeFont;
import org.apache.pdfbox.io.IOUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

<<<<<<< HEAD
    private static final Logger LOGGER = LoggerFactory.getLogger(CvPdfGeneratorImpl.class);

=======
>>>>>>> 5e09bf99bb182402144c60f870db4af232b39afe
	Document document = new Document();
	PDDocument pdDoc = new PDDocument();

	// QA Colour Scheme
	String QABlue = "#004050";
	String QAPurple = "#7F007D";
	String QARed = "#FF004C";
	String QAGrey = "#565759";
<<<<<<< HEAD
	

	int padding = 20;
=======

	int pd = 20;
>>>>>>> 5e09bf99bb182402144c60f870db4af232b39afe
	int sideBarHeaderFontSize = 20;
	int sideBarTitleFontSize = 11;
	int sideBarListsFontSize = 10;
	int titleParagraphSpacing = 4;
	int bodyParagraphFontSize = 9;
	int bodyHeadingsFontSize = 12;

	float heightSideBox1 = document.getPageHeight() / 6 + 4;
	float heightSideBox2 = (float) 2.5 * document.getPageHeight() / 6 + 7;
	float heightSideBox3 = document.getPageHeight() - heightSideBox1 - heightSideBox2;
	float widthCol1 = document.getPageWidth() / 3 + 4.5f;
	float widthCol2 = document.getPageWidth() - widthCol1;
	float heightHeader = document.getPageHeight() / 20 - 8;
	float heightFooter = document.getPageHeight() / 30;
	float heightBody = document.getPageHeight() - heightHeader - heightFooter;

<<<<<<< HEAD
	String arrowPath = "target/classes/Arrow.png";
	String logoPath = "target/classes/QA_Logo.png";

	Frame frame;
	Paragraph paragraph;

	public static TrueTypeFont montserratTtf = null;
	public static TrueTypeFont montserratBoldTtf = null;
    public static TrueTypeFont kranaTtf = null;
    
    public PDFont montserratUtf = null;
    public PDFont montserratBoldUtf = null;
    public PDFont kranaUtf = null;

	@PostConstruct
	public void loadFonts() {
		try {
			Resource montserratResource = new ClassPathResource("Montserrat-Regular.ttf");
			Resource montserratBoldResource = new ClassPathResource("Montserrat-SemiBold.ttf");
            Resource kranaResource = new ClassPathResource("Krana-Fat-B.ttf");

            if (montserratTtf == null){
                InputStream montserratUtfStream = montserratResource.getInputStream();
                montserratTtf = new TTFParser().parse(montserratUtfStream);
            }
            if (montserratBoldTtf == null){
                InputStream montserratBoldUtfStream = montserratBoldResource.getInputStream();
                montserratBoldTtf = new TTFParser().parse(montserratBoldUtfStream);
            }
            if (kranaTtf == null) {
                InputStream kranaUtfStream = kranaResource.getInputStream();
                kranaTtf = new TTFParser().parse(kranaUtfStream);
            }

			montserratBoldUtf = PDType0Font.load(document.getPDDocument(), montserratBoldTtf, true);
            montserratUtf = PDType0Font.load(document.getPDDocument(), montserratTtf, true);
            kranaUtf = PDType0Font.load(document.getPDDocument(), kranaTtf, true);
		} catch (IOException e) {
			throw new QaPortalBusinessException("Cannot load in CvPdfGeneratorImpl fonts");
		}
	}
=======
	Frame frame;
	Paragraph paragraph;

	PDFont montserrat;
	PDFont montserratBold;
	PDFont kranaFatB;

	@PostConstruct
    public void loadfonts() {
         Resource montRegResource = new ClassPathResource("Montserrat-Regular.ttf");
            Resource montBoldResource = new ClassPathResource("Montserrat-SemiBold.ttf");
            Resource kranaResource = new ClassPathResource("Krana-Fat-B.ttf");
    	
    	try {
           

    		if(this.montserrat==null && this.montserratBold == null && this.kranaFatB==null) {
            this.montserrat = PDType0Font.load(document.getPDDocument(),
                    montRegResource.getInputStream());
            this.montserratBold = PDType0Font.load(document.getPDDocument(),
                    montBoldResource.getInputStream());
            this.kranaFatB = PDType0Font.load(document.getPDDocument(),
                    kranaResource.getInputStream());}
        } catch (IOException e) {
            e.printStackTrace();
            throw new QaPortalBusinessException("Cannot load in CvPdfGeneratorImpl fonts");
        }
    }
>>>>>>> 5e09bf99bb182402144c60f870db4af232b39afe

	@Override
	public byte[] generateCv(CvVersion cvVersion) {
		try {
<<<<<<< HEAD
			paragraph = new Paragraph();

			// column 1 box 1
			generateConsultantNameBox(cvVersion);

			// column 1 box 2
			paragraph = new Paragraph();
			generateSkillsBox(paragraph, "Programming Languages", cvVersion.getAllSkills().get(0).getDatabases());
			generateSkillsBox(paragraph, "IDEs", cvVersion.getAllSkills().get(0).getIdes());
			generateSkillsBox(paragraph, "Operating Systems", cvVersion.getAllSkills().get(0).getOperatingSystems());
			generateSkillsBox(paragraph, "DevOps Technologies", cvVersion.getAllSkills().get(0).getDevops());
			generateSkillsBox(paragraph, "Database Technologies", cvVersion.getAllSkills().get(0).getDatabases());
			generateSkillsBox(paragraph, "Project Frameworks", cvVersion.getAllSkills().get(0).getPlatforms());
			generateSkillsBox(paragraph, "Other", cvVersion.getAllSkills().get(0).getOther());
			frame = new Frame(paragraph, widthCol1, heightSideBox2);
			frame.setBackgroundColor(Color.decode(QAPurple));
			frame.setAbsolutePosition(new Position(0, document.getPageHeight() - heightSideBox1));
			paragraph.setMaxWidth(frame.getWidth() - (padding * 2));
			frame.setPadding(padding, padding, padding, padding);
			document.add(frame);

			// create column 1 box 3
			generateQualificationBox(cvVersion);

			// column 2 divider 1
			paragraph = new Paragraph();
			frame = new Frame(paragraph, widthCol2 - padding * 2, 0.5f);
			divider(frame, document.getPageHeight() - heightHeader);
			document.add(frame);

			// column 2 body
			generateBodyBox(cvVersion);
			generateWorkExperienceBox(cvVersion);
			generateHobbiesBox(cvVersion);
			paragraph = new Paragraph();
			frame = new Frame(paragraph, widthCol2 - padding * 2, 0.5f);
			divider(frame, heightHeader);
			document.add(frame);

			loadHeader();
			loadImages(arrowPath, logoPath);
			loadFooter();

			// returns
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            LOGGER.info("Document saving...");
            document.save(out);
            LOGGER.info("Document saved");
			byte[] data = out.toByteArray();
			InputStream in = new ByteArrayInputStream(data);
			byte[] byteArray = IOUtils.toByteArray(in);
			return byteArray;

		} catch (IOException e) {
			e.printStackTrace();
			throw new QaPortalSevereException("Cannot generate pdf");
		}
	}

	public void divider(Frame frame, float height) {
		frame.setBorder(Color.decode("#d9dbdb"), new Stroke(0.5f));
		frame.setAbsolutePosition(new Position(widthCol1, height));
		frame.setMargin(padding, 0, 0, 0);
		frame.setPaddingTop(5);
	}

	public String loadImages(String arrowPath, String logoPath) {
		// column 1 box 1 image
		ImageElement image;
		try {
			image = new ImageElement(arrowPath);
			image.setWidth(image.getWidth() / 35);
			image.setHeight(image.getHeight() / 35);
			image.setAbsolutePosition(new Position(padding, document.getPageHeight() - heightSideBox1 + padding + 20));
			document.add(image);
		} catch (IOException e) {
			e.printStackTrace();
			throw new QaPortalSevereException("Cannot load in CvPdfGeneratorImpl image_arrow");
		}

		// column 2 header image
		ImageElement logo;
		try {
			logo = new ImageElement("target/classes/QA_Logo.png");
			logo.setWidth(logo.getWidth() / 37f);
			logo.setHeight(logo.getHeight() / 37f);
			logo.setAbsolutePosition(new Position(widthCol1 + widthCol2 - logo.getWidth() - padding,
					heightFooter + heightBody + logo.getHeight() + 4));
			document.add(logo);
		} catch (Exception e) {
			e.printStackTrace();
			throw new QaPortalSevereException("Cannot load in CvPdfGeneratorImpl image_logo");
		}
		return "Passed";
	}

	public void loadHeader() {
		// column 2 Header
		paragraph = new Paragraph();
		try {
			paragraph.addMarkup("{color:#89898b}Consultant Profile", 8.8f, montserratUtf, montserratBoldUtf, montserratUtf,
					montserratUtf);
			paragraph.setAlignment(Alignment.Left);
			frame = new Frame(paragraph, widthCol2 - padding * 2, heightHeader);
			frame.setAbsolutePosition(new Position(widthCol1, heightFooter + heightBody + paragraph.getHeight() + 10));
			paragraph.setMaxWidth(frame.getWidth());
			frame.setMargin(padding, 0, 0, 0);
			document.add(frame);
		} catch (IOException e) {
			e.printStackTrace();
			throw new QaPortalSevereException("Cannot load in CvPdfGeneratorImpl loadHeader");
		}
	}

	public void generateConsultantNameBox(CvVersion cvVersion) {
		// column 1 box 1
		paragraph = new Paragraph();
		try {
			paragraph.addMarkup("{color:#FFFFFF}*" + cvVersion.getFirstName() + "\n" + cvVersion.getSurname() + "*",
					sideBarHeaderFontSize, kranaUtf, kranaUtf, kranaUtf, kranaUtf);
			paragraph.addMarkup("{color:" + QAPurple + "} \n*Consultant*", sideBarHeaderFontSize, kranaUtf, kranaUtf,
					kranaUtf, kranaUtf);
			frame = new Frame(paragraph, widthCol1, heightSideBox1);
			frame.setBackgroundColor(Color.decode(QARed));
			frame.setAbsolutePosition(new Position(0, document.getPageHeight()));
			frame.setPadding(padding, padding, padding, padding);
			document.add(frame);
		} catch (IOException e) {
			e.printStackTrace();
			throw new QaPortalSevereException("Cannot load in CvPdfGeneratorImpl generateConsultantNameBox");
		}
	}

	public Paragraph generateSkillsBox(Paragraph paragraph, String title, List<String> list) {
		try {
			paragraph.addMarkup("{color:#FFFFFF}*" + title + "*\n", sideBarTitleFontSize, montserratUtf, montserratBoldUtf,
					montserratUtf, montserratUtf);
			paragraph.addMarkup("\n", titleParagraphSpacing, montserratUtf, montserratBoldUtf, montserratUtf, montserratUtf);
			for (int i = 0; i < list.size(); i++) {
				if (i < list.size() - 1) {
					paragraph.addMarkup("{color:#FFFFFF}" + list.get(i) + ", ", sideBarListsFontSize, montserratUtf,
							montserratBoldUtf, montserratUtf, montserratUtf);
				} else {
					paragraph.addMarkup("{color:#FFFFFF}" + list.get(i) + "\n\n", sideBarListsFontSize, montserratUtf,
							montserratBoldUtf, montserratUtf, montserratUtf);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw new QaPortalSevereException("Cannot load in CvPdfGeneratorImpl generateSkillsBox");
		}
		return paragraph;
	}

	public void generateQualificationBox(CvVersion cvVersion) {
		// column 1 box 3
		paragraph = new Paragraph();
		try {
			paragraph.addMarkup("{color:#FFFFFF}*Qualification*\n", sideBarTitleFontSize, montserratUtf, montserratBoldUtf,
					montserratUtf, montserratUtf);
			paragraph.addMarkup("\n", titleParagraphSpacing, montserratUtf, montserratBoldUtf, montserratUtf, montserratUtf);
			for (Qualification i : cvVersion.getAllQualifications()) {
				paragraph.addMarkup("{color:#FFFFFF}" + i.getQualificationDetails(), sideBarListsFontSize, montserratUtf,
						montserratBoldUtf, montserratUtf, montserratUtf);
			}
			frame = new Frame(paragraph, widthCol1, heightSideBox3);
			frame.setBackgroundColor(Color.decode(QABlue));
			frame.setAbsolutePosition(new Position(0, heightSideBox3));
			paragraph.setMaxWidth(frame.getWidth() - (padding * 2));
			frame.setPadding(padding, padding, padding, padding);
			document.add(frame);
		} catch (IOException e) {
			e.printStackTrace();
			throw new QaPortalSevereException("Cannot load in CvPdfGeneratorImpl generateQualificationBox");
		}
	}

	public void generateBodyBox(CvVersion cvVersion) {
		// column 2 Body
		paragraph = new Paragraph();

		// Profile
		try {
			paragraph.addMarkup("{color:" + QAPurple + "}*PROFILE*\n", bodyHeadingsFontSize, kranaUtf, kranaUtf,
					kranaUtf, kranaUtf);
			paragraph.addMarkup("\n", 5, kranaUtf, kranaUtf, kranaUtf, kranaUtf);
			paragraph.addMarkup("{color:" + QAGrey + "}" + cvVersion.getProfile().getProfileDetails() + "\n\n\n",
					bodyParagraphFontSize, montserratUtf, montserratBoldUtf, montserratUtf, montserratUtf);
		} catch (IOException e) {
			e.printStackTrace();
			throw new QaPortalSevereException("Cannot load in CvPdfGeneratorImpl generateBodyBox Profile");
		}
	}

	public void generateWorkExperienceBox(CvVersion cvVersion) {
		// Work Experience
		try {
			paragraph.addMarkup("{color:" + QAPurple + "}*WORK EXPERIENCE - QA*\n", bodyHeadingsFontSize, kranaUtf,
					kranaUtf, kranaUtf, kranaUtf);
			paragraph.addMarkup("\n", 5, kranaUtf, kranaUtf, kranaUtf, kranaUtf);
			for (int i = 0; i < cvVersion.getAllWorkExperience().size(); i++) {
				paragraph.addMarkup(
						"{color:" + QABlue + "}*" + cvVersion.getAllWorkExperience().get(i).getJobTitle() + "*\n",
						bodyParagraphFontSize, montserratUtf, montserratBoldUtf, montserratUtf, montserratUtf);
				paragraph.addMarkup("\n", 4, montserratUtf, montserratBoldUtf, montserratUtf, montserratUtf);
=======
			// column 1 box 1
			paragraph = new Paragraph();
			paragraph.addMarkup("{color:#FFFFFF}*" + cvVersion.getFirstName() + "\n" + cvVersion.getSurname() + "*",
					sideBarHeaderFontSize, kranaFatB, kranaFatB, kranaFatB, kranaFatB);
			paragraph.addMarkup("{color:" + QAPurple + "} \n*Consultant*", sideBarHeaderFontSize, kranaFatB, kranaFatB,
					kranaFatB, kranaFatB);
			frame = new Frame(paragraph, widthCol1, heightSideBox1);
			frame.setBackgroundColor(Color.decode(QARed));
			frame.setAbsolutePosition(new Position(0, document.getPageHeight()));
			frame.setPadding(pd, pd, pd, pd);
			document.add(frame);

			// column 1 box 1 image
			ImageElement image = new ImageElement("target/classes/Arrow.png");
			image.setWidth(image.getWidth() / 35);
			image.setHeight(image.getHeight() / 35);
			image.setAbsolutePosition(new Position(pd, document.getPageHeight() - heightSideBox1 + pd + 20));
			document.add(image);

			// column 1 box 2
			paragraph = new Paragraph();
			box1_2(paragraph, "Programming Languages", cvVersion.getAllSkills().get(0).getProgrammingLanguages());
			box1_2(paragraph, "IDEs", cvVersion.getAllSkills().get(0).getIdes());
			box1_2(paragraph, "Operating Systems", cvVersion.getAllSkills().get(0).getOperatingSystems());
			box1_2(paragraph, "DevOps Technologies", cvVersion.getAllSkills().get(0).getDevops());
			box1_2(paragraph, "Database Technologies", cvVersion.getAllSkills().get(0).getDatabases());
			box1_2(paragraph, "Project Frameworks", cvVersion.getAllSkills().get(0).getPlatforms());
			box1_2(paragraph, "Other", cvVersion.getAllSkills().get(0).getOther());
			frame = new Frame(paragraph, widthCol1, heightSideBox2);
			frame.setBackgroundColor(Color.decode(QAPurple));
			frame.setAbsolutePosition(new Position(0, document.getPageHeight() - heightSideBox1));
			paragraph.setMaxWidth(frame.getWidth() - (pd * 2));
			frame.setPadding(pd, pd, pd, pd);
			document.add(frame);

			// column 1 box 3
			paragraph = new Paragraph();
			paragraph.addMarkup("{color:#FFFFFF}*Qualification*\n", sideBarTitleFontSize, montserrat, montserratBold,
					montserrat, montserrat);
			paragraph.addMarkup("\n", titleParagraphSpacing, montserrat, montserratBold, montserrat, montserrat);
			for (Qualification i : cvVersion.getAllQualifications()) {
				paragraph.addMarkup("{color:#FFFFFF}" + i.getQualificationDetails(), sideBarListsFontSize, montserrat,
						montserratBold, montserrat, montserrat);
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
			ImageElement logo = new ImageElement("target/classes/QA_Logo.png");
			logo.setWidth(logo.getWidth() / 37f);
			logo.setHeight(logo.getHeight() / 37f);
			logo.setAbsolutePosition(new Position(widthCol1 + widthCol2 - logo.getWidth() - pd,
					heightFooter + heightBody + logo.getHeight() + 4));
			document.add(logo);

			// column 2 Body
			paragraph = new Paragraph();
			paragraph.addMarkup("{color:" + QAPurple + "}*PROFILE*\n", bodyHeadingsFontSize, kranaFatB, kranaFatB,
					kranaFatB, kranaFatB);
			paragraph.addMarkup("\n", 5, kranaFatB, kranaFatB, kranaFatB, kranaFatB);
			// Profile
			paragraph.addMarkup("{color:" + QAGrey + "}" + cvVersion.getProfile().getProfileDetails() + "\n\n\n",
					bodyParagraphFontSize, montserrat, montserratBold, montserrat, montserrat);
			// Work Experience
			paragraph.addMarkup("{color:" + QAPurple + "}*WORK EXPERIANCE - QA*\n", bodyHeadingsFontSize, kranaFatB,
					kranaFatB, kranaFatB, kranaFatB);
			paragraph.addMarkup("\n", 5, kranaFatB, kranaFatB, kranaFatB, kranaFatB);
			for (int i = 0; i < cvVersion.getAllWorkExperience().size(); i++) {
				paragraph.addMarkup(
						"{color:" + QABlue + "}*" + cvVersion.getAllWorkExperience().get(i).getJobTitle() + "*\n",
						bodyParagraphFontSize, montserrat, montserratBold, montserrat, montserrat);
				paragraph.addMarkup("\n", 4, montserrat, montserratBold, montserrat, montserrat);
>>>>>>> 5e09bf99bb182402144c60f870db4af232b39afe
				paragraph
						.addMarkup(
								"{color:" + QAGrey + "}"
										+ cvVersion.getAllWorkExperience().get(i).getWorkExperienceDetails() + "\n\n",
<<<<<<< HEAD
								bodyParagraphFontSize, montserratUtf, montserratBoldUtf, montserratUtf, montserratUtf);
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw new QaPortalSevereException("Cannot load in CvPdfGeneratorImpl generateBodyBox Work Experiance");
		}
	}

	public void generateHobbiesBox(CvVersion cvVersion) {
		// Hobbies and Interests
		try {
			paragraph.addMarkup("{color:" + QAPurple + "}*HOBBIES/INTERESTS*\n", bodyHeadingsFontSize, kranaUtf,
					kranaUtf, kranaUtf, kranaUtf);
			paragraph.addMarkup("{color:" + QAGrey + "}" + cvVersion.getHobbies().getHobbiesDetails() + "\n\n",
					bodyParagraphFontSize, montserratUtf, montserratBoldUtf, montserratUtf, montserratUtf);
			frame = new Frame(paragraph, widthCol2, heightBody);
			frame.setAbsolutePosition(new Position(widthCol1, document.getPageHeight() - heightHeader));
			paragraph.setMaxWidth(frame.getWidth() - (padding * 2));
			frame.setPadding(padding, padding, padding, padding);
			document.add(frame);
		} catch (IOException e) {
			e.printStackTrace();
			throw new QaPortalSevereException(
					"Cannot load in CvPdfGeneratorImpl generateBodyBox Hobbies and Interests");
		}
	}

	public void loadFooter() {
		// column 2 Footer
		paragraph = new Paragraph();
		try {
			paragraph.addMarkup("{color:#89898b}+44 1273 022670 / qa.com", 8, montserratUtf, montserratBoldUtf, montserratUtf,
					montserratUtf);
			paragraph.setAlignment(Alignment.Right);
			frame = new Frame(paragraph, widthCol2 - padding * 2, heightHeader);
			frame.setAbsolutePosition(new Position(widthCol1, heightHeader));
			paragraph.setMaxWidth(frame.getWidth());
			frame.setMargin(padding, 0, 0, 0);
			frame.setPaddingTop(5);
			document.add(frame);
		} catch (IOException e) {
			e.printStackTrace();
			throw new QaPortalSevereException("Cannot load in CvPdfGeneratorImpl loadFooter");
=======
								bodyParagraphFontSize, montserrat, montserratBold, montserrat, montserrat);
			}

			// Hobbies and Interests
			paragraph.addMarkup("{color:" + QAPurple + "}*HOBBIES/INTERESTS*\n", bodyHeadingsFontSize, kranaFatB,
					kranaFatB, kranaFatB, kranaFatB);
			paragraph.addMarkup("{color:" + QAGrey + "}" + cvVersion.getHobbies().getHobbiesDetails() + "\n\n",
					bodyParagraphFontSize, montserrat, montserratBold, montserrat, montserrat);
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
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			document.save(out);
			byte[] data = out.toByteArray();
			InputStream in = new ByteArrayInputStream(data);
			byte[] byteArray = IOUtils.toByteArray(in);
			return byteArray;

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

	public void box1_2(Paragraph paragraph, String title, List<String> list) throws IOException {
		paragraph.addMarkup("{color:#FFFFFF}*" + title + "*\n", sideBarTitleFontSize, montserrat, montserratBold,
				montserrat, montserrat);
		paragraph.addMarkup("\n", titleParagraphSpacing, montserrat, montserratBold, montserrat, montserrat);
		for (int i = 0; i < list.size(); i++) {
			if (i < list.size() - 1) {
				paragraph.addMarkup("{color:#FFFFFF}" + list.get(i) + ", ", sideBarListsFontSize, montserrat,
						montserratBold, montserrat, montserrat);
			} else {
				paragraph.addMarkup("{color:#FFFFFF}" + list.get(i) + "\n\n", sideBarListsFontSize, montserrat,
						montserratBold, montserrat, montserrat);
			}
>>>>>>> 5e09bf99bb182402144c60f870db4af232b39afe
		}
	}
}
