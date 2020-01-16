package com.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import com.constant.Constants;
import com.main.app.JenkinsRunner;
import com.report.GenerateReport;
import com.utility.FileUtils;
import com.utility.ImageComparatorWorker;

public class ImageService {
	
	private String totalImages;
	private String totalDiffImages;
	private String totalNotDiffImages;
	private final int MAX_THREAD = 10;
	private static ImageService instance;
	private ArrayList<String> listOfCommonImgs = FileUtils.instance().commonImages();
	
	
	public static ImageService getInstance() {
		if (instance == null) {
			instance = new ImageService();
		}
		return instance;
	}

	public void checkDifferenceBetweenImages(String directory1, String directory2, String outputDirectory, String imageType) {
		FileUtils.instance().copyFilesOneToOtherDir(directory1, Constants.instance().TEMP_FOLDER1);
		FileUtils.instance().copyFilesOneToOtherDir(directory2, Constants.instance().TEMP_FOLDER2);
		ArrayList<String> list1Images = null;
		ArrayList<String> list2Images = null;
		int sizeOfDirectory1 = FileUtils.instance().sizeOfDirectory(Constants.instance().TEMP_FOLDER1);
		int sizeOfDirectory2= FileUtils.instance().sizeOfDirectory(Constants.instance().TEMP_FOLDER2);
		try {
			if (sizeOfDirectory1 > 0 & sizeOfDirectory2 > 0) {
				FileUtils.instance().moveFilesByRemovingDigBrackUsingDefRegex(FileUtils.instance().listOfFiles(Constants.instance().TEMP_FOLDER1), Constants.instance().RENAMED_FOLDER1);
				FileUtils.instance().moveFilesByRemovingDigBrackUsingDefRegex(FileUtils.instance().listOfFiles(Constants.instance().TEMP_FOLDER2), Constants.instance().RENAMED_FOLDER2);
				list1Images = FileUtils.instance().listOfNames(Constants.instance().RENAMED_FOLDER1);
				list2Images = FileUtils.instance().listOfNames(Constants.instance().RENAMED_FOLDER2);
				FileUtils.instance().findComAndUnComImgFromLists(list1Images, list2Images);
				FileUtils.instance().destoryObject(list1Images);
				FileUtils.instance().destoryObject(list2Images);
				if (listOfCommonImgs.size() > 0) {
					Collections.sort(listOfCommonImgs);
					FileUtils.instance().printTextOnSameLine("Loading and Comparing screenshots : ");
					ExecutorService executorService = Executors.newFixedThreadPool(MAX_THREAD);
					for (int index = 1; index < listOfCommonImgs.size()-1; index++) {	
						Runnable worker1 = new ImageComparatorWorker(Constants.instance().RENAMED_FOLDER1.concat(Constants.instance().SINGLE_FORWARD_SLASH).concat(listOfCommonImgs.get(index)), Constants.instance().RENAMED_FOLDER2.concat(Constants.instance().SINGLE_FORWARD_SLASH).concat(listOfCommonImgs.get(index)), outputDirectory, imageType);
						Thread.sleep(50);
						executorService.execute(worker1);
						FileUtils.instance().printTextOnSameLine(index+" ");
					}
					executorService.shutdown();
					while (!executorService.isTerminated()) {}
					Thread.sleep(1000);
					if(JenkinsRunner.writeUnCommonImage())
						FileUtils.instance().writeUnCommonImagesInReport();
					int differencecounter = FileUtils.instance().sizeOfDirectory(outputDirectory) + (FileUtils.instance().unCommonImages1().size() + FileUtils.instance().unCommonImages2().size());
					int totalImgCount = listOfCommonImgs.size() + FileUtils.instance().unCommonImages1().size() + FileUtils.instance().unCommonImages2().size();
					totalImages = String.valueOf(totalImgCount);
					totalDiffImages = String.valueOf(differencecounter);
					totalNotDiffImages = String.valueOf(totalImgCount - differencecounter);
					FileUtils.instance().printText(Constants.instance().NEW_LINE + Constants.instance().NEW_LINE + "Total compared images are  					: " + totalImages + Constants.instance().NEW_LINE);
					FileUtils.instance().printText("Total images, where we have found the differences are  		: " + totalDiffImages + Constants.instance().NEW_LINE);
					FileUtils.instance().printText("Total images, where we have not found any differences are  	: " + totalNotDiffImages + Constants.instance().NEW_LINE);
					GenerateReport.instance().generateDataforReportChart(totalImages, totalDiffImages, totalNotDiffImages);
					if (differencecounter > 0) {
						FileUtils.instance().printText(Constants.instance().NEW_LINE + Constants.instance().STAR);
						FileUtils.instance().printText(Constants.instance().NEW_LINE + "Found the difference while comparing the images. So, conflicted images are placed at below mentioned path : " + Constants.instance().NEW_LINE + Constants.instance().NEW_LINE + FileUtils.instance().absolutePath(outputDirectory) + Constants.instance().NEW_LINE);
						FileUtils.instance().printText(Constants.instance().STAR);
					}
				}
			}
		} catch (Exception e) {
			FileUtils.instance().printText("Please give the correct folder's path where the images exists... Thank You !!" + Constants.instance().NEW_LINE + Constants.instance().NEW_LINE + e.getClass() + Constants.instance().Arrow + e.getLocalizedMessage());
		}
		GenerateReport.instance().generateReport();
	}	
}
