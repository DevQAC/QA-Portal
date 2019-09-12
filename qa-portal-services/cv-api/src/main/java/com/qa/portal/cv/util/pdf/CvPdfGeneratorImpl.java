package com.qa.portal.cv.util.pdf;

import com.qa.portal.common.exception.QaPortalBusinessException;
import com.qa.portal.common.exception.QaPortalSevereException;
import com.qa.portal.cv.domain.CvVersion;
import com.qa.portal.cv.domain.Qualification;
import com.qa.portal.cv.domain.WorkExperience;
import org.apache.fontbox.ttf.TTFParser;
import org.apache.fontbox.ttf.TrueTypeFont;
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
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import java.util.function.Function;

import static com.qa.portal.cv.util.pdf.CvPdfConstants.*;

@Component
public class CvPdfGeneratorImpl implements CvPdfGenerator {

    private final Logger LOGGER = LoggerFactory.getLogger(CvPdfGeneratorImpl.class);

    private Map<String, Function<CvVersion, List<String>>> skillsMap;

    private DateTimeFormatter dateTimeFormatter;

    private TrueTypeFont montserratTTF;

    private TrueTypeFont montserratBoldTTF;

    private TrueTypeFont kranaFatBTTF;

    @PostConstruct
    public void init() {
        createSkillsMap();
        createFonts();
        createDateFormatter();
    }

	@Override
	public byte[] generateCv(CvVersion cvVersion) {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {
			Document document = buildCvDocument(cvVersion);
			document.save(out);
			return out.toByteArray();
		} catch (Exception e) {
			LOGGER.error("Error generating PDF for CV " + e.getMessage(), e);
			throw new QaPortalSevereException("Error generating PDF for CV");
		}
		finally {
			try {
				out.close();
			}
			catch (Exception e) {
				LOGGER.error("Error closing output stream for PDF " + e.getMessage(), e);
			}
		}
	}

    private Document buildCvDocument(CvVersion cvVersion) throws Exception {
        Document document = new Document();
        PDFont montserrat = PDType0Font.load(document.getPDDocument(), montserratTTF, true);
        PDFont montserratBold = PDType0Font.load(document.getPDDocument(), montserratBoldTTF, true);
        PDFont kranaFatB = PDType0Font.load(document.getPDDocument(), kranaFatBTTF, true);
        document.add(generateConsultantNameBox(cvVersion, kranaFatB));
        document.add(generateSkillsBox(cvVersion, montserrat, montserratBold));
        document.add(generateQualificationsBox(cvVersion, montserrat, montserratBold));
        document.add(generateMainHeader(cvVersion, montserrat, montserratBold));
        document.add(generateMainBody(cvVersion, montserrat, montserratBold, kranaFatB));
        document.add(generateMainFooter(montserrat, montserratBold));
        document.add(divider(Dividers.Y_POSITION_TOP.value));
        document.add(divider(Dividers.Y_POSITION_BOTTOM.value));
        return document;
    }

    private Frame generateConsultantNameBox(CvVersion cvVersion, PDFont kranaFatB) throws IOException {
        CvPdfElement consultantNameBox = getCvPdfElement(ConsultantNameBox.WIDTH.value, ConsultantNameBox.HEIGHT.value,
                ConsultantNameBox.X_POSITION.value, ConsultantNameBox.Y_POSITION.value);
        Paragraph paragraph = consultantNameBox.getParagraph();
        Frame frame = consultantNameBox.getFrame();
		paragraph.addMarkup("{color:" + ColourScheme.QA_PURPLE.value + "}Consultant\n",
				FontSize.CONSULTANT_NAME.value, kranaFatB, kranaFatB, kranaFatB, kranaFatB);
        paragraph.addMarkup(WHITE_COLOUR + cvVersion.getFirstName() + "\n" + cvVersion.getSurname() + "*",
                FontSize.CONSULTANT_NAME.value, kranaFatB, kranaFatB, kranaFatB, kranaFatB);
        frame.setBackgroundColor(Color.decode(ColourScheme.QA_RED.value));
        return frame;
    }

    private Frame generateSkillsBox(CvVersion cvVersion, PDFont montserrat, PDFont montserratBold) throws IOException{
        CvPdfElement skillsBox = getCvPdfElement(SkillsBox.WIDTH.value, SkillsBox.HEIGHT.value,
                SkillsBox.X_POSITION.value, SkillsBox.Y_POSITION.value);
        Paragraph paragraph = skillsBox.getParagraph();
		paragraph.addMarkup(WHITE_COLOUR + SKILLS_HEADER + "\n", FontSize.SIDEBAR_TITLES.value, montserrat,
				montserratBold, montserrat, montserrat);
		paragraph.addMarkup("\n", FontSize.SIDEBAR_TITLE_CONTENT_SPACING.value, montserrat, montserratBold, montserrat,
				montserrat);
        skillsMap.keySet().stream()
                .forEach(k -> generateSkillsBoxContent(paragraph,
                        k,
                        skillsMap.get(k).apply(cvVersion),
                        montserrat,
                        montserratBold));
        Frame frame = skillsBox.getFrame();
        frame.setBackgroundColor(Color.decode(ColourScheme.QA_PURPLE.value));
        return frame;
    }

    private void generateSkillsBoxContent(Paragraph paragraph, String title, List<String> list,
                                          PDFont montserrat, PDFont montserratBold) {
        try {
            if (list != null && list.size() > 0) {
                paragraph.addMarkup(WHITE_COLOUR + title + "*\n", FontSize.SIDEBAR_TITLES.value, montserrat,
                        montserratBold, montserrat, montserrat);
                paragraph.addMarkup("\n", FontSize.SIDEBAR_TITLE_CONTENT_SPACING.value, montserrat, montserratBold, montserrat,
                        montserrat);
                for (int i = 0; i < list.size(); i++) {
                    if (i < list.size() - 1) {
                        paragraph.addMarkup(WHITE_COLOUR + list.get(i) + ", ", FontSize.SIDEBAR_CONTENT.value, montserrat,
                                montserratBold, montserrat, montserrat);
                    } else {
                        paragraph.addMarkup(WHITE_COLOUR + list.get(i) + "\n\n", FontSize.SIDEBAR_CONTENT.value,
                                montserrat, montserratBold, montserrat, montserrat);
                    }
                }
            }
        } catch (Exception e) {
            throw new QaPortalBusinessException("Error generating PDF file for CV");
        }
    }

    private Frame generateQualificationsBox(CvVersion cvVersion, PDFont montserrat, PDFont montserratBold) throws IOException {
        CvPdfElement qualificationsBox = getCvPdfElement(QualificationsBox.WIDTH.value, QualificationsBox.HEIGHT.value,
                QualificationsBox.X_POSITION.value, QualificationsBox.Y_POSITION.value);
        Paragraph paragraph = qualificationsBox.getParagraph();
        Frame frame = qualificationsBox.getFrame();
        paragraph.addMarkup(WHITE_COLOUR + QUALIFICATION_HEADER + "\n", FontSize.SIDEBAR_TITLES.value, montserrat,
                montserratBold, montserrat, montserrat);
        paragraph.addMarkup("\n", FontSize.SIDEBAR_TITLE_CONTENT_SPACING.value, montserrat, montserratBold, montserrat,
                montserrat);
        for (Qualification i : cvVersion.getAllQualifications()) {
            paragraph.addMarkup(WHITE_COLOUR + i.getQualificationDetails() + "\n\n", FontSize.SIDEBAR_CONTENT.value,
                    montserrat, montserratBold, montserrat, montserrat);
        }
        frame.setBackgroundColor(Color.decode(ColourScheme.QA_BLUE.value));
        return frame;
    }

    private Frame generateMainHeader(CvVersion cvVersion, PDFont montserrat, PDFont montserratBold) throws IOException {
        CvPdfElement header = getCvPdfElement(MainHeader.WIDTH.value, MainHeader.HEIGHT.value,
                MainHeader.X_POSITION.value, MainHeader.Y_POSITION.value);
        Paragraph paragraph = header.getParagraph();
        Frame frame = header.getFrame();
        paragraph.addMarkup(CONSULTANT_HEADER + cvVersion.getFirstName() + " " + cvVersion.getSurname(), 8.8f, montserrat, montserratBold, montserrat,
                montserrat);
        ImageElement logo = loadImages(Images.LOGO.filePath, Images.LOGO.resizeFactor);
        logo.setAbsolutePosition(
                new Position(Images.LOGO.xPosition - logo.getWidth(), Images.LOGO.yPosition + logo.getHeight()));
        return frame;
    }

    private Frame generateMainBody(CvVersion cvVersion,
                                   PDFont montserrat,
                                   PDFont montserratBold,
                                   PDFont kranaFatB) throws IOException {
        CvPdfElement body = getCvPdfElement(MainBody.WIDTH.value, null, MainBody.X_POSITION.value,
                MainBody.Y_POSITION.value);
        Paragraph paragraph = body.getParagraph();
        Frame frame = body.getFrame();
        bodyTitle(paragraph, SECTION_HEADER_PROFILE, kranaFatB);
        paragraph.addMarkup(
                "{color:" + ColourScheme.QA_GREY.value + "}" + cvVersion.getProfile().getProfileDetails() + "\n\n\n",
                FontSize.BODY_CONTENT.value, montserrat, montserratBold, montserrat, montserrat);
        bodyTitle(paragraph, SECTION_HEADER_WORK_EXPERIENCE, kranaFatB);
        for (int i = 0; i < cvVersion.getAllWorkExperience().size(); i++) {
            paragraph.addMarkup(
                    "{color:" + ColourScheme.QA_BLUE.value + "}*"
                            + getWorkExperenceJobHeader(cvVersion.getAllWorkExperience().get(i)) + "*\n",
                    FontSize.BODY_CONTENT.value, montserrat, montserratBold, montserrat, montserrat);
            paragraph.addMarkup("\n", FontSize.BODY_SUBTITLE_CONTENT_SPACING.value, montserrat, montserratBold,
                    montserrat, montserrat);
            paragraph.addMarkup(
                    "{color:" + ColourScheme.QA_GREY.value + "}"
                            + cvVersion.getAllWorkExperience().get(i).getWorkExperienceDetails() + "\n\n",
                    FontSize.BODY_CONTENT.value, montserrat, montserratBold, montserrat, montserrat);
        }
        paragraph.addMarkup("\n", FontSize.BODY_CONTENT.value, montserrat, montserratBold, montserrat, montserrat);
        bodyTitle(paragraph, SECTION_HEADER_HOBBIES, kranaFatB);
        paragraph.addMarkup(
                "{color:" + ColourScheme.QA_GREY.value + "}" + cvVersion.getHobbies().getHobbiesDetails() + "\n\n",
                FontSize.BODY_CONTENT.value, montserrat, montserratBold, montserrat, montserrat);
        return frame;
    }

    private Frame generateMainFooter(PDFont montserrat, PDFont montserratBold) throws IOException {
        CvPdfElement footer = getCvPdfElement(MainFooter.WIDTH.value, MainFooter.HEIGHT.value,
                MainFooter.X_POSITION.value, MainFooter.Y_POSITION.value);
        Paragraph paragraph = footer.getParagraph();
        Frame frame = footer.getFrame();
        paragraph.addMarkup(FOOTER_CONTACT, 8, montserrat, montserratBold, montserrat,
                montserrat);
        paragraph.setAlignment(Alignment.Right);
        frame.setPadding(PageFormat.PADDING.value, PageFormat.PADDING.value, 10, 0);
        return frame;
    }

    private ImageElement loadImages(String path, float resizeFactor) throws IOException {
        ImageElement img = new ImageElement(path);
        img.setWidth(img.getWidth() / resizeFactor);
        img.setHeight(img.getHeight() / resizeFactor);
        return img;
    }

    private void bodyTitle(Paragraph paragraph,
                           String title,
                           PDFont kranaFatB) throws IOException {
        paragraph.addMarkup("{color:" + ColourScheme.QA_PURPLE.value + "}*" + title + "*\n", FontSize.BODY_TITLES.value,
                kranaFatB, kranaFatB, kranaFatB, kranaFatB);
        paragraph.addMarkup("\n", FontSize.BODY_TITLE_CONTENT_SPACING.value, kranaFatB, kranaFatB, kranaFatB,
                kranaFatB);
    }

    private Frame divider(float yPosition) {
        CvPdfElement divider = getCvPdfElement(Dividers.WIDTH.value, Dividers.HEIGHT.value, Dividers.X_POSITION.value,
                yPosition);
        Frame frame = divider.getFrame();
        frame.setBorder(Color.decode("#d9dbdb"), new Stroke(0.5f));
        return frame;
    }

    private void createSkillsMap() {
        skillsMap = new HashMap<>();
		skillsMap.put(SKILLS_TITLE_OTHER, (cvVersion) -> cvVersion.getAllSkills().get(0).getOther());
        skillsMap.put(SKILLS_TITLE_PROGRAMMING_LANGUAGES, (cvVersion) -> cvVersion.getAllSkills().get(0).getProgrammingLanguages());
        skillsMap.put(SKILLS_TITLE_IDES, (cvVersion) -> cvVersion.getAllSkills().get(0).getIdes());
        skillsMap.put(SKILLS_TITLE_DATABASES, (cvVersion) -> cvVersion.getAllSkills().get(0).getDatabases());
        skillsMap.put(SKILLS_TITLE_DEVOPS, (cvVersion) -> cvVersion.getAllSkills().get(0).getDevops());
        skillsMap.put(SKILLS_TITLE_OPERATING_SYSTEMS, (cvVersion) -> cvVersion.getAllSkills().get(0).getOperatingSystems());
        skillsMap.put(SKILLS_TITLE_PROJECT_FRAMEWORKS, (cvVersion) -> cvVersion.getAllSkills().get(0).getPlatforms());
    }

    private void createFonts() {
        Resource montRegResource = new ClassPathResource(MONTSERRAT_TTF_FILE);
        Resource montBoldResource = new ClassPathResource(MONTSERRAT_BOLD_TTF_FILE);
        Resource kranaResource = new ClassPathResource(KRANA_TTF_FILE);
        try {
            montserratTTF = new TTFParser().parse(montRegResource.getInputStream());
            montserratBoldTTF = new TTFParser().parse(montBoldResource.getInputStream());
            kranaFatBTTF = new TTFParser().parse(kranaResource.getInputStream());
        } catch (Exception e) {
            LOGGER.error("Error loading fonts for CV generation " + e.getMessage(), e);
            throw new QaPortalBusinessException("Cannot load fonts for CV generation");
        }
    }

    private void createDateFormatter() {
        dateTimeFormatter = DateTimeFormatter.ofPattern("YYYY.MM");
    }

    private CvPdfElement getCvPdfElement(Float width, Float height, Float xPos, Float yPos) {
        try {
            return new CvPdfElement(width, height, xPos, yPos);
        } catch (Exception e) {
            LOGGER.error("Error generating CV " + e.getMessage(), e);
            throw new QaPortalBusinessException("Error generating PDF file for CV");
        }
    }

    private String getWorkExperenceJobHeader(WorkExperience workExperience) {
        StringBuffer sb = new StringBuffer();
        sb.append(formatDateToMMYY(workExperience.getStart()));
        sb.append(" - ");
        sb.append((workExperience.getEnd() == null || workExperience.getEnd().trim().length() == 0) ? "Present" : formatDateToMMYY(workExperience.getEnd()));
        sb.append(" : ");
        sb.append(workExperience.getJobTitle());
        return sb.toString();
    }

    private String formatDateToMMYY(String dateString) {
        Instant dateTime = Instant.parse(dateString);
        return dateTime.atZone(ZoneId.systemDefault()).toLocalDate().format(dateTimeFormatter);
    }
}
