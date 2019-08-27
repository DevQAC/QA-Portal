package com.qa.portal.cv.util;

import java.awt.Color;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDateTime;

import com.qa.portal.common.exception.QaPortalSevereException;
import com.qa.portal.cv.domain.CvVersion;

import org.apache.pdfbox.io.IOUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import rst.pdfbox.layout.elements.Document;
import rst.pdfbox.layout.elements.Frame;
import rst.pdfbox.layout.elements.ImageElement;
import rst.pdfbox.layout.elements.Paragraph;
import rst.pdfbox.layout.elements.render.ColumnLayout;
import rst.pdfbox.layout.shape.Stroke;
import rst.pdfbox.layout.text.Alignment;
import rst.pdfbox.layout.text.BaseFont;
import rst.pdfbox.layout.text.Position;

@Component
public class CvPdfGeneratorImpl implements CvPdfGenerator {

    String firstname = "Scott";
    String lastname = "Smith";
    String jobTitle = "Consultant";
    String profile = "I chose to study Physics in order to develop my mathematical ability within a subject I find deeply interesting. After graduating, the IT industry is where I felt I could best apply my learned skillset, including problem-solving and logical thinking, while keeping within a field of personal interest.\\n\\nBoth in and out of the workplace, I thrive to discover new things. This";
    
    String[] programmingLanguages = {"Dummy", "Data", "Test", "Java(Maven, JUnit, Cucumber, Selenium)", "JavaScript"};
    String[] IDEs = {"eclipse", "sheep", "Test"};
    String[] operatingSystems = {"Windows", "Linux", "Hack"};
    String[] devOpsTech = {"Jenkins", "Git", "The"};
    String[] databaseTech = {"MySQL", "MongoDB", "Black"};
    String[] projectFrameWorks = {"Agile", "Kanban", "Car"};
    String[] other = {"Postman", "Life", "Test"};
    
    String[] qualifications = {"BSc (hons) Physics with Theoretical Physics, King’s College London, 2018", "Debt"};	
    
    
    PDFont mainFont = PDType1Font.HELVETICA;
    BaseFont baseFont = BaseFont.Helvetica;
    
    Document document = new Document();
    PDDocument pdDoc = new PDDocument();
    
    int pd = 20;
    
    float heightBox1 = document.getPageHeight()/6;
    float heightBox2 = (float) 2.5*document.getPageHeight()/6;
    float widthCol1 = document.getPageWidth()/3;	
    float widthCol2 = 2*document.getPageWidth()/3;
    float heightBox2_1 = document.getPageHeight()/36;
    float heightBox2_2 = document.getPageHeight() - heightBox2_1*2;
    
    Frame frame;
    Paragraph paragraph;

    public void box1_2(Paragraph paragraph, String title, String[] list) throws IOException {		
		paragraph.addMarkup("{color:#FFFFFF}*"+title+"*\n", 12, baseFont);
		for(int i=0; i<list.length; i++) {
			if(i<list.length-1) {
				paragraph.addMarkup("{color:#FFFFFF}" + list[i]+", ", 10, baseFont);
			} else {
				paragraph.addMarkup("{color:#FFFFFF}" + list[i]+"\n\n", 10, baseFont);
			}			
		}		
    }

    @Override
	public byte[] generateCv(CvVersion cvVersion) {
        //Resource res = new ClassPathResource("generatedCv-versiondate-"+LocalDateTime.now()+".pdf");
        Resource res = new ClassPathResource("generatedCv.pdf");
        try{
            //column 1 box 1
            paragraph = new Paragraph();
            paragraph.addMarkup("{color:#FFFFFF}*" + firstname + "\n" + lastname + "*", 20, baseFont);
            paragraph.addMarkup("{color:#80007D} \n*" + jobTitle + "*", 20, baseFont);
            frame = new Frame(paragraph, widthCol1, heightBox1);
            frame.setBackgroundColor(Color.decode("#FF004C"));
            frame.setAbsolutePosition(new Position(0, document.getPageHeight()));
            frame.setPadding(pd, pd, pd, pd);
            document.add(frame);
            //ImageElement image = new ImageElement("qaArrow.png");
            //image.setWidth(40);
            //image.setHeight(20);
            //image.setAbsolutePosition(new Position(pd, document.getPageHeight()-heightBox1+pd+20));
            //document.add(image);
            
            //column 1 box 2
            paragraph = new Paragraph();
            box1_2(paragraph, "Programming Languages", programmingLanguages);
            box1_2(paragraph, "IDE's", IDEs);
            box1_2(paragraph, "Operating Systems", operatingSystems);
            box1_2(paragraph, "DevOps Technologies", devOpsTech);
            box1_2(paragraph, "Database Technologies", databaseTech);
            box1_2(paragraph, "Project Frameworks", projectFrameWorks);
            box1_2(paragraph, "Other", other);
            
            frame = new Frame(paragraph, widthCol1, heightBox2);
            frame.setBackgroundColor(Color.decode("#80007D"));
            frame.setAbsolutePosition(new Position(0, document.getPageHeight()-heightBox1));
            paragraph.setMaxWidth(frame.getWidth()-(pd*2));
            frame.setPadding(pd, pd, pd, pd);
            document.add(frame);
            
            //column 1 box 3
            paragraph = new Paragraph();
            paragraph.addMarkup("{color:#FFFFFF}*Qualification*", 12, baseFont);
            for (String i : qualifications) {
                paragraph.addMarkup("\n\n{color:#FFFFFF}"+i, 10, baseFont);
            }
            frame = new Frame(paragraph, widthCol1, heightBox2);
            frame.setBackgroundColor(Color.decode("#004050"));
            frame.setAbsolutePosition(new Position(0, document.getPageHeight()-heightBox1-heightBox2));
            paragraph.setMaxWidth(frame.getWidth()-(pd*2));
            frame.setPadding(pd, pd, pd, pd);
            document.add(frame);

            //column 2 box 1
            paragraph = new Paragraph();
            paragraph.addText("Consultant Profile", 12, mainFont);
            paragraph.setAlignment(Alignment.Right);
            frame = new Frame(paragraph, widthCol2-pd*2, heightBox2_1);
            frame.setBorder(Color.black, new Stroke());
            frame.setAbsolutePosition(new Position(widthCol1, document.getPageHeight()));
            paragraph.setMaxWidth(frame.getWidth());
            frame.setMargin(pd, 0, 0, 0);
            frame.setPaddingTop(5);
            document.add(frame);
            
            //column 2 box 2 
            paragraph = new Paragraph();
            paragraph.addText("PROFILE\n", 16, mainFont);
            paragraph.addMarkup("{color:#393936}I chose to study Physics in order to develop my mathematical ability within a subject I find deeply interesting. After graduating, the IT industry is where I felt I could best apply my learned skillset, including problem-solving and logical thinking, while keeping within a field of personal interest.\n\nBoth in and out of the workplace, I thrive to discover new things. This", 12, baseFont);
            frame = new Frame(paragraph, widthCol2, heightBox2_2);
            frame.setBorder(Color.black, new Stroke());
            frame.setAbsolutePosition(new Position(widthCol1, document.getPageHeight()-heightBox2_1));
            paragraph.setMaxWidth(frame.getWidth()-(pd*2));
            frame.setPadding(pd, pd, pd, pd);
            document.add(frame);
            
            //column 2 box 3
            paragraph = new Paragraph();
            paragraph.addText("+44 1273 022670 / qa.com", 12, mainFont);
            paragraph.setAlignment(Alignment.Right);
            frame = new Frame(paragraph, widthCol2-pd*2, heightBox2_1);
            frame.setBorder(Color.black, new Stroke());
            frame.setAbsolutePosition(new Position(widthCol1, heightBox2_1));
            paragraph.setMaxWidth(frame.getWidth());
            frame.setMargin(pd, 0, 0, 0);
            frame.setPaddingTop(5);
            document.add(frame);

            final OutputStream outputStream = new FileOutputStream("src/main/resources/generatedCv.pdf");
            document.save(outputStream);

            return IOUtils.toByteArray(res.getInputStream());

        } catch (IOException e){

            e.printStackTrace();
            throw new QaPortalSevereException("Cannot load file");

        }
    }
}