package com.garbage;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import com.constant.Constants;
import com.report.GenerateReport;
import com.utility.FileUtils;
import com.utility.ImageComparatorWorker;

public class ImageService {/*
	static long startTime;
	static long endTime;
	static long beforeUsedMemory;
	static long afterUsedMemory;
	private String totalImages;
	private String totalDiffImages;
	private String totalNotDiffImages;
	private final int MAX_THREAD = 10;
	private static ImageService instance;
	private Set<String> unCommonImages1 = FileUtils.getinstance().unCommonImages1();
	private Set<String> unCommonImages2 = FileUtils.getinstance().unCommonImages2();
	private ArrayList<String> imageNamesList = FileUtils.getinstance().imageNamesList();
	
	public static ImageService getInstance() {
		if (instance == null) {
			instance = new ImageService();
		}
		return instance;
	}

	public void checkDifferenceBetweenImages(String folder1, String folder2, String outputFolder, String imageType) {
		FileUtils.getinstance().copyFilesFromOneDirToAnotherDir(folder1, Constants.TEMP_FOLDER1);
		FileUtils.getinstance().copyFilesFromOneDirToAnotherDir(folder2, Constants.TEMP_FOLDER2);
		ArrayList<String> imageFileArray1 = null;
		ArrayList<String> imageFileArray2 = null;
		int folder1ListSize = FileUtils.getinstance().getSizeOfFiles(Constants.TEMP_FOLDER1);
		int folder2ListSize = FileUtils.getinstance().getSizeOfFiles(Constants.TEMP_FOLDER2);
		try {
			if (folder1ListSize > 0 & folder2ListSize > 0) {
				FileUtils.getinstance().removeDigitsBracketsUsingDefaultRegexFromFiles(FileUtils.getinstance().getFileList(Constants.TEMP_FOLDER1), Constants.RENAMED_FOLDER1);
				FileUtils.getinstance().removeDigitsBracketsUsingDefaultRegexFromFiles(FileUtils.getinstance().getFileList(Constants.TEMP_FOLDER2), Constants.RENAMED_FOLDER2);
				imageFileArray1 = FileUtils.getinstance().getFilesNameInList(Constants.RENAMED_FOLDER1);
				imageFileArray2 = FileUtils.getinstance().getFilesNameInList(Constants.RENAMED_FOLDER2);				
				FileUtils.getinstance().findCommonAndUnCommonImgFromList(imageFileArray1, imageFileArray2);
				FileUtils.getinstance().destoryObject(imageFileArray1);
				FileUtils.getinstance().destoryObject(imageFileArray2);
				if (imageNamesList.size() > 0) {
					Collections.sort(imageNamesList);
					FileUtils.getinstance().printTextOnSameLine("Loading and Comparing screenshots : ");
					ExecutorService executorService = Executors.newFixedThreadPool(MAX_THREAD);
					beforeUsedMemory = (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory())/1000000;
					startTime = System.currentTimeMillis()/1000;
					for (int index = 1; index < imageNamesList.size()-1; index++) {	
						Runnable worker1 = new ImageComparatorWorker(Constants.RENAMED_FOLDER1.concat(Constants.DOUBLE_BACKWARD_SLASH).concat(imageNamesList.get(index)), Constants.RENAMED_FOLDER2.concat(Constants.DOUBLE_BACKWARD_SLASH).concat(imageNamesList.get(index)), outputFolder, imageType);
						Thread.sleep(50);
						executorService.execute(worker1);
						FileUtils.getinstance().printTextOnSameLine(index+" ");
					}
					executorService.shutdown();
					 while (!executorService.isTerminated()) { }
					 endTime = System.currentTimeMillis()/1000;
					 afterUsedMemory = (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory())/1000000;
					 System.out.println(Constants.NEW_LINE+"Before Used Memory : "+ beforeUsedMemory +" and after used Memory : " +afterUsedMemory);
					 System.out.println("Total consumed Memory is : "+(afterUsedMemory - beforeUsedMemory));
					 System.out.println("Start Time is : " +startTime +" and End Time is : " +endTime);
					 System.out.println("Total Time Is : " + (endTime - startTime));
					Thread.sleep(1000);
					FileUtils.getinstance().writeUnCommonImagesInReport(unCommonImages1, unCommonImages2);
					int differencecounter = (new File(outputFolder).list().length) + (unCommonImages1.size() + unCommonImages2.size());
					int totalImgCount = imageNamesList.size() + unCommonImages1.size() + unCommonImages2.size();
					totalImages = String.valueOf(totalImgCount);
					totalDiffImages = String.valueOf(differencecounter);
					totalNotDiffImages = String.valueOf(totalImgCount - differencecounter);
					FileUtils.getinstance().printText(Constants.NEW_LINE + Constants.NEW_LINE + "Total compared images are  					: " + totalImages + Constants.NEW_LINE);
					FileUtils.getinstance().printText("Total images, where we have found the differences are  		: " + totalDiffImages + Constants.NEW_LINE);
					FileUtils.getinstance().printText("Total images, where we have not found any differences are  	: " + totalNotDiffImages + Constants.NEW_LINE);
					GenerateReport.instance().generateDataforReportChart(totalImages, totalDiffImages, totalNotDiffImages);
					if (differencecounter > 0) {
						FileUtils.getinstance().printText(Constants.NEW_LINE + Constants.STAR);
						FileUtils.getinstance().printText(Constants.NEW_LINE + "Found the difference while comparing the images. So, conflicted images are placed at below mentioned path : " + Constants.NEW_LINE + Constants.NEW_LINE + FileUtils.getinstance().getAbsolutePathOfFile(outputFolder) + Constants.NEW_LINE);
						FileUtils.getinstance().printText(Constants.STAR);
					}
				}
			}
		} catch (Exception e) {
			FileUtils.getinstance().printText("Please give the correct folder's path where the images exists... Thank You !!" + Constants.NEW_LINE + Constants.NEW_LINE + e.getClass() + Constants.Arrow + e.getLocalizedMessage());
		}
		GenerateReport.instance().generateReport();
	}	
*/}
