package com.qa.portal.cv.util.pdf;

import java.io.IOException;

import rst.pdfbox.layout.elements.Frame;
import rst.pdfbox.layout.elements.Paragraph;
import rst.pdfbox.layout.text.Position;

public class CvPdfElement {
	private Paragraph paragraph;
	private Frame frame;
		
	public CvPdfElement(Float width, Float height, Float xPosition, Float yPosition) throws IOException {
		super();
		float padding = PageFormat.PADDING.value;
		this.paragraph = new Paragraph();
		this.frame = new Frame(this.paragraph, width, height);
		this.frame.setAbsolutePosition(new Position(xPosition, yPosition));
		this.frame.setPadding(padding, padding, padding, padding);
		this.paragraph.setMaxWidth(this.frame.getWidth() - (padding*2));
	}

	public Paragraph getParagraph() {
		return paragraph;
	}

	public Frame getFrame() {
		return frame;
	}
}
