package com.qa.portal.cv.util;

import java.awt.Color;
import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.time.LocalTime;

import com.qa.portal.common.exception.QaPortalSevereException;
import com.qa.portal.cv.domain.CvVersion;

import org.apache.pdfbox.io.IOUtils;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import rst.pdfbox.layout.elements.Document;
import rst.pdfbox.layout.elements.Frame;
import rst.pdfbox.layout.elements.Paragraph;
import rst.pdfbox.layout.elements.render.ColumnLayout;
import rst.pdfbox.layout.shape.Stroke;
import rst.pdfbox.layout.text.Alignment;
import rst.pdfbox.layout.text.Position;

@Component
public class CvPdfGeneratorImpl implements CvPdfGenerator {

    @Override
	public byte[] generateCv(CvVersion cvVersion) {
        Resource res = new ClassPathResource("generatedCv-versiondate-"+ LocalDateTime.now() + ".pdf");
        try{
		
            Document document = new Document();
            float heightBox1 = document.getPageHeight()/6;
            float heightBox2 = (float) 2.5*document.getPageHeight()/6;
            float widthCol1 = document.getPageWidth()/3;	
            float widthCol2 = 2*document.getPageWidth()/3;
            float heightBox2_1 = document.getPageHeight()/36;
            float heightBox2_2 = document.getPageHeight() - heightBox2_1*2;
            
            Frame frame;
            Paragraph paragraph;

            PDFont mainFont = PDType1Font.HELVETICA;
            
            document.add(new ColumnLayout(2, 0));
            
            //column 1 box 1
            paragraph = new Paragraph();
            paragraph.addText("Name Smith", 20, mainFont);
            frame = new Frame(paragraph, widthCol1, heightBox1);
            frame.setBorder(Color.black, new Stroke());
            frame.setBackgroundColor(Color.pink);
            frame.setAbsolutePosition(new Position(0, document.getPageHeight()));
            document.add(frame);
            
            //column 1 box 2
            paragraph = new Paragraph();
            paragraph.addText("Programming Languages", 12, mainFont);
            frame = new Frame(paragraph, widthCol1, heightBox2);
            frame.setBorder(Color.black, new Stroke());
            frame.setAbsolutePosition(new Position(0, document.getPageHeight()-heightBox1));
            document.add(frame);
            
            //column 1 box 3
            paragraph = new Paragraph();
            paragraph.addText("Qualification", 12, mainFont);
            frame = new Frame(paragraph, widthCol1, heightBox2);
            frame.setBorder(Color.black, new Stroke());
            frame.setAbsolutePosition(new Position(0, document.getPageHeight()-heightBox1-heightBox2));
            document.add(frame);
            document.add(ColumnLayout.NEWCOLUMN);
            int pd = 20;
            
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
            paragraph.addText("I chose to study Physics in order to develop my mathematical ability within a subject I find deeply interesting. After graduating, the IT industry is where I felt I could best apply my learned skillset, including problem-solving and logical thinking, while keeping within a field of personal interest.\n\nBoth in and out of the workplace, I thrive to discover new things. This", 12, mainFont);
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

            return IOUtils.toByteArray(res.getInputStream());

        } catch (IOException e){

            e.printStackTrace();
            throw new QaPortalSevereException("Cannot load file");

        }
        
	}
}