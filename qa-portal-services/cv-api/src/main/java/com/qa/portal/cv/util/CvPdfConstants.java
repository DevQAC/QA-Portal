package com.qa.portal.cv.util;

public enum CvPdfConstants {

	// QA Colour Scheme
	QA_BLUE("#004050"), QA_PURPLE("#7F007D"), QA_RED("#FF004C"), QA_GREY("#565759"),

	montserratFile("Montserrat-Regular.ttf"), montserratBoldFile("Montserrat-SemiBold.ttf"),
	kranaFatBFile("Krana-Fat-B.ttf"),

	arrowFile("target/classes/Arrow.png"), logoFile("target/classes/QA_Logo.png");

	// // Font Sizes and Spacing
//    int pd = 20;
//    int sideBarHeaderFontSize = 20;
//    int sideBarTitleFontSize = 11;
//    int sideBarListsFontSize = 10;
//    int sideTitleListSpacing = 4;
//    int bodyHeadingsFontSize = 12;
//    int bodyParagraphFontSize = 9;
//    int bodyHeadingParaSpacing = 4;

	final String value;
//	public final int intValue;

	CvPdfConstants(String value) {
		this.value = value;
//		this.intValue = intValue;
	}

}
