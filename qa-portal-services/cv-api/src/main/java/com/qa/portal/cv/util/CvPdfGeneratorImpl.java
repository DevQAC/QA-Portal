package com.qa.portal.cv.util;

import com.qa.portal.common.exception.QaPortalBusinessException;
import com.qa.portal.common.exception.QaPortalSevereException;
import com.qa.portal.cv.domain.CvVersion;
import com.qa.portal.cv.domain.Qualification;
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

import javax.annotation.PostConstruct;
import java.awt.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Component
public class CvPdfGeneratorImpl implements CvPdfGenerator {

	private final Logger LOGGER = LoggerFactory.getLogger(CvPdfGeneratorImpl.class);

	// Document components
	private Document document = new Document();
	private PDDocument pdDoc = new PDDocument();
	private Frame frame;
	private Paragraph paragraph;

    // QA Colour Scheme
    private static final String QA_BLUE = "#004050";
    private static final String QA_PURPLE = "#7F007D";
    private static final String QA_RED = "#FF004C";
    private static final String QA_GREY = "#565759";
    private static final int PD = 20;

    // Dimensions
    private final float HEIGHT_SIDE_BOX1 = document.getPageHeight() / 6 + 4;
    private final float HEIGHT_SIDE_BOX2 = (float) 2.5 * document.getPageHeight() / 6 + 7;
    private final float HEIGHT_SIDE_BOX3 = document.getPageHeight() - HEIGHT_SIDE_BOX1 - HEIGHT_SIDE_BOX2;
    private final float WIDTH_COL1 = document.getPageWidth() / 3 + 4.5f;
    private final float WIDTH_COL2 = document.getPageWidth() - WIDTH_COL1;
    private final float HEIGHT_HEADER = document.getPageHeight() / 20 - 8;
    private final float HEIGHT_FOOTER = document.getPageHeight() / 30;
    private final float HEIGHT_BODY = document.getPageHeight() - HEIGHT_HEADER - HEIGHT_FOOTER;

    // Fonts
    private PDFont montserrat;
    private PDFont montserratBold;
    private PDFont kranaFatB;

    @PostConstruct
    public void loadfonts() {
        try {
            Resource montRegResource = new ClassPathResource("Montserrat-Regular.ttf");
            Resource montBoldResource = new ClassPathResource("Montserrat-SemiBold.ttf");
            Resource kranaResource = new ClassPathResource("Krana-Fat-B.ttf");
            this.montserrat = PDType0Font.load(document.getPDDocument(),
                    montRegResource.getInputStream());
            this.montserratBold = PDType0Font.load(document.getPDDocument(),
                    montBoldResource.getInputStream());
            this.kranaFatB = PDType0Font.load(document.getPDDocument(),
                    kranaResource.getInputStream());
        } catch (IOException e) {
			LOGGER.error("Exception loading fonts for PDF generation " + e.getMessage(), e);
            throw new QaPortalBusinessException("Exception loading fonts for generation of CV PDF file.");
        }
    }

    @Override
    public byte[] generateCv(CvVersion cvVersion) {

        Resource res = new ClassPathResource("generatedCv.pdf");
        try {
            // column 1 box 1
            paragraph = new Paragraph();
            paragraph.addMarkup("{color:#FFFFFF}*" + cvVersion.getFirstName() + "\n" + cvVersion.getSurname() + "*", 20,
                    kranaFatB, kranaFatB, kranaFatB, kranaFatB);
            paragraph.addMarkup("{color:" + QA_PURPLE + "} \n*" + cvVersion.getCohort() + "*", 20, kranaFatB, kranaFatB,
                    kranaFatB, kranaFatB);
            frame = new Frame(paragraph, WIDTH_COL1, HEIGHT_SIDE_BOX1);
            frame.setBackgroundColor(Color.decode(QA_RED));
            frame.setAbsolutePosition(new Position(0, document.getPageHeight()));
            frame.setPadding(PD, PD, PD, PD);
            document.add(frame);

            // column 1 box 1 image
            ImageElement image = new ImageElement("target/classes/Arrow.png");
            image.setWidth(image.getWidth() / 35);
            image.setHeight(image.getHeight() / 35);
            image.setAbsolutePosition(new Position(PD, document.getPageHeight() - HEIGHT_SIDE_BOX1 + PD + 20));
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
            frame = new Frame(paragraph, WIDTH_COL1, HEIGHT_SIDE_BOX2);
            frame.setBackgroundColor(Color.decode(QA_PURPLE));
            frame.setAbsolutePosition(new Position(0, document.getPageHeight() - HEIGHT_SIDE_BOX1));
            paragraph.setMaxWidth(frame.getWidth() - (PD * 2));
            frame.setPadding(PD, PD, PD, PD);
            document.add(frame);

            // column 1 box 3
            paragraph = new Paragraph();
            paragraph.addMarkup("{color:#FFFFFF}*Qualification*", 20, montserrat, montserratBold, montserrat,
                    montserrat);
            for (Qualification i : cvVersion.getAllQualifications()) {
                paragraph.addMarkup("\n\n{color:#FFFFFF}" + i.getQualificationDetails(), 10, montserrat, montserratBold,
                        montserrat, montserrat);
            }
            frame = new Frame(paragraph, WIDTH_COL1, HEIGHT_SIDE_BOX3);
            frame.setBackgroundColor(Color.decode(QA_BLUE));
            frame.setAbsolutePosition(new Position(0, HEIGHT_SIDE_BOX3));
            paragraph.setMaxWidth(frame.getWidth() - (PD * 2));
            frame.setPadding(PD, PD, PD, PD);
            document.add(frame);

            // column 2 Header
            paragraph = new Paragraph();
            paragraph.addMarkup("{color:#89898b}Consultant Profile", 8.8f, montserrat, montserratBold, montserrat,
                    montserrat);
            paragraph.setAlignment(Alignment.Left);
            frame = new Frame(paragraph, WIDTH_COL2 - PD * 2, HEIGHT_HEADER);
            frame.setAbsolutePosition(new Position(WIDTH_COL1, HEIGHT_FOOTER + HEIGHT_BODY + paragraph.getHeight() + 10));
            paragraph.setMaxWidth(frame.getWidth());
            frame.setMargin(PD, 0, 0, 0);
            document.add(frame);

            // column 2 header image
            ImageElement logo = new ImageElement("target/classes/QA_Logo.png");
            logo.setWidth(logo.getWidth() / 37f);
            logo.setHeight(logo.getHeight() / 37f);
            logo.setAbsolutePosition(new Position(WIDTH_COL1 + WIDTH_COL2 - logo.getWidth() - PD,
                    HEIGHT_FOOTER + HEIGHT_BODY + logo.getHeight() + 4));
            document.add(logo);

            // column 2 Body
            paragraph = new Paragraph();
            paragraph.addMarkup("{color:" + QA_PURPLE + "}*PROFILE*\n", 12, kranaFatB, kranaFatB, kranaFatB, kranaFatB);
            paragraph.addMarkup("\n", 5, kranaFatB, kranaFatB, kranaFatB, kranaFatB);
            // Profile
            paragraph.addMarkup("{color:" + QA_GREY + "}" + cvVersion.getProfile().getProfileDetails() + "\n\n\n", 9,
                    montserrat, montserratBold, montserrat, montserrat);
            // Work Experience
            paragraph.addMarkup("{color:" + QA_PURPLE + "}*WORK EXPERIANCE - QA*\n", 12, kranaFatB, kranaFatB, kranaFatB,
                    kranaFatB);
            paragraph.addMarkup("\n", 5, kranaFatB, kranaFatB, kranaFatB, kranaFatB);
            for (int i = 0; i < cvVersion.getAllWorkExperience().size(); i++) {
                paragraph.addMarkup(
                        "{color:" + QA_BLUE + "}*" + cvVersion.getAllWorkExperience().get(i).getJobTitle() + "*\n", 9,
                        montserrat, montserratBold, montserrat, montserrat);
                paragraph.addMarkup("\n", 4, montserrat, montserratBold, montserrat, montserrat);
                paragraph
                        .addMarkup(
                                "{color:" + QA_GREY + "}"
                                        + cvVersion.getAllWorkExperience().get(i).getWorkExperienceDetails() + "\n\n",
                                9, montserrat, montserratBold, montserrat, montserrat);
            }

            // Hobbies and Interests
            paragraph.addMarkup("{color:" + QA_PURPLE + "}*HOBBIES/INTERESTS*\n", 12, kranaFatB, kranaFatB, kranaFatB,
                    kranaFatB);
            paragraph.addMarkup("{color:" + QA_GREY + "}" + cvVersion.getHobbies().getHobbiesDetails() + "\n\n", 9, montserrat,
                    montserratBold, montserrat, montserrat);
            frame = new Frame(paragraph, WIDTH_COL2, HEIGHT_BODY);
            frame.setAbsolutePosition(new Position(WIDTH_COL1, document.getPageHeight() - HEIGHT_HEADER));
            paragraph.setMaxWidth(frame.getWidth() - (PD * 2));
            frame.setPadding(PD, PD, PD, PD);
            document.add(frame);

            // column 2 Footer
            paragraph = new Paragraph();
            paragraph.addMarkup("{color:#89898b}+44 1273 022670 / qa.com", 8, montserrat, montserratBold, montserrat,
                    montserrat);
            paragraph.setAlignment(Alignment.Right);
            frame = new Frame(paragraph, WIDTH_COL2 - PD * 2, HEIGHT_HEADER);
            frame.setAbsolutePosition(new Position(WIDTH_COL1, HEIGHT_HEADER));
            paragraph.setMaxWidth(frame.getWidth());
            frame.setMargin(PD, 0, 0, 0);
            frame.setPaddingTop(5);
            document.add(frame);

            // column 2 divider 1
            paragraph = new Paragraph();
            frame = new Frame(paragraph, WIDTH_COL2 - PD * 2, 0.5f);
            divider(frame, document.getPageHeight() - HEIGHT_HEADER);
            document.add(frame);

            // column 2 divider 2
            paragraph = new Paragraph();
            frame = new Frame(paragraph, WIDTH_COL2 - PD * 2, 0.5f);
            divider(frame, HEIGHT_HEADER);
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
        frame.setAbsolutePosition(new Position(WIDTH_COL1, height));
        frame.setMargin(PD, 0, 0, 0);
        frame.setPaddingTop(5);
    }

    public void box1_2(Paragraph paragraph, String title, List<String> list) throws IOException {
        paragraph.addMarkup("{color:#FFFFFF}*" + title + "*\n", 10, montserrat, montserratBold, montserrat, montserrat);
        paragraph.addMarkup("\n", 4, montserrat, montserratBold, montserrat, montserrat);
        for (int i = 0; i < list.size(); i++) {
            if (i < list.size() - 1) {
                paragraph.addMarkup("{color:#FFFFFF}" + list.get(i) + ", ", 10, montserrat, montserratBold, montserrat,
                        montserrat);
            } else {
                paragraph.addMarkup("{color:#FFFFFF}" + list.get(i) + "\n\n", 10, montserrat, montserratBold,
                        montserrat, montserrat);
            }
        }
    }
}
