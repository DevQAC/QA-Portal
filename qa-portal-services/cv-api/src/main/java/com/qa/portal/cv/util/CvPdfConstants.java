package com.qa.portal.cv.util;

enum ColourScheme {
	QA_BLUE("#004050"), 
	QA_PURPLE("#7F007D"), 
	QA_RED("#FF004C"), 
	QA_GREY("#565759");
	final String value;
	ColourScheme(String value) {
		this.value = value;
	}
}

enum Fonts {
	MONTSERRAT_FILE("Montserrat-Regular.ttf"), 
	MONTSERRAT_BOLD_FILE("Montserrat-SemiBold.ttf"), 
	KRANA_FAT_B_FILE("Krana-Fat-B.ttf");
	final String value;
	Fonts(String value) {
		this.value = value;
	}	
}

enum Images {
	ARROW(
			"target/classes/Arrow.png", 
			35, 
			PageFormat.PADDING.value, 
			PageFormat.HEIGHT.value - ConsultantNameBox.HEIGHT.value + PageFormat.PADDING.value + 20
		),
			
	LOGO(
			"target/classes/QA_Logo.png",
			37,
			PageFormat.WIDTH.value - PageFormat.PADDING.value,
			MainFooter.HEIGHT.value + MainBody.HEIGHT.value + 4
		);
	
	final String filePath;
	final float resizeFactor;
	final float xPosition;
	final float yPosition;
	Images(String FilePath, float resizeFactor, float xPosition, float yPosition) {
		this.filePath = FilePath;
		this.resizeFactor = resizeFactor;
		this.xPosition = xPosition;
		this.yPosition = yPosition;
	}	
}

enum FontSize {
	CONSULTANT_NAME(20),
	SIDEBAR_TITLES(11),
	SIDEBAR_CONTENT(10),
	SIDEBAR_TITLE_CONTENT_SPACING(4),
	BODY_TITLES(12),
	BODY_CONTENT(9),
	BODY_TITLE_CONTENT_SPACING(5),
	BODY_SUBTITLE_CONTENT_SPACING(4);
	final int value;
	FontSize(int value) {
		this.value = value;
	}
}

enum PageFormat {
	WIDTH(595.27563f),
	HEIGHT(841.8898f),
	WIDTH_COL1(WIDTH.value/3 + 4.5f),
	WIDTH_COL2(WIDTH.value - WIDTH_COL1.value),
	PADDING(20);
	final float value;
	PageFormat(float value) {
		this.value = value;
	}
}

enum ConsultantNameBox {
	WIDTH(PageFormat.WIDTH_COL1.value),
	HEIGHT(PageFormat.HEIGHT.value/6 + 4),
	X_POSITION(0),
	Y_POSITION(PageFormat.HEIGHT.value);
	final float value;
	ConsultantNameBox(float value) {
		this.value = value;
	}
}

enum SkillsBox {
	WIDTH(PageFormat.WIDTH_COL1.value),
	HEIGHT(2.5f*PageFormat.HEIGHT.value/6 + 7),
	X_POSITION(0),
	Y_POSITION(PageFormat.HEIGHT.value - ConsultantNameBox.HEIGHT.value);
	final float value;
	SkillsBox(float value) {
		this.value = value;
	}
}

enum QualificationsBox {
	WIDTH(PageFormat.WIDTH_COL1.value),
	HEIGHT(PageFormat.HEIGHT.value - ConsultantNameBox.HEIGHT.value - SkillsBox.HEIGHT.value),
	X_POSITION(0),
	Y_POSITION(HEIGHT.value);
	final float value;
	QualificationsBox(float value) {
		this.value = value;
	}
}

enum MainHeader {
	WIDTH(PageFormat.WIDTH_COL2.value),
	HEIGHT(PageFormat.HEIGHT.value / 20 - 8),
	X_POSITION(PageFormat.WIDTH_COL1.value),
	Y_POSITION(PageFormat.HEIGHT.value);
	final float value;
	MainHeader(float value) {
		this.value = value;
	}
}

enum MainFooter {
	WIDTH(PageFormat.WIDTH_COL2.value),
	HEIGHT(PageFormat.HEIGHT.value / 20 - 8),
	X_POSITION(PageFormat.WIDTH_COL1.value),
	Y_POSITION(HEIGHT.value);
	final float value;
	MainFooter(float value) {
		this.value = value;
	}
}

enum MainBody {
	WIDTH(PageFormat.WIDTH_COL2.value),
	HEIGHT(PageFormat.HEIGHT.value - MainHeader.HEIGHT.value - MainFooter.HEIGHT.value),
	X_POSITION(PageFormat.WIDTH_COL1.value),
	Y_POSITION(PageFormat.HEIGHT.value - MainHeader.HEIGHT.value);
	final float value;
	MainBody(float value) {
		this.value = value;
	}
}

enum Dividers {
	WIDTH(PageFormat.WIDTH_COL2.value - PageFormat.PADDING.value*2),
	HEIGHT(0.5f),
	X_POSITION(PageFormat.WIDTH_COL1.value + PageFormat.PADDING.value),
	Y_POSITION_TOP(PageFormat.HEIGHT.value - MainHeader.HEIGHT.value),
	Y_POSITION_BOTTOM(MainFooter.HEIGHT.value);
	final float value;
	Dividers(float value) {
		this.value = value;
	}
}




