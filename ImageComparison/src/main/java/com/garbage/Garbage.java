package com.garbage;

import java.io.File;
import java.util.Collections;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.constant.Constants;
import com.report.GenerateReport;
import com.utility.FileUtils;
import com.utility.ImageComparatorWorker;

public class Garbage {

/*	public void checkDifferenceBetweenImages(String folder1, String folder2, String outputFolder, String imageType) {
		FileUtils.copyFilesFromOneDirToAnotherDir(folder1, Constants.TEMP_FOLDER1);
		FileUtils.copyFilesFromOneDirToAnotherDir(folder2, Constants.TEMP_FOLDER2);
		File[] imageFileArray1;
		File[] imageFileArray2;
		int folder1ListSize = FileUtils.getSizeOfFiles(Constants.TEMP_FOLDER1);
		int folder2ListSize = FileUtils.getSizeOfFiles(Constants.TEMP_FOLDER2);
		try {
			if (folder1ListSize > 0 & folder2ListSize > 0) {
				FileUtils.removeDigitsBracketsUsingDefaultRegexFromFiles(FileUtils.getFileList(Constants.TEMP_FOLDER1), Constants.RENAMED_FOLDER1);
				FileUtils.removeDigitsBracketsUsingDefaultRegexFromFiles(FileUtils.getFileList(Constants.TEMP_FOLDER2), Constants.RENAMED_FOLDER2);
				if (FileUtils.getSizeOfFiles(Constants.RENAMED_FOLDER1) > FileUtils.getSizeOfFiles(Constants.RENAMED_FOLDER2)) {
					imageFileArray1 = FileUtils.getFileList(Constants.RENAMED_FOLDER1);
					imageFileArray2 = FileUtils.getFileList(Constants.RENAMED_FOLDER2);
					removeExtraImagesFromFolder1(imageFileArray1, imageFileArray2);
				} else if (FileUtils.getSizeOfFiles(Constants.RENAMED_FOLDER1) < FileUtils.getSizeOfFiles(Constants.RENAMED_FOLDER2) | FileUtils.getSizeOfFiles(Constants.RENAMED_FOLDER1) == FileUtils.getSizeOfFiles(Constants.RENAMED_FOLDER2)) {
					imageFileArray1 = FileUtils.getFileList(Constants.RENAMED_FOLDER1);
					imageFileArray2 = FileUtils.getFileList(Constants.RENAMED_FOLDER2);
					removeExtraImagesFromFolder2(imageFileArray1, imageFileArray2);
				}
				if (imageNamesList.size() > 0) {
					Collections.sort(imageNamesList);
					FileUtils.printTextOnSameLine("Comparing screenshots.... ");
					ExecutorService executorService = Executors.newFixedThreadPool(10);
					startTime = System.currentTimeMillis()/1000;
					for (int index = 0; index < imageNamesList.size(); index++) {
						Runnable worker = new ImageComparatorWorker(Constants.RENAMED_FOLDER1.concat(Constants.DOUBLE_BACKWARD_SLASH).concat(imageNamesList.get(index)), Constants.RENAMED_FOLDER2.concat(Constants.DOUBLE_BACKWARD_SLASH).concat(imageNamesList.get(index)), outputFolder, imageType);
						Thread workerThread = new Thread(worker);
						workerThread.start();
						Thread.sleep(50);
						
						Runnable worker1 = new ImageComparatorWorker(Constants.RENAMED_FOLDER1.concat(Constants.DOUBLE_BACKWARD_SLASH).concat(imageNamesList.get(index)), Constants.RENAMED_FOLDER2.concat(Constants.DOUBLE_BACKWARD_SLASH).concat(imageNamesList.get(index)), outputFolder, imageType);
						Thread.sleep(50);
						executorService.execute(worker1);
						FileUtils.printTextOnSameLine(Constants.DOT + index);
					}
					executorService.shutdown();
					 while (!executorService.isTerminated()) {   }
					 	endTime = System.currentTimeMillis()/1000;
						System.out.println("Start Time is : " +startTime +" and End Time is : " +endTime);
						System.out.println("Total Time Is : " + (endTime - startTime));
					Thread.sleep(5000);
					int differencecounter = new File(outputFolder).list().length;
					FileUtils.printText(Constants.NEW_LINE + Constants.NEW_LINE + "Total compared images are  					: " + String.valueOf(imageNamesList.size()) + Constants.NEW_LINE);
					FileUtils.printText("Total images, where we have found the differences are  		: " + String.valueOf(differencecounter) + Constants.NEW_LINE);
					FileUtils.printText("Total images, where we have not found any differences are  	: " + String.valueOf(imageNamesList.size() - differencecounter) + Constants.NEW_LINE);
					GenerateReport.instance().generateDataforReportChart(String.valueOf(imageNamesList.size()), String.valueOf(differencecounter), String.valueOf(imageNamesList.size() - differencecounter));
					if (differencecounter > 0) {
						FileUtils.printText(Constants.NEW_LINE + Constants.STAR);
						FileUtils.printText(Constants.NEW_LINE + "Found the difference while comparing the images. So, conflicted images are placed at below mentioned path : " + Constants.NEW_LINE + Constants.NEW_LINE + FileUtils.getAbsolutePathOfFile(outputFolder) + Constants.NEW_LINE);
						FileUtils.printText(Constants.STAR);
					}
				}
			}
		} catch (Exception e) {
			FileUtils.printText("Please give the correct folder's path where the images exists... Thank You !!" + Constants.NEW_LINE + Constants.NEW_LINE + e.getClass() + Constants.Arrow + e.getLocalizedMessage());
		}
		GenerateReport.instance().generateReport();
	}*/
}
