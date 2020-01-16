package com.main.app;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import com.constant.Constants;
import com.service.ImageService;
import com.utility.FileUtils;

public class JenkinsRunner {
		
	public static Properties prop = new Properties();
	private static boolean isMobile = false;
	private static boolean writeUnCommonImage = false;

	public static void main(String[] args) throws IOException {
		prop.load(new FileReader(Constants.instance().PROPERTIES_FILE));
		isMobile = Boolean.parseBoolean(JenkinsRunner.prop.get("isMobile").toString());
		writeUnCommonImage = Boolean.parseBoolean(JenkinsRunner.prop.get("writeUnCommonImage").toString());
		String folderPath1 = prop.getProperty("folder1");
		String folderPath2 = prop.getProperty("folder2");
		String outputFolderPath = prop.getProperty("outputFolder");
		String imageType = prop.getProperty("imageType");
		boolean isCrop = Boolean.valueOf(prop.getProperty("isCrop"));
		int cropWidth = Integer.valueOf(prop.getProperty("cropWidth"));
		int cropHeight = Integer.valueOf(prop.getProperty("cropHeight"));
		if (isCrop) {
			FileUtils.instance().cropimage(folderPath1, folderPath1, cropHeight, cropWidth);
			FileUtils.instance().cropimage(folderPath2, folderPath2, cropHeight, cropWidth);
		}
		FileUtils.instance().cleanDirectories();
		compareImages(folderPath1, folderPath2, outputFolderPath, imageType);
		FileUtils.instance().printText(Constants.instance().NEW_LINE+"Thank You !!!, we hope you have enjoyed..");
	}

	private static void compareImages(String folderPath1, String folderPath2, String outputFolderPath, String imageType) {
		if (!folderPath1.isEmpty() & !folderPath2.isEmpty() & !outputFolderPath.isEmpty() & !imageType.isEmpty()) {
			ImageService imageService = ImageService.getInstance();
			imageService.checkDifferenceBetweenImages(folderPath1, folderPath2, outputFolderPath, imageType);
		} else
			FileUtils.instance().printText("Invalid file path provided.");
	} 
	
	public static boolean isMobile() {
		return isMobile;	
	}
	
	public static boolean writeUnCommonImage() {
		return writeUnCommonImage;
	}

}
