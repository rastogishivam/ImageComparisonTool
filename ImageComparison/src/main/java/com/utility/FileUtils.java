package com.utility;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import javax.imageio.ImageIO;
import com.constant.Constants;
import com.google.common.io.Files;
import com.main.app.JenkinsRunner;
import com.report.GenerateReport;

import ru.yandex.qatools.ashot.Screenshot;

public class FileUtils {
	
	private ArrayList<String> commonImgList = new ArrayList<String>();
	private Set<String> set1 = new HashSet<String>();
	private Set<String> set2 = new HashSet<String>();
	private static FileUtils fileUtils;
	private String pathOfExtraImgFolder1 = Constants.instance().EXTRA_IMG_1.split(Constants.instance().SINGLE_FORWARD_SLASH)[2].concat(Constants.instance().SINGLE_FORWARD_SLASH + Constants.instance().EXTRA_IMG_1.split(Constants.instance().SINGLE_FORWARD_SLASH)[3] + Constants.instance().SINGLE_FORWARD_SLASH);
	private String pathOfExtraImgFolder2 = Constants.instance().EXTRA_IMG_2.split(Constants.instance().SINGLE_FORWARD_SLASH)[2].concat(Constants.instance().SINGLE_FORWARD_SLASH + Constants.instance().EXTRA_IMG_2.split(Constants.instance().SINGLE_FORWARD_SLASH)[3] + Constants.instance().SINGLE_FORWARD_SLASH);
	
	public Set<String> unCommonImages1() {
		return set1;
	}
	
	public Set<String> unCommonImages2() {
		return set2;
	}
	
	public ArrayList<String> commonImages() {
		return commonImgList;
	}
	
	public void findComAndUnComImgFromLists(ArrayList<String> list1, ArrayList<String> list2) {
		if(list1.size() > list2.size())
			findComAndUncomImgFromList(list1, list2, set1);
		else if(list1.size() < list2.size() | list1.size() == list2.size())
			findComAndUncomImgFromList(list2, list1, set2);
	}
	
	private void findComAndUncomImgFromList(ArrayList<String> directory1, ArrayList<String> directory2, Set<String> unCommonImages) {
		try {
			for(String fileName : directory1) {
				if(directory2.contains(fileName))
					commonImgList.add(fileName);
				else if(JenkinsRunner.writeUnCommonImage())
					unCommonImages.add(fileName);
			}
		}catch (Exception e) {
			FileUtils.instance().printText("The array can't be null or blank.. Please check the array size..." + Constants.instance().NEW_LINE + Constants.instance().NEW_LINE + e.getClass() + Constants.instance().Arrow + e.getLocalizedMessage());
		}
	}
	
	public void writeUnCommonImagesInReport() {
		writeUncommonImagesInReport(set1, set2, pathOfExtraImgFolder1, pathOfExtraImgFolder2);
	}
	
	private void writeUncommonImagesInReport(Set<String> set1OfUnCommonImgs, Set<String> set2OfUnCommonImgs, String path1, String path2) {
		if(set1OfUnCommonImgs.size() > 0) {
			for(String imageName : set1OfUnCommonImgs) {
				FileUtils.instance().copyFilesOneToOtherLoc(FileUtils.instance().absolutePath(Constants.instance().RENAMED_FOLDER1 + Constants.instance().SINGLE_FORWARD_SLASH + imageName), Constants.instance().EXTRA_IMG_1);
				GenerateReport.instance().generateReportData(imagename(imageName), String.valueOf(Constants.counter++), path1 + imageName, Constants.instance().NOIMG, Constants.instance().NOIMG, "0");
			}
		}else if(set2OfUnCommonImgs.size() > 0){
			for(String imageName : set2OfUnCommonImgs) {
				FileUtils.instance().copyFilesOneToOtherLoc(FileUtils.instance().absolutePath(Constants.instance().RENAMED_FOLDER2 + Constants.instance().SINGLE_FORWARD_SLASH + imageName), Constants.instance().EXTRA_IMG_2);
				GenerateReport.instance().generateReportData(imagename(imageName), String.valueOf(Constants.counter++), Constants.instance().NOIMG , path2 + imageName, Constants.instance().NOIMG, "0");
			}
		}
	}
	
	/**
	 * @author Altametrics Inc. : Method will return the FileUtils class
	 * instance in singleton manner.
	 * 
	 * @return
	 */
	public static FileUtils instance() {
		if(fileUtils == null) {
			synchronized (FileUtils.class) {
				if(fileUtils == null)
					fileUtils = new FileUtils();
			}
		}
		return fileUtils;
	}

	/**
	 * @author Altametrics Inc. : Method will return the file name
	 * from the given file path.
	 * 
	 * @param filePath
	 * @return
	 */
	public String filename(String filePath) {
		if (!filePath.isEmpty())
			return new java.io.File(filePath).getName().trim();
		return null;
	}

	/**
	 * @author Altametrics Inc. : Method will return the Absolute file path from
	 * the given file path.
	 * 
	 * @param filePath
	 * @return
	 */
	public String absolutePath(String filePath) {
		if (!filePath.isEmpty()) {
			return new File(filePath).getAbsolutePath();	
		}
		return null;
	}

	/**
	 * @author Altametrics Inc. : Method will return the total no. of files in Array
	 * present in the given path.
	 * 
	 * @param folderPath
	 * @return
	 */
	public File[] listOfFiles(String directory) {
		if (!directory.isEmpty())
			return new File(directory).listFiles();
		return null;
	}
	
	public int sizeOfDirectory(String folderPath) {
		if (!folderPath.isEmpty())
			return new File(folderPath).listFiles().length;
		return 0;
	}

	public Screenshot screenshot(String filePath) {
		BufferedImage bufferedImage = null;
		try {
			bufferedImage = ImageIO.read(new File(filePath));
		} catch (IOException e) {
			printText("ImagePath can't be Blank.., Please specify the correct Images Path..");
		} catch (java.lang.IndexOutOfBoundsException e) {
			printText("java.lang.IndexOutOfBoundsException");
		}
		return new Screenshot(bufferedImage);
	}
	
	public BufferedImage bufferedImage(String filePath) {
		BufferedImage bufferedImage = null;
		try {
			bufferedImage = ImageIO.read(new File(filePath).getAbsoluteFile());
		} catch (IOException e) {
			printText("ImagePath can't be Blank.., Please specify the correct Images Path..");
		} catch (java.lang.IndexOutOfBoundsException e) {
			printText("java.lang.IndexOutOfBoundsException");
		}
		return bufferedImage;
	}
	
	public void writeImage(BufferedImage bufferedImage, String outputFolderPath, String imageFormat, String actualImgFilePath) {
		if (bufferedImage.getWidth() > 0 & bufferedImage.getHeight() > 0 | !outputFolderPath.isEmpty() | !imageFormat.isEmpty() | !actualImgFilePath.isEmpty()) {
			try {
				ImageIO.write(bufferedImage, imageFormat, new File(makeDirectory(outputFolderPath).concat(Constants.instance().DOUBLE_BACKWARD_SLASH) + filename(actualImgFilePath)));
			} catch (IOException e) {
				System.err.println(e.getMessage());
			}
		} else
			printText("Getting the Output Folder Path or Image Format or Actual Image File Path blank. Please check the value for the mentioned fields...");
	}
	
	/**
	 * @author Altametrics Inc. : Method will return the Name from
	 * the file name.
	 * 
	 * @param path
	 * @return
	 */
	public String imagename(String path) {
		File file = new File(path);
		String tempName;
		if(file.exists()) {
			tempName = new File(path).getName();
			return tempName.contains(Constants.instance().DASH) ? tempName.split(Constants.instance().DASH)[0] : tempName.contains(Constants.instance().DEFAULT_IMAGE_FORMAT) ? tempName.split(Constants.instance().DEFAULT_IMAGE_FORMAT)[0] : tempName;
		}else
			return path.contains(Constants.instance().DASH) ? path.split(Constants.instance().DASH)[0] : path.contains(Constants.instance().DEFAULT_IMAGE_FORMAT) ? path.split(Constants.instance().DEFAULT_IMAGE_FORMAT)[0] : path;
	}
	
	public String makeDirectory(String path, String folderName) {
		String path1 = null;
		if (!path.isEmpty()) {
			File file = new File(path.concat(Constants.instance().DOUBLE_BACKWARD_SLASH + folderName));
			if (!file.isDirectory()) {
				file.mkdirs();
				path1 = file.getAbsolutePath();
			} else
				path1 = file.getAbsolutePath();
		}
		return path1;
	}

	public String makeDirectory(String path) {
		String path1 = null;
		File file = new File(path);
		if (!file.isDirectory()) {
			file.mkdirs();
			path1 = file.getAbsolutePath();
		} else
			path1 = file.getAbsolutePath();
		return path1;
	}

	public void cropimage(String folderPath, String outputFolderPath, int height, int width) {
		int counter = 0;
		File file;
		BufferedImage originalImgage = null;
		BufferedImage subImgage;
		File[] imageList = listOfFiles(folderPath);
		printTextOnSameLine("Cropping images present in " + folderPath);
		for (int i = 0; i < imageList.length; i++) {
			file = new File(imageList[i].getAbsolutePath());
			try {
				if (!file.isDirectory()) {
					try {
						originalImgage = ImageIO.read(file);
					} catch (java.lang.IndexOutOfBoundsException e) {
						printText("Corrupted File Name" + file.getAbsolutePath());
					}
					subImgage = originalImgage.getSubimage(width, height, originalImgage.getWidth() - width, originalImgage.getHeight() - height);
					counter++;
					writeImage(subImgage, outputFolderPath, Constants.instance().DEFAULT_IMAGE_FORMAT, removeDigitsBracketsUsingDefaultRegex(filename(file.getAbsolutePath())));
				}
			} catch (IOException e) {
				printText("Please check the files in the given path.. " + Constants.instance().NEW_LINE + e.getClass() + Constants.instance().Arrow + e.getLocalizedMessage());
				e.printStackTrace();
			}
			printTextOnSameLine(Constants.instance().DOT + i);
		}
		printText(Constants.instance().NEW_LINE + Constants.instance().NEW_LINE + "Total cropped images are : " + counter);
	}

	public String removeDigitsBracketsUsingDefaultRegex(String fileName) {
		if(JenkinsRunner.isMobile())
			if(fileName.trim().contains(Constants.instance().DASH))
				return fileName.trim().replaceAll(Constants.instance().DIGIT_REGEX, Constants.instance().BLANKSTR).replaceAll(Constants.instance().BRACKET_REGEX, Constants.instance().BLANKSTR).split(Constants.instance().DASH)[0].concat(Constants.instance().DOT+Constants.instance().DEFAULT_IMAGE_FORMAT).trim();
			else
				return fileName.trim().replaceAll(Constants.instance().DIGIT_REGEX, Constants.instance().BLANKSTR).replaceAll(Constants.instance().BRACKET_REGEX, Constants.instance().BLANKSTR).trim();
		else
			return fileName.trim().replaceAll(Constants.instance().DIGIT_REGEX, Constants.instance().BLANKSTR).replaceAll(Constants.instance().BRACKET_REGEX, Constants.instance().BLANKSTR).trim();	
	}

	public boolean renameFileNameOfSingleFile(File existingfile, String newNameForFile, String outputDirectoryPath, String imageFormat) {
		boolean flag = false;
		if (existingfile.exists()) {
			if (existingfile.renameTo(new File(makeDirectory(outputDirectoryPath).concat(Constants.instance().DOUBLE_BACKWARD_SLASH + newNameForFile.concat(Constants.instance().DOT + imageFormat)))))
				flag = true;
		}
		return flag;
	}

	public void moveFilesByRemovingDigBrackUsingDefRegex(File[] files, String outputDirectoryPath) {
		File targetFileLoc;
		File[] internalTempArray = files;
		String outputFolderpath = makeDirectory(outputDirectoryPath);
		try {
			if (internalTempArray.length > 0)
				for (int index = 0; index < internalTempArray.length; index++) {
					targetFileLoc = new File(outputFolderpath, removeDigitsBracketsUsingDefaultRegex(internalTempArray[index].getName()));
					Files.move(internalTempArray[index], targetFileLoc);
				}
		} catch (Exception e) {
			printText("Files are not found... Thank You !!" + Constants.instance().NEW_LINE + Constants.instance().NEW_LINE + e.getClass() + Constants.instance().Arrow + e.getLocalizedMessage());
		}
		internalTempArray = null;
	}

	public void copyFilesOneToOtherDir(String sourceDirPath, String destinationDirPath) {
		copyFilesOneDirToAnotherDir(sourceDirPath, destinationDirPath);
	}

	private void copyFilesOneDirToAnotherDir(String sourceDirPath, String destinationDirPath) {
		try {
			if (!sourceDirPath.isEmpty() | !destinationDirPath.isEmpty()) {
				File srcFile = new File(sourceDirPath);
				File destFile = new File(makeDirectory(destinationDirPath));
				if (srcFile.exists()) {
					try {
						org.apache.commons.io.FileUtils.copyDirectory(srcFile, destFile);
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else
					printText("Directory is not exist at given path. Please check the given path. Thank you !!!");
			}
		} catch (Exception e) {
			printText(Constants.instance().NEW_LINE + "Please check the provided paths " + Constants.instance().Arrow + sourceDirPath + Constants.instance().NEW_LINE + Constants.instance().NEW_LINE + e.getClass() + Constants.instance().Arrow + e.getLocalizedMessage());
		}
	}

	public void copyFilesOneToOtherLoc(String sourceFilePath, String destinationLoc) {
		try {
			if (!sourceFilePath.isEmpty() | !destinationLoc.isEmpty()) {
				File srcFile = new File(sourceFilePath);
				File destFile = new File(makeDirectory(destinationLoc));
				if (srcFile.exists()) {
					try {
						org.apache.commons.io.FileUtils.copyFileToDirectory(srcFile, destFile);
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else
					printText("Directory is not exist at the given path. Please check the given path. Thank you !!!");
			}
		} catch (Exception e) {
			printText(Constants.instance().NEW_LINE + "Please check the provided paths " + Constants.instance().Arrow + sourceFilePath + Constants.instance().NEW_LINE + Constants.instance().NEW_LINE + e.getClass() + Constants.instance().Arrow + e.getLocalizedMessage());
		}
	}

	public void deleteFiles(String folderPath) {
		if (!folderPath.isEmpty()) {
			File[] tempFile = listOfFiles(folderPath);
			for (File file : tempFile)
				file.delete();
		}
	}

	public void printText(String tempString) {
		System.out.println(tempString);
	}

	public void nextLine() {
		System.out.println();
	}

	public void printTextOnSameLine(String tempStr) {
		System.out.print(tempStr);
	}
	
	public void destoryObject(Object object) {
		if(object != null) {
			object = null;
			System.gc();
		}
	}
	
	public ArrayList<String> listOfNames(String directory) {
		ArrayList<String> tempList = new ArrayList<String>();
		if(!directory.isEmpty()) {
			for(File file : listOfFiles(directory))
				tempList.add(file.getName().trim());
		}
		return tempList;
	}
	
	public void cleanDirectories() {
		FileUtils.instance().deleteFiles(Constants.instance().TEMP_FOLDER1);
		FileUtils.instance().deleteFiles(Constants.instance().TEMP_FOLDER2);
		FileUtils.instance().deleteFiles(Constants.instance().RENAMED_FOLDER1);
		FileUtils.instance().deleteFiles(Constants.instance().RENAMED_FOLDER2);
		FileUtils.instance().deleteFiles(Constants.instance().ACTUALIMAGES);
		FileUtils.instance().deleteFiles(Constants.instance().EXPECTEDIMAGES);
		FileUtils.instance().deleteFiles(Constants.instance().DIFFERENCEIMAGES);
		FileUtils.instance().deleteFiles(Constants.instance().OUTPUT_FOLDER);
		FileUtils.instance().deleteFiles(Constants.instance().EXTRA_IMG_1);
		FileUtils.instance().deleteFiles(Constants.instance().EXTRA_IMG_2);
	}
}
