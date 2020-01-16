package com.constant;

public class Constants {
	
	private static Constants constants;
	
	/**
	 * @author Altametrics Inc. : Method will return the Constants class
	 * instance in singleton manner.
	 * 
	 * @return
	 */
	public static Constants instance() {
		if(constants == null) {
			synchronized (Constants.class) {
				if(constants == null)
					constants = new Constants();
			}
		}
		return constants;
	}
	
	public final String BLANKSTR = "";
	public final String DIGIT_REGEX = ">?(\\[)>?([0-9]{0,2})?(\\.)?([0-9]{0,4})?(\\])";
	public final String BRACKET_REGEX = "^([\\(])?(M)?([0-9]{0,4})?([\\)])";
	public final String DOUBLE_BACKWARD_SLASH = "\\";
	public final String SINGLE_FORWARD_SLASH = "/";
	public final String NEW_LINE = "\n";
	public final String One= "1";
	public final String STAR = "*	*	*	*	*	*	*	*	*	*	*	*	*	*";
	public final String DEFAULT_IMAGE_FORMAT = "png";
	public final String Arrow = "-- >";
	public final String DOT = ".";
	public final String RENAMED_FOLDER1 = "TEMP//RENAMED1";
	public final String RENAMED_FOLDER2 = "TEMP//RENAMED2";
	public final String TEMP_FOLDER1 = "TEMP//FOLDER1//";
	public final String TEMP_FOLDER2 = "TEMP//FOLDER2//";
	public final String MUSTECHE_MOBILE_REPORT_PATH = "setup/reportsetup/imageDiffReport_MOB.mustache";
	public final String MUSTECHE_WEB_REPORT_PATH = "setup/reportsetup/imageDiffReport_WEB.mustache";
	public final String COMPARISONREPORT = "REPORT/COMPARISON_REPORT/IMAGE_DIFF.html";
	public final String ACTUALIMAGES = "REPORT/COMPARISON_REPORT/SNAPSHOTS/ACTUAL/";
	public final String EXPECTEDIMAGES = "REPORT/COMPARISON_REPORT/SNAPSHOTS/EXPECTED/";
	public final String DIFFERENCEIMAGES = "REPORT/COMPARISON_REPORT/SNAPSHOTS/DIFF/";
	public final String OUTPUT_FOLDER = "output/";
	public final String PROPERTIES_FILE = "input.properties";
	public final String FOLDER1 = "Folder1";
	public final String FOLDER2 = "Folder2";
	public final String DASH = "-";
	public final String EXTRA_IMG_1 = "REPORT/COMPARISON_REPORT/SNAPSHOTS/EXTRAIMAGES_1/";
	public final String EXTRA_IMG_2 = "REPORT/COMPARISON_REPORT/SNAPSHOTS/EXTRAIMAGES_2/";
	public static int counter=1;
	public final String NOIMG = "scripts/noImg.png";
}
