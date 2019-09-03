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

import org.apache.pdfbox.io.IOUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
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

    Document document = new Document();
    PDDocument pdDoc = new PDDocument();

    Frame frame;
    Paragraph paragraph;
    
    // Fonts
    PDFont montserrat;
    PDFont montserratBold;
    PDFont kranaFatB;
    
    // QA Colour Scheme
    String QABlue = "#004050";
    String QAPurple = "#7F007D";
    String QARed = "#FF004C";
    String QAGrey = "#565759";

    // Font Sizes and Spacing
    int pd = 20;
    int sideBarHeaderFontSize = 20;
    int sideBarTitleFontSize = 11;
    int sideBarListsFontSize = 10;
    int sideTitleListSpacing = 4;
    int bodyHeadingsFontSize = 12;
    int bodyParagraphFontSize = 9;
    int bodyHeadingParaSpacing = 4;

    float heightSideBox1 = document.getPageHeight() / 6 + 4;
    float heightSideBox2 = (float) 2.5 * document.getPageHeight() / 6 + 7;
    float heightSideBox3 = document.getPageHeight() - heightSideBox1 - heightSideBox2;
    float widthCol1 = document.getPageWidth() / 3 + 4.5f;
    float widthCol2 = document.getPageWidth() - widthCol1;
    float heightHeader = document.getPageHeight() / 20 - 8;
    float heightFooter = document.getPageHeight() / 20 - 8;
    float heightBody = document.getPageHeight() - heightHeader - heightFooter;

    public PDFont loadFont(String path) throws IOException {
    	Resource fontResource = new ClassPathResource(path);
    	return PDType0Font.load(document.getPDDocument(), fontResource.getInputStream());
    }

    @PostConstruct
    public void loadfonts() {    	
        try {
        	this.montserrat = loadFont("Montserrat-Regular.ttf");
        	this.montserratBold = loadFont("Montserrat-SemiBold.ttf");
        	this.kranaFatB = loadFont("Krana-Fat-B.ttf");
        } catch (IOException e) {
            e.printStackTrace();
            throw new QaPortalBusinessException("Cannot load in CvPdfGeneratorImpl fonts");
        }
    }

    @Override
    public byte[] generateCv(CvVersion cvVersion) {
        try {            
            CvPdfElement consultantNameBox = new CvPdfElement(widthCol1, heightSideBox1, 0, document.getPageHeight(), pd);
            paragraph = consultantNameBox.getParagraph();
            frame = consultantNameBox.getFrame();
            paragraph.addMarkup("{color:#FFFFFF}*" + cvVersion.getFirstName() + "\n" + cvVersion.getSurname() + "*", sideBarHeaderFontSize, kranaFatB, kranaFatB, kranaFatB, kranaFatB);
            paragraph.addMarkup("{color:" + QAPurple + "} \n*Consultant*", sideBarHeaderFontSize, kranaFatB, kranaFatB, kranaFatB, kranaFatB);
            frame.setBackgroundColor(Color.decode(QARed));
            document.add(frame);
            
            ImageElement arrow = loadImages("target/classes/Arrow.png", 35);
            arrow.setAbsolutePosition(new Position(pd, document.getPageHeight() - heightSideBox1 + pd + 20));

            CvPdfElement skillsBox = new CvPdfElement(widthCol1, heightSideBox2, 0, document.getPageHeight() - heightSideBox1, pd);
            paragraph = skillsBox.getParagraph();
            frame = skillsBox.getFrame();
            generateSkillsBox(paragraph, "Programming Languages", cvVersion.getAllSkills().get(0).getProgrammingLanguages());
            generateSkillsBox(paragraph, "IDEs", cvVersion.getAllSkills().get(0).getIdes());
            generateSkillsBox(paragraph, "Operating Systems", cvVersion.getAllSkills().get(0).getOperatingSystems());
            generateSkillsBox(paragraph, "DevOps Technologies", cvVersion.getAllSkills().get(0).getDevops());
            generateSkillsBox(paragraph, "Database Technologies", cvVersion.getAllSkills().get(0).getDatabases());
            generateSkillsBox(paragraph, "Project Frameworks", cvVersion.getAllSkills().get(0).getPlatforms());
            generateSkillsBox(paragraph, "Other", cvVersion.getAllSkills().get(0).getOther());
            frame.setBackgroundColor(Color.decode(QAPurple));
            document.add(frame);

            CvPdfElement qualificationBox = new CvPdfElement(widthCol1, heightSideBox3, 0, heightSideBox3, pd);
            paragraph = qualificationBox.getParagraph();
            frame = qualificationBox.getFrame();
            paragraph.addMarkup("{color:#FFFFFF}*Qualification*\n", sideBarTitleFontSize, montserrat, montserratBold, montserrat, montserrat);
            paragraph.addMarkup("\n", sideTitleListSpacing, montserrat, montserratBold, montserrat, montserrat);
            for (Qualification i : cvVersion.getAllQualifications()) {
                paragraph.addMarkup("{color:#FFFFFF}" + i.getQualificationDetails(), sideBarListsFontSize, montserrat, montserratBold,
                        montserrat, montserrat);
            }
            frame.setBackgroundColor(Color.decode(QABlue));
            document.add(frame);

            CvPdfElement header = new CvPdfElement(widthCol2, heightHeader, widthCol1, document.getPageHeight(), pd);
            paragraph = header.getParagraph();
            frame = header.getFrame();
            paragraph.addMarkup("{color:#89898b}Consultant Profile", 8.8f, montserrat, montserratBold, montserrat, montserrat);
            document.add(frame);
            
            ImageElement logo = loadImages("target/classes/QA_Logo.png", 37);
            logo.setAbsolutePosition(new Position((widthCol1 + widthCol2 - logo.getWidth() - pd), (heightFooter + heightBody + logo.getHeight() + 4)));

            CvPdfElement body = new CvPdfElement(widthCol2, heightBody, widthCol1, document.getPageHeight()-heightHeader, pd);
            paragraph = body.getParagraph();
            frame = body.getFrame();
            bodyTitle(paragraph, "PROFILE");
            paragraph.addMarkup("{color:" + QAGrey + "}" + cvVersion.getProfile().getProfileDetails() + "\n\n\n", bodyParagraphFontSize, montserrat, montserratBold, montserrat, montserrat);
            bodyTitle(paragraph, "WORK EXPERIENCE");
            for (int i = 0; i < cvVersion.getAllWorkExperience().size(); i++) {
                paragraph.addMarkup("{color:" + QABlue + "}*" + cvVersion.getAllWorkExperience().get(i).getJobTitle() + "*\n", bodyParagraphFontSize, montserrat, montserratBold, montserrat, montserrat);
                paragraph.addMarkup("\n", 4, montserrat, montserratBold, montserrat, montserrat);
                paragraph.addMarkup("{color:" + QAGrey + "}" + cvVersion.getAllWorkExperience().get(i).getWorkExperienceDetails() + "\n\n", bodyParagraphFontSize, montserrat, montserratBold, montserrat, montserrat);
            }
            paragraph.addMarkup("\n", bodyParagraphFontSize, montserrat, montserratBold, montserrat, montserrat);
            bodyTitle(paragraph, "HOBBIES/INTERESTS");
            paragraph.addMarkup("{color:" + QAGrey + "}" + cvVersion.getHobbies().getHobbiesDetails() + "\n\n", bodyParagraphFontSize, montserrat, montserratBold, montserrat, montserrat);
            document.add(frame);
            
            CvPdfElement footer = new CvPdfElement(widthCol2, heightFooter, widthCol1, heightFooter, pd);
            paragraph = footer.getParagraph();
            frame = footer.getFrame();
            paragraph.addMarkup("{color:#89898b}+44 1273 022670 / qa.com", 8, montserrat, montserratBold, montserrat, montserrat);
            paragraph.setAlignment(Alignment.Right);
            frame.setPadding(pd, pd, 10, 0);
            document.add(frame);
            
            divider(document.getPageHeight()-heightHeader);
            divider(heightFooter);

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
    
    public ImageElement loadImages(String path, float resizeFactor) throws IOException {
        ImageElement img = new ImageElement(path);
        img.setWidth(img.getWidth() / resizeFactor);
        img.setHeight(img.getHeight() / resizeFactor);
        document.add(img);
        return img;
    }

    public void bodyTitle(Paragraph paragraph, String title) throws IOException {
        paragraph.addMarkup("{color:" + QAPurple + "}*" +title+ "*\n", bodyHeadingsFontSize, kranaFatB, kranaFatB, kranaFatB, kranaFatB);
        paragraph.addMarkup("\n", 5, kranaFatB, kranaFatB, kranaFatB, kranaFatB);
    }
    
    public void divider(float yPosition) throws IOException {
    	CvPdfElement divider = new CvPdfElement(widthCol2-pd*2, 0.5f, widthCol1+pd, yPosition, pd);
    	paragraph = divider.getParagraph();
    	frame = divider.getFrame();
        frame.setBorder(Color.decode("#d9dbdb"), new Stroke(0.5f));
        document.add(frame);
    }

    public void generateSkillsBox(Paragraph paragraph, String title, List<String> list) throws IOException {
        paragraph.addMarkup("{color:#FFFFFF}*" + title + "*\n", sideBarTitleFontSize, montserrat, montserratBold, montserrat, montserrat);
        paragraph.addMarkup("\n", sideTitleListSpacing, montserrat, montserratBold, montserrat, montserrat);
        for (int i = 0; i < list.size(); i++) {
            if (i < list.size() - 1) {
                paragraph.addMarkup("{color:#FFFFFF}" + list.get(i) + ", ", sideBarListsFontSize, montserrat, montserratBold, montserrat,
                        montserrat);
            } else {
                paragraph.addMarkup("{color:#FFFFFF}" + list.get(i) + "\n\n", sideBarListsFontSize, montserrat, montserratBold,
                        montserrat, montserrat);
            }
        }
    }
}
